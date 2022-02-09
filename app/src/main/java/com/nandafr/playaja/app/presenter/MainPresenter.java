package com.nandafr.playaja.app.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nandafr.playaja.R;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.observers.DisposableObserver;

public class MainPresenter implements com.nandafr.playaja.domain.interfaces.main.MainPresenter {

    private MainView mvi;
    private GetMovieUseCase getMovieUseCase;
    private String TAG = MainPresenter.class.getSimpleName();


    public MainPresenter(MainView mvi, MovieRepository movieRepository) {
        this.getMovieUseCase = new GetMovieUseCaseImp(movieRepository);
        this.mvi = mvi;
    }


    @Override
    public void getMovie() {
//        getMovieUseCase.getPopMovie().subscribeWith(getMovieObserver());
        getMovieUseCase.getPopMovie().subscribe(new DisposableObserver<Movie>() {
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
        });
    }

    @Override
    public void getPopMovieCountry() {
//        getMovieUseCase.getPopMovieByCountry().subscribeWith(getMovieCountryObserver());
        getMovieUseCase.getPopMovieByCountry().subscribe(new DisposableObserver<Movie>() {
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
        });

    }


    public DisposableObserver<Movie> getMovieObserver() {
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

    public DisposableObserver<Movie> getMovieCountryObserver() {
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
