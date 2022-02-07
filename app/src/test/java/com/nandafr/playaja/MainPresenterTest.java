package com.nandafr.playaja;

import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.app.presenter.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainPresenterTest {

    @Mock
    MovieRepository movieRepository;
    MainView mainView;
    MovieService movieService;
    MainPresenter presenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(mainView, movieRepository);
    }


//    @Test
//    public void getPopMoviesApiTest(){
//            movieService.getPopMovies(Constants.API_KEY,Constants.DEFAULT_LANGUAGE,"1");
//    }
//
//    @Test
//    public void getPopMovieByCountryApiTest(){
//        movieService.getPopMoviesByCountry(Constants.API_KEY, Constants.DEFAULT_LANGUAGE, "1",Constants.DEFAULT_REGION);
//    }
//
    @Test
    public void getPopMovieTest(){}

    @Test void getPopMovieByCountryTest(){}









}
