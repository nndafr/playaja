package com.nandafr.playaja.app.presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.nandafr.playaja.R;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements com.nandafr.playaja.domain.interfaces.main.MainPresenter {

    private MainView mvi;
    private GetMovieUseCase getMovieUseCase;
    private String TAG = MainPresenter.class.getSimpleName();
    private Scheduler mainThread;

    public MainPresenter(MainView mvi, MovieRepository movieRepository, Scheduler mainThread) {
        this.getMovieUseCase = new GetMovieUseCaseImp(mainThread,movieRepository);
        this.mvi = mvi;
        this.mainThread = mainThread;
    }


    @Override
    public void getMovie() {
        getMovieUseCase.getPopMovie().subscribe(new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                mvi.displayMovies(movie);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                mvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                //Do something
            }
        });
    }

    @Override
    public void getPopMovieCountry() {
        getMovieUseCase.getPopMovieByCountry().subscribe(new DisposableObserver<Movie>() {
            @Override
            public void onNext(@NonNull Movie movie) {
                mvi.displayMoviesCountry(movie);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
                mvi.displayError(String.valueOf(R.string.error_get_movie));
            }

            @Override
            public void onComplete() {
                //Do Something
            }
        });

    }

}
