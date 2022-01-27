package com.nandafr.playaja.domain.interfaces.main;

import com.nandafr.playaja.domain.models.Movie;

public interface MainView {

    void showToast(String msg);
    void showProgressBar();
    void hideProgressBar();
    void displayMovies(Movie response);
    void displayMoviesCountry(Movie response);
    void displayError(String msg);

}
