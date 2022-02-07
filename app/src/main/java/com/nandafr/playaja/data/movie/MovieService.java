package com.nandafr.playaja.data.movie;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;
import com.nandafr.playaja.domain.models.Video;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    //Pop = Popular

    @GET("movie/popular")
    Observable<MovieDataClass> getPopMovies(@Query("api_key") String api_key, @Query("language") String language, @Query("page") String page);

    @GET("movie/top_rated")
    Observable<MovieDataClass> getPopMoviesByCountry(@Query("api_key") String api_key, @Query("language") String language, @Query("page") String page, @Query("region") String region);

    @GET("movie/{movie_id}")
    Observable<MovieResultDataClass> getDetailMovie(@Path(value="movie_id") int movie_id, @Query("api_key") String api_key, @Query("language") String language);

    @GET("movie/{movie_id}/videos")
    Observable<VideoDataClass> getVideoMovie(@Path(value="movie_id") int movie_id, @Query("api_key") String api_key);

    //Recom = Recommendation
    @GET("movie/{movie_id}/recommendations")
    Observable<MovieDataClass> getRecomMovie(@Path(value="movie_id") int movie_id, @Query("api_key") String api_key,  @Query("language") String language, @Query("page") String page);



}
