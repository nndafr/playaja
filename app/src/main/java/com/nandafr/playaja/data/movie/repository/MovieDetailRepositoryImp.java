package com.nandafr.playaja.data.movie.repository;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailRepositoryImp implements MovieDetailRepository {
    @Override
    public Observable<MovieDataClass> getRelateMovie(int movie_id) {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getRecomMovie(movie_id, Constants.API_KEY, Constants.DEFAULT_LANGUAGE, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    @Override
    public Observable<MovieResultDataClass> getSingleMovie(int movie_id) {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getDetailMovie(movie_id, Constants.API_KEY, Constants.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    @Override
    public Observable<VideoDataClass> getSingleMovieVideo(int movie_id) {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getVideoMovie(movie_id, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }
}
