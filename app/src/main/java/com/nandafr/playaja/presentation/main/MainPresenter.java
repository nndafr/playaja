package com.nandafr.playaja.presentation.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nandafr.playaja.R;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements com.nandafr.playaja.domain.interfaces.main.MainPresenter {

    private MainView mvi;
    private GetMovieUseCase getMovieUseCase;
    private String TAG = MainPresenter.class.getSimpleName();



    public MainPresenter(MainView mvi, GetMovieUseCase getMovieUseCase) {
        this.getMovieUseCase = getMovieUseCase;
        this.mvi = mvi;
    }


    @Override
    public void getMovie() {
        getMovieUseCase.getPopMovie().subscribeWith(getMovieObserver());
    }

    @Override
    public void getPopMovieCountry() {
        getMovieUseCase.getPopMovieByCountry().subscribeWith(getMovieCountryObserver());
    }

    public DisposableObserver<Movie> getMovieObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                Log.d(TAG, "getMovie onNext " + movie.getTotalResults());
                mvi.displayMovies(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "getMovie onError  " + e);
                e.printStackTrace();
                mvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }

    public DisposableObserver<Movie> getMovieCountryObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                Log.d(TAG, "getMovieCountry onNext " + movie.getTotalResults());
                mvi.displayMoviesCountry(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "getMovieCountry onError  " + e);
                e.printStackTrace();
                mvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }

}
