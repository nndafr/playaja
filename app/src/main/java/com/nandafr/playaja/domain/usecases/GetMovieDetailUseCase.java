package com.nandafr.playaja.domain.usecases;

import com.nandafr.playaja.domain.models.Movie;

import io.reactivex.Observable;

public interface GetMovieDetailUseCase {

    Observable<Movie> getRelateMovie();
    Observable<Movie> getSingleMovie();
}
