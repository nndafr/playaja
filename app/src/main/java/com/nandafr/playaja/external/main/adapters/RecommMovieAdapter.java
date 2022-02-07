package com.nandafr.playaja.external.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nandafr.playaja.R;
import com.nandafr.playaja.app.view.DetailActivity;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;
import com.nandafr.playaja.external.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommMovieAdapter extends RecyclerView.Adapter<RecommMovieAdapter.RecommMoviesHolder> {

    List<MovieResultDataClass> movieList;
    Context context;
    String TAG = PopularMovieCountryAdapter.class.getSimpleName();


    public RecommMovieAdapter(List<MovieResultDataClass> movieList, Context context){
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public RecommMovieAdapter.RecommMoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_single_movie, parent, false);
        RecommMovieAdapter.RecommMoviesHolder mh = new RecommMovieAdapter.RecommMoviesHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(RecommMovieAdapter.RecommMoviesHolder holder, int position) {
        int idMovie = movieList.get(position).getId();
        String title = movieList.get(position).getTitle();
        holder.movieTitle.setText(movieList.get(position).getTitle());
        Picasso.get().load(Constants.IMAGE_PATH + movieList.get(position).getPosterPath()).into(holder.moviePoster);

        Log.d(TAG, Constants.IMAGE_PATH + movieList.get(position).getPosterPath());

        holder.movieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Dipilih: " + title, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(Constants.KEY_IDMOVIE, idMovie);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class RecommMoviesHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        ImageView moviePoster;
        RelativeLayout movieItem;

        public RecommMoviesHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_title);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieItem = itemView.findViewById(R.id.itemMovie);
        }
    }
}