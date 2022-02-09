package com.nandafr.playaja;

import static org.mockito.Mockito.when;

import com.nandafr.playaja.app.presenter.MainPresenter;
import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

public class MainUseCaseTest {


    @Mock MovieRepository movieRepository;

    @Mock GetMovieUseCase getMovieUseCase;

    @Mock
    MainView mainView;
    @Mock
    MovieService movieService;
    @Mock
    Observable<Movie> movieObservable;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        getMovieUseCase = new GetMovieUseCaseImp(movieRepository);

    }

    @Test
    public void getPopMovieTest(){
//        when(movieRepository.)

        getMovieUseCase.getPopMovie();

    }





}
