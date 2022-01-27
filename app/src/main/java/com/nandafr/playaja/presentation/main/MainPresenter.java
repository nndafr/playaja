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
        getMovieObservable().subscribeWith(getMovieObserver());

    }

    @Override
    public void getPopMovieCountry() {
        getMovieCountryObservable().subscribeWith(getMovieCountryObserver());
    }

    public Observable<Movie> getMovieObservable(){
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMovies(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Movie> getMovieObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                Log.d(TAG, "onNext " + movie.getTotalResults());
                mvi.displayMovies(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError  " + e);
                e.printStackTrace();
                mvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                Log.d(TAG, String.valueOf(R.string.complete_get_movie));
            }
        };
    }



    public Observable<Movie> getMovieCountryObservable(){
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,null, Constants.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<Movie> getMovieCountryObserver(){
        return new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                Log.d(TAG, "onNext " + movie.getTotalResults());
                mvi.displayMoviesCountry(movie);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError  " + e);
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
