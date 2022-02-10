package com.nandafr.playaja;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nandafr.playaja.app.presenter.DetailPresenter;
import com.nandafr.playaja.app.presenter.MainPresenter;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;
import com.nandafr.playaja.domain.interfaces.detail.DetailView;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCaseImp;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenterTest {


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    DetailView detailView;
    @Mock
    MovieDetailRepository movieDRepository;
    GetMovieDetailUseCase getMovieDUseCase;
    DetailPresenter detailPresenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        getMovieDUseCase = new GetMovieDetailUseCaseImp(Schedulers.trampoline(),movieDRepository);
        detailPresenter = new DetailPresenter(detailView, movieDRepository, Schedulers.trampoline());
    }


    @Test
    public void getRelateMovieTest() {
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

        when(movieDRepository.getRelateMovie(585083)).thenReturn(Observable.just(movieDC));

        detailPresenter.getRelateMovie(585083);

        verify(detailView).displayRelateMovie(any(Movie.class));

    }


    @Test
    public void getSingleMovieTest(){
        MovieResultDataClass movieDC = new MovieResultDataClass();
        movieDC.setId(1);
        movieDC.setVoteAverage(8.5);
        movieDC.setOverview("This is overview");
        movieDC.setPosterPath("path/path/1");
        movieDC.setReleaseDate("20-01-2022");

        when(movieDRepository.getSingleMovie(585083)).thenReturn(Observable.just(movieDC));

        detailPresenter.getSingleMovie(585083);

        verify(detailView).displayDetailMovie(any(MovieResult.class));
    }


    @Test
    public void getSingleVideoTest(){
        List<VideoResultDataClass> videoRDCList = new ArrayList<>();
        VideoResultDataClass videoRDC = new VideoResultDataClass();
        videoRDC.setId("1");
        videoRDC.setKey("2134567fdsa");
        videoRDC.setName("name");
        videoRDC.setSite("youtube.com");
        videoRDCList.add(videoRDC);


        VideoDataClass videoDC = new VideoDataClass();
        videoDC.setId(1);
        videoDC.setResults(videoRDCList);

        when(movieDRepository.getSingleMovieVideo(585083)).thenReturn(Observable.just(videoDC));

        detailPresenter.getSingleVideo(585083);

        verify(detailView).displaySingleVideo(any(Video.class));
    }
}
