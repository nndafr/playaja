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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nandafr.playaja.R;
import com.nandafr.playaja.domain.models.MovieResult;
import com.nandafr.playaja.external.Constants;
import com.nandafr.playaja.presentation.detail.DetailActivity;
import com.nandafr.playaja.presentation.main.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.PopularMoviesHolder> {

    List<MovieResult> movieList;
    Context context;
    String TAG = PopularMovieAdapter.class.getSimpleName();


    public PopularMovieAdapter(List<MovieResult> movieList, Context context){
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public PopularMoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_single_movie, parent, false);
        PopularMoviesHolder mh = new PopularMoviesHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(PopularMoviesHolder holder, int position) {

        String title = movieList.get(position).getTitle();
        int idMovie = movieList.get(position).getId();
        holder.movieTitle.setText(movieList.get(position).getTitle());
        Picasso.get().load(Constants.IMAGE_PATH + movieList.get(position).getPosterPath()).into(holder.moviePoster);

        Log.d(TAG, Constants.IMAGE_PATH + movieList.get(position).getPosterPath());

        holder.movieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Dipilih: " + idMovie, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(Constants.KEY_IDMOVIE, idMovie);
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class PopularMoviesHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        ImageView moviePoster;
        RelativeLayout movieItem;

        public PopularMoviesHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_title);
            moviePoster = itemView.findViewById(R.id.movie_poster);
            movieItem = itemView.findViewById(R.id.itemMovie);
        }
    }
}
