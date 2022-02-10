package com.nandafr.playaja.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nandafr.playaja.R;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.repository.MovieRepositoryImp;
import com.nandafr.playaja.domain.interfaces.main.MainView;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.repository.MovieRepository;
import com.nandafr.playaja.domain.usecases.GetMovieUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieUseCaseImp;
import com.nandafr.playaja.external.main.adapters.PopularMovieAdapter;
import com.nandafr.playaja.external.main.adapters.PopularMovieCountryAdapter;
import com.nandafr.playaja.app.presenter.MainPresenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements MainView {

    private String TAG = MainActivity.class.getSimpleName();
    private CardView toolbar;
    private ImageView brand;
    private RecyclerView rvPopularMovie, rvPopularMovieByCountry;
    private ProgressBar progressBar;
    private RecyclerView.Adapter adapterPopMovie, adapterMovieByCountry;
    private MainPresenter mainPresenter;
    private GetMovieUseCase getMovieUseCase;
    private MovieRepository movieRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupMVP();
        getPopMovieList();
        getPopMovieCountry();
    }


    private void setupMVP(){
        movieRepository = new MovieRepositoryImp();
        getMovieUseCase = new GetMovieUseCaseImp(AndroidSchedulers.mainThread(),movieRepository);
        mainPresenter = new MainPresenter(this, movieRepository, AndroidSchedulers.mainThread());
    }

    private void setupViews(){
        toolbar = findViewById(R.id.brand_toolbar);
        brand = toolbar.findViewById(R.id.brand);
        rvPopularMovie = findViewById(R.id.rv_movie_popular);
        rvPopularMovieByCountry = findViewById(R.id.rv_movie_popular_country);
        brand.setVisibility(View.VISIBLE);

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Logo onClick");
            }
        });

        rvPopularMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getPopMovieList(){
        mainPresenter.getMovie();
    }

    private void getPopMovieCountry(){mainPresenter.getPopMovieCountry();}


    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayMovies(Movie response) {

        if(response != null){
//            Log.d(TAG, response.getResults().get(1).getTitle());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            adapterPopMovie = new PopularMovieAdapter(response.getResults(), MainActivity.this);
            rvPopularMovie.setAdapter(adapterPopMovie);
            rvPopularMovie.setLayoutManager(layoutManager);
        }else {
            //Log.d(TAG, "Movie response null");
        }

    }

    @Override
    public void displayMoviesCountry(Movie response) {
        if(response != null){
//            Log.d(TAG, response.getResults().get(1).getTitle());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            adapterMovieByCountry = new PopularMovieCountryAdapter(response.getResults(), MainActivity.this);
            rvPopularMovieByCountry.setAdapter(adapterMovieByCountry);
            rvPopularMovieByCountry.setLayoutManager(layoutManager);
        }else {
            Log.d(TAG, "Movie country response null");
        }
    }

    @Override
    public void displayError(String msg) {
        showToast(msg);
    }
}