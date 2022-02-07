package com.nandafr.playaja.domain.usecases;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class GetMovieUseCaseImp implements GetMovieUseCase{

    private MovieRepository movieRepository;
    private String TAG = GetMovieUseCaseImp.class.getSimpleName();

    public GetMovieUseCaseImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<Movie> getPopMovie() {
        return movieRepository.getPopMovie()
                .map(new Function<MovieDataClass, Movie>() {
                    @Override
                    public Movie apply(MovieDataClass movieDataClass) throws Exception {

                        Movie movie = new Movie();
                        movie.setTotalResults(movieDataClass.getTotalResults());
                        movie.setTotalPages(movieDataClass.getTotalPages());
                        movie.setPage(movieDataClass.getPage());
                        movie.setResults(movieDataClass.getResults());

                        return movie;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Movie> getPopMovieByCountry() {
        return movieRepository.getPopMovieByCountry()
                .map(new Function<MovieDataClass, Movie>() {
                    @Override
                    public Movie apply(@NonNull MovieDataClass movieDataClass) throws Exception {

                        Movie movie = new Movie();
                        movie.setTotalResults(movieDataClass.getTotalResults());
                        movie.setTotalPages(movieDataClass.getTotalPages());
                        movie.setPage(movieDataClass.getPage());
                        movie.setResults(movieDataClass.getResults());

                        return movie;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void testingFun(){

    }
}
