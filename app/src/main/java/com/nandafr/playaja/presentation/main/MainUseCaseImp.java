package com.nandafr.playaja.presentation.main;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainUseCaseImp implements GetMovieUseCase {
    @Override
    public Observable<Movie> getPopMovie() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Movie> getPopMoviewByCountry() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,"1", Constants.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
