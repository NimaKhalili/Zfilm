package com.example.zfilm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<MovieEntry> movieList;

    public MovieListAdapter(List<MovieEntry> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntry movie = movieList.get(position);
        holder.name.setText(String.format("%s %s", "نام فیلم :", movie.getName()));
        holder.year.setText(String.format("%s %s", "سال انتشار :", movie.getYear()));
        Picasso.get().load(movie.getPoster()).into(holder.poster);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.name.getContext(), MovieActivity.class);
            intent.putExtra("EXTRA", movie);
            holder.name.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster = itemView.findViewById(R.id.imageView_itemMovieList_poster);
        TextView name = itemView.findViewById(R.id.textView_itemMovieList_name);
        TextView year = itemView.findViewById(R.id.textView_itemMovieList_year);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}