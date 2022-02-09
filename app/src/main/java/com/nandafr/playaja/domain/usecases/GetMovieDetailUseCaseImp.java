package com.nandafr.playaja.domain.usecases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.models.VideoResult;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class GetMovieDetailUseCaseImp implements GetMovieDetailUseCase{

    private MovieDetailRepository movieDetailRepository;
    private String TAG = GetMovieDetailUseCaseImp.class.getSimpleName();

    public GetMovieDetailUseCaseImp(MovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }

    @Override
    public Observable<MovieResult> getSingleMovie(int movie_id) {
        return movieDetailRepository.getSingleMovie(movie_id)
                .map(new Function<MovieResultDataClass, MovieResult>() {
                    @Override
                    public MovieResult apply(@NonNull MovieResultDataClass movieResultDataClass) throws Exception {

                        MovieResult movieResult = new MovieResult();
                        movieResult.setId(movieResultDataClass.getId());
                        movieResult.setTitle(movieResultDataClass.getTitle());
                        movieResult.setVoteAverage(movieResultDataClass.getVoteAverage());
                        movieResult.setReleaseDate(movieResultDataClass.getReleaseDate());
                        movieResult.setOverview(movieResultDataClass.getOverview());

                        return movieResult;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Video> getSingleMovieVideo(int movie_id) {
        return movieDetailRepository.getSingleMovieVideo(movie_id)
                .map(new Function<VideoDataClass, Video>() {
                    @Override
                    public Video apply(@NonNull VideoDataClass videoDataClass) throws Exception {

                        Video video = new Video();
                        video.setId(videoDataClass.getId());
                        video.setResults(videoDataClass.getResults());

                        return video;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<Movie> getRelateMovie(int movie_id) {
        return movieDetailRepository.getRelateMovie(movie_id)
                .map(new Function<MovieDataClass, Movie>() {
                    @Override
                    public Movie apply(@NonNull MovieDataClass movieDataClass) throws Exception {

                        List<MovieResult> movieResultsList = new ArrayList<>();
                        for(int i=0; i < movieDataClass.getResults().size(); i++){
                            MovieResult movieResult = new MovieResult();
                            movieResult.setId(movieDataClass.getResults().get(i).getId());
                            movieResult.setBackdropPath(movieDataClass.getResults().get(i).getBackdropPath());
                            movieResult.setPosterPath(movieDataClass.getResults().get(i).getPosterPath());
                            movieResult.setTitle(movieDataClass.getResults().get(i).getTitle());
                            movieResult.setOverview(movieDataClass.getResults().get(i).getOverview());
                            movieResult.setVoteAverage(movieDataClass.getResults().get(i).getVoteAverage());
                            movieResult.setReleaseDate(movieDataClass.getResults().get(i).getReleaseDate());
                            movieResultsList.add(movieResult);
                        }

                        Movie movie = new Movie();
                        movie.setTotalResults(movieDataClass.getTotalResults());
                        movie.setTotalPages(movieDataClass.getTotalPages());
                        movie.setPage(movieDataClass.getPage());
                        movie.setResults(movieResultsList);

                        return movie;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
