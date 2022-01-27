package com.nandafr.playaja.presentation.detail;

import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCase;

import io.reactivex.Observable;

public class DetailUseCaseImp implements GetMovieDetailUseCase {
    @Override
    public Observable<Movie> getRelateMovie() {
        return null;
    }

    @Override
    public Observable<Movie> getSingleMovie() {
        return null;
    }
}
