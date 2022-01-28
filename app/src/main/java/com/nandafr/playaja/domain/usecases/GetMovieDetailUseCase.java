package com.nandafr.playaja.domain.usecases;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetMovieDetailUseCase implements MovieDetailRepository {

    @Override
    public Observable<Movie> getRelateMovie() {
        return null;
    }

    @Override
    public Observable<MovieResult> getSingleMovie(int movie_id) {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getDetailMovie(movie_id, Constants.API_KEY, Constants.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Video> getSingleMovieVideo(int movie_id) {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getVideoMovie(movie_id, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
