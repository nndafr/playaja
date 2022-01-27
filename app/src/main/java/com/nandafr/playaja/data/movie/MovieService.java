package com.nandafr.playaja.data.movie;

import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.models.VideoResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    //Pop = Popular

    @GET("movie/popular")
    Observable<Movie> getPopMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("page") String page);

    @GET("movie/top_rated")
    Observable<Movie> getPopMoviesByCountry(@Query("api_key") String api_key, @Query("language") String language, @Query("page") String page, @Query("region") String region);

    @GET("movie/{movie_id}")
    Observable<MovieResult> getDetailMovie(@Path(value="movie_id") int movie_id, @Query("api_key") String api_key, @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Observable<Video> getVideoMovie(@Path(value="movie_id") int movie_id, @Query("api_key") String api_key);


}
