package com.nandafr.playaja.domain.usecases;

import androidx.annotation.NonNull;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.models.VideoResult;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;

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
                        Movie movie = new Movie();
                        movie.setTotalResults(movieDataClass.getTotalResults());
                        movie.setTotalPages(movieDataClass.getTotalPages());
                        movie.setPage(movieDataClass.getPage());
                        movie.setResults(movieDataClass.getResults());

                        return movie;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

}
