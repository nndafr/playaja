package com.nandafr.playaja.app.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nandafr.playaja.R;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.domain.interfaces.detail.DetailView;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCaseImp;

import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

public class DetailPresenter implements com.nandafr.playaja.domain.interfaces.detail.DetailPresenter {

    private DetailView dvi;
    private GetMovieDetailUseCase getMovieDetailUseCase;
    private String TAG = DetailPresenter.class.getSimpleName();
    private Scheduler mainThread;


    public DetailPresenter(DetailView dvi, MovieDetailRepository movieDetailRepository, Scheduler mainThread) {
        this.getMovieDetailUseCase = new GetMovieDetailUseCaseImp(mainThread,movieDetailRepository);
        this.dvi = dvi;
        this.mainThread = mainThread;
    }

    @Override
    public void getSingleMovie(int movie_id) {
        getMovieDetailUseCase.getSingleMovie(movie_id).subscribe(new DisposableObserver<MovieResult>() {
            @Override
            public void onNext(MovieResult movieResult) {
                dvi.displayDetailMovie(movieResult);

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));

            }

            @Override
            public void onComplete() {
                //Do Something
            }
        });
    }
    @Override
    public void getSingleVideo(int movie_id) {
        getMovieDetailUseCase.getSingleMovieVideo(movie_id).subscribe(new DisposableObserver<Video>() {
            @Override
            public void onNext(@NonNull Video video) {
                dvi.displaySingleVideo(video);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                //Do Something

            }
        });
    }


    @Override
    public void getRelateMovie(int movie_id) {
         getMovieDetailUseCase.getRelateMovie(movie_id).subscribe(new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                dvi.displayRelateMovie(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                //Do Something
            }
        });
    }

}
