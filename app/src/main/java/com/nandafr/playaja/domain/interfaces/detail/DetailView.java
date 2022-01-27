package com.nandafr.playaja.domain.interfaces.detail;

import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;

public interface DetailView {

    void showToast(String msg);
    void showProgressBar();
    void hideProgressBar();
    void displayDetailMovie(MovieResult response);
    void displayRelateMovie(Movie response);
    void displaySingleVideo(Video response);
    void displayError(String msg);
}
