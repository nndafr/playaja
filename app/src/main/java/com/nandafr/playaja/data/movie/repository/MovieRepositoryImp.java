package com.nandafr.playaja.data.movie.repository;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepositoryImp implements MovieRepository {

    @Override
    public Observable<MovieDataClass> getPopMovie() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    @Override
    public Observable<MovieDataClass> getPopMovieByCountry() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,"1", Constants.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }
}
