package com.nandafr.playaja.domain.repository;

import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;

import io.reactivex.Observable;

public interface MovieDetailRepository {

    Observable<Movie> getRelateMovie();
    Observable<MovieResult> getSingleMovie(int movie_id);
    Observable<Video> getSingleMovieVideo(int movie_id);

}
