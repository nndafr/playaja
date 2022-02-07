package com.nandafr.playaja.domain.usecases;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.domain.models.Movie;

import io.reactivex.Observable;

public interface GetMovieUseCase  {

    Observable<Movie> getPopMovie();
    Observable<Movie> getPopMovieByCountry();

//    @Override
//    public Observable<Movie> getPopMovie() {
//        return RetrofitClient.getRetrofit().create(MovieService.class)
//                .getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,"1")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    @Override
//    public Observable<Movie> getPopMovieByCountry() {
//        return RetrofitClient.getRetrofit().create(MovieService.class)
//                .getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,"1", Constants.DEFAULT_REGION)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
