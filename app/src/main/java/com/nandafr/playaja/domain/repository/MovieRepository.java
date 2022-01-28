package com.nandafr.playaja.domain.repository;

import com.nandafr.playaja.domain.models.Movie;

import io.reactivex.Observable;

public interface MovieRepository {

    //Pop = Popular;
    Observable<Movie> getPopMovie();
    Observable<Movie> getPopMovieByCountry();

}
