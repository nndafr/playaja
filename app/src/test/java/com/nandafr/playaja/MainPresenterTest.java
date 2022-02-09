package com.nandafr.playaja;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCaseImp;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.app.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import io.reactivex.Observable;

public class MainPresenterTest {

    @Mock MovieRepository movieRepository;
    @Mock GetMovieUseCase getMovieUseCase;
    @Mock MainView mainView;
    @Mock MovieService movieService;
    @Mock MainPresenter presenter;
    @Mock Observable<Movie> movieObservable;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mainView, movieRepository);
        getMovieUseCase = new GetMovieUseCaseImp(movieRepository);

    }



    @Test
    public void getPopMoviesApiTest(){
            movieService.getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,"1");
    }
//
//    @Test
//    public void getPopMovieByCountryApiTest(){
//        movieService.getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE, "1",Constants.DEFAULT_REGION);
//    }
//
    @Test
    public void getPopMovieTest() {

        when(movieService.getPopMovies(Constants.API_KEY, Constants.DEFAULT_LANGUAGE, "1"))
                .thenReturn(Observable.just(new MovieDataClass()));

        presenter.getMovie();

        verify(mainView).showToast("Success get movie!");
        verify(mainView).displayMovies(any(Movie.class));


    }

    @Test void getPopMovieByCountryTest(){}









}
