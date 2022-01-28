package com.nandafr.playaja.domain.usecases;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.RetrofitClient;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.external.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetMovieUseCase implements MovieRepository {

    @Override
    public Observable<Movie> getPopMovie() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Movie> getPopMovieByCountry() {
        return RetrofitClient.getRetrofit().create(MovieService.class)
                .getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE,"1", Constants.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
