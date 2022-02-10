package com.nandafr.playaja;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import com.nandafr.playaja.app.presenter.MainPresenter;
import com.nandafr.playaja.data.movie.MovieService;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

public class MainUseCaseTest {

    @Mock MovieRepository movieRepository;
    GetMovieUseCase getMovieUseCase;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        getMovieUseCase = new GetMovieUseCaseImp(Schedulers.trampoline(),movieRepository);
    }

    @Test
    public void getPopMovieTest(){
        List<MovieResultDataClass> mrdcList = new ArrayList<>();
        MovieResultDataClass mrdc = new MovieResultDataClass();
        mrdc.setId(1);
        mrdc.setTitle("Title 1");
        mrdc.setOverview("Overview 1");
        mrdc.setPosterPath("path/path/1");
        mrdc.setVoteAverage(8.5);
        mrdcList.add(mrdc);

        MovieDataClass movieDataClass = new MovieDataClass();
        movieDataClass.setPage(1);
        movieDataClass.setResults(mrdcList);
        movieDataClass.setTotalPages(23);

        when(movieRepository.getPopMovie()).thenReturn(Observable.just(movieDataClass));

        TestObserver<Movie> testObserver = getMovieUseCase.getPopMovie().test();
        testObserver.assertValue(mv -> mv.getPage().equals(movieDataClass.getPage()));


    }





}
