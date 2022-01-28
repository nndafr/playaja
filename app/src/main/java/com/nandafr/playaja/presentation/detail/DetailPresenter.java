package com.nandafr.playaja.presentation.detail;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nandafr.playaja.R;
import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.domain.interfaces.detail.DetailView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCase;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements com.nandafr.playaja.domain.interfaces.detail.DetailPresenter {

    private DetailView dvi;
    private GetMovieDetailUseCase getMovieDetailUseCase;
    private String TAG = DetailPresenter.class.getSimpleName();


    public DetailPresenter(DetailView dvi, GetMovieDetailUseCase getMovieDetailUseCase) {
        this.getMovieDetailUseCase = getMovieDetailUseCase;
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
    public void getRelateMovie() {

    }
}
