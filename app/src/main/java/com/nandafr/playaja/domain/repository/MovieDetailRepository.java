package com.nandafr.playaja.domain.repository;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;

import io.reactivex.Observable;

public interface MovieDetailRepository {

    Observable<MovieDataClass> getRelateMovie(int movie_id);
    Observable<MovieResultDataClass> getSingleMovie(int movie_id);
    Observable<VideoDataClass> getSingleMovieVideo(int movie_id);

}
