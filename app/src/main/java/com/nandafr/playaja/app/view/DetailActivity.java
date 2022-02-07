package com.nandafr.playaja.app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nandafr.playaja.R;
import com.nandafr.playaja.data.movie.repository.MovieDetailRepositoryImp;
import com.nandafr.playaja.domain.interfaces.detail.DetailView;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.domain.models.Movie;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.domain.models.Video;
import com.nandafr.playaja.domain.repository.MovieDetailRepository;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCase;
import com.nandafr.playaja.domain.usecases.GetMovieDetailUseCaseImp;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.app.presenter.DetailPresenter;
import com.nandafr.playaja.external.main.adapters.PopularMovieAdapter;
import com.nandafr.playaja.external.main.adapters.RecommMovieAdapter;

public class DetailActivity extends AppCompatActivity implements DetailView {

    private String TAG = DetailActivity.class.getSimpleName();
    private CardView toolbar;
    private ImageButton backButton;
    private RecyclerView rvRelateMovie;
    private TextView title, rating, release, overview,appbarTitle;
    private WebView ytVideoPlayer;
    private ProgressBar progressBar;
    private RecyclerView.Adapter adapterRelateMovie;
    private DetailPresenter detailPresenter;
    private GetMovieDetailUseCase getMovieDetailUseCase;
    private MovieDetailRepository movieDetailRepository;
    private int movieid;
    private RecyclerView rvRecommMovie;
    private RecyclerView.Adapter adapterRecommMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                movieid = 0;
            } else {
                movieid = extras.getInt(Constants.KEY_IDMOVIE);
            }
        } else {
            movieid = (int) savedInstanceState.getSerializable(Constants.KEY_IDMOVIE);
        }

        Log.d(TAG, "onCreate MovieID: " + movieid);

        setupViews();
        setupMVP();
        getMovieDetail(movieid);
        getSingleVideoMovie(movieid);
        getRelateMovie(movieid);

    }

    private void setupMVP() {
        movieDetailRepository = new MovieDetailRepositoryImp();
        getMovieDetailUseCase = new GetMovieDetailUseCaseImp(movieDetailRepository);
        detailPresenter = new DetailPresenter(this, movieDetailRepository);
    }

    private void setupViews() {
        toolbar = findViewById(R.id.brand_toolbar);
        backButton = toolbar.findViewById(R.id.btn_back);
        rvRelateMovie = findViewById(R.id.rv_movie_recommendation);
        backButton.setVisibility(View.VISIBLE);
        appbarTitle = findViewById(R.id.appbar_title);
        appbarTitle.setVisibility(View.VISIBLE);

        rvRecommMovie = findViewById(R.id.rv_movie_recommendation);

        ytVideoPlayer = findViewById(R.id.movie_player);
        title = findViewById(R.id.movie_title);
        rating = findViewById(R.id.movie_ratings);
        release = findViewById(R.id.movie_relase_date);
        overview = findViewById(R.id.movie_overview);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Back Button OnClick");
                finish();
            }
        });

        rvRelateMovie.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getMovieDetail(int movie_id){
        detailPresenter.getSingleMovie(movie_id);
    }

    private void getRelateMovie(int movie_id){
        detailPresenter.getRelateMovie(movie_id);
    }

    private void getSingleVideoMovie(int movie_id){
        detailPresenter.getSingleVideo(movie_id);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void displayDetailMovie(MovieResult response) {
        if(response != null){
            Log.d(TAG, response.getTitle());

            appbarTitle.setText(response.getTitle());
            title.setText(response.getTitle());
            rating.setText(String.valueOf(response.getVoteAverage()));
            release.setText(String.format(getString(R.string.movie_rating_text), response.getReleaseDate()));

            if(!response.getOverview().equals("")){
                overview.setText(response.getOverview());
            }else{
                overview.setText(R.string.no_overview);
            }


        }else{
            Log.d(TAG, "Movie response null");
        }

    }

    @Override
    public void displayRelateMovie(Movie response) {
        if(response != null){
//            Log.d(TAG, response.getResults().get(1).getTitle());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            adapterRecommMovie = new RecommMovieAdapter(response.getResults(), DetailActivity.this);
            rvRecommMovie.setAdapter(adapterRecommMovie);
            rvRecommMovie.setLayoutManager(layoutManager);
        }else {
            Log.d(TAG, "Movie response null");
        }
    }

    @Override
    public void displaySingleVideo(Video response) {

        if(response != null){
            if(response.getResults().get(0).getSite().equals("YouTube")){
                String url = "https://www.youtube.com/embed/" + response.getResults().get(0).getKey() +"?rel=0&controls=0";
                String loadData = "<html><body><iframe frameborder=0 allowfullscreen width=100% height=100% src="+ url +"></iframe></body></html>";

                ytVideoPlayer.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                WebSettings webSettings = ytVideoPlayer.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                ytVideoPlayer.loadData(loadData, "text/html", "utf-8");

                Log.d(TAG, "" + response.getResults().get(0).getKey());
            }else{
                showToast("Video Tidak tersedia!");
                Log.d(TAG, "Sumber video bukan dari youtube");
            }


        }else{
            Log.d(TAG, "Movie response null");
        }

    }

    @Override
    public void displayError(String msg) {
        showToast(msg);
    }
}