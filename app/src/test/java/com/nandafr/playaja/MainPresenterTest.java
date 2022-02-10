package com.nandafr.playaja;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.os.Debug;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.repository.MovieRepositoryImp;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCaseImp;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.app.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;

public class MainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock MainView mainView;
    @Mock MovieRepository movieRepository;
    GetMovieUseCase getMovieUseCase;
    MainPresenter mainPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        getMovieUseCase = new GetMovieUseCaseImp(Schedulers.trampoline(),movieRepository);
        mainPresenter = new MainPresenter(mainView, movieRepository, Schedulers.trampoline());
    }

    @Test
    public void getPopMovieTest() {
        List<MovieResultDataClass> mrdcList = new ArrayList<>();
        MovieResultDataClass movieResultDc = new MovieResultDataClass();

        movieResultDc.setId(0);
        movieResultDc.setTitle("Example 0");
        movieResultDc.setPosterPath("photo/path/0");
        movieResultDc.setOverview("Overview 0");
        movieResultDc.setReleaseDate("20-12-2021");
        movieResultDc.setVoteAverage(8.5);
        mrdcList.add(movieResultDc);

        MovieDataClass movieDC = new MovieDataClass();
        movieDC.setPage(1);
        movieDC.setTotalResults(459);
        movieDC.setTotalPages(23);
        movieDC.setResults(mrdcList);

        when(movieRepository.getPopMovie()).thenReturn(Observable.just(movieDC));

        mainPresenter.getMovie();

        verify(mainView).displayMovies(any(Movie.class));

    }

    @Test
    public void getPopMovieByCountryTest(){
        List<MovieResultDataClass> mrdcList = new ArrayList<>();
        MovieResultDataClass movieResultDc = new MovieResultDataClass();

        movieResultDc.setId(0);
        movieResultDc.setTitle("Example 0");
        movieResultDc.setPosterPath("photo/path/0");
        movieResultDc.setOverview("Overview 0");
        movieResultDc.setReleaseDate("20-12-2021");
        movieResultDc.setVoteAverage(8.5);
        mrdcList.add(movieResultDc);

        MovieDataClass movieDC = new MovieDataClass();
        movieDC.setPage(1);
        movieDC.setTotalResults(459);
        movieDC.setTotalPages(23);
        movieDC.setResults(mrdcList);

        when(movieRepository.getPopMovieByCountry()).thenReturn(Observable.just(movieDC));

        mainPresenter.getPopMovieCountry();

        verify(mainView).displayMoviesCountry(any(Movie.class));
    }









}
