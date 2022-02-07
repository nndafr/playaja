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

import io.reactivex.observers.DisposableObserver;

public class DetailPresenter implements com.nandafr.playaja.domain.interfaces.detail.DetailPresenter {

    private DetailView dvi;
    private GetMovieDetailUseCase getMovieDetailUseCase;
    private String TAG = DetailPresenter.class.getSimpleName();


    public DetailPresenter(DetailView dvi, MovieDetailRepository movieDetailRepository) {
        this.getMovieDetailUseCase = new GetMovieDetailUseCaseImp(movieDetailRepository);
        this.dvi = dvi;
    }

    @Override
    public void getSingleMovie(int movie_id) {
        Log.d(TAG, "getSingleMovie " + movie_id);

        getMovieDetailUseCase.getSingleMovie(movie_id).subscribeWith(getDetailMovieObserver());


    }

    public DisposableObserver<MovieResult> getDetailMovieObserver(){
        return new DisposableObserver<MovieResult>() {
            @Override
            public void onNext(@NonNull MovieResult movie) {
                Log.d(TAG, "getDetailMovie onNext " + movie.getId());
                dvi.displayDetailMovie(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "getDetailMovie onError  " + e);
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }


    @Override
    public void getSingleVideo(int movie_id) {
        Log.d(TAG, "getSingleVideo " + movie_id);
        getMovieDetailUseCase.getSingleMovieVideo(movie_id).subscribeWith(getSingleVideoMovieObserver());
    }


    public DisposableObserver<Video> getSingleVideoMovieObserver(){
        return new DisposableObserver<Video>() {
            @Override
            public void onNext(@NonNull Video video) {
                Log.d(TAG, "onNext " + video.getResults());
                dvi.displaySingleVideo(video);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError  " + e);
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }


    @Override
    public void getRelateMovie(int movie_id) {
        getMovieDetailUseCase.getRelateMovie(movie_id).subscribeWith(getRecommMovieObserver());
    }

    private DisposableObserver<Movie> getRecommMovieObserver() {
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                Log.d(TAG, "getRecommMovie onNext " + movie.getTotalResults());
                dvi.displayRelateMovie(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "getMovie onError  " + e);
                e.printStackTrace();
                dvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }
}
