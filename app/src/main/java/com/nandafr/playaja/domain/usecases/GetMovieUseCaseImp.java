package com.nandafr.playaja.domain.usecases;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class GetMovieUseCaseImp implements GetMovieUseCase{

    private MovieRepository movieRepository;
    private String TAG = GetMovieUseCaseImp.class.getSimpleName();
    private Scheduler mainThread;

    public GetMovieUseCaseImp(Scheduler mainThread, MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.mainThread = mainThread;
    }

    @Override
    public Observable<Movie> getPopMovie() {
        return movieRepository.getPopMovie()
                .map(new Function<MovieDataClass, Movie>() {
                    @Override
                    public Movie apply(MovieDataClass movieDataClass) throws Exception {
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

                .observeOn(mainThread);
    }

    @Override
    public Observable<Movie> getPopMovieByCountry() {
        return movieRepository.getPopMovieByCountry()
                .map(new Function<MovieDataClass, Movie>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
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

                .observeOn(mainThread);
    }


}
