package com.nandafr.playaja.domain.usecases;

import com.nandafr.playaja.domain.models.Movie;

import io.reactivex.Observable;

public interface GetMovieUseCase {

    //Pop = Popular;
    Observable<Movie> getPopMovie();
    Observable<Movie> getPopMoviewByCountry();


}
