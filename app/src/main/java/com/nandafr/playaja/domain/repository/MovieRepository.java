package com.nandafr.playaja.domain.repository;

import com.nandafr.playaja.data.movie.model.MovieDataClass;

import io.reactivex.Observable;

public interface MovieRepository {

    //Pop = Popular;
    Observable<MovieDataClass> getPopMovie();
    Observable<MovieDataClass> getPopMovieByCountry();

}
