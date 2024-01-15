package com.example.zfilm;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
    private MovieListAdapter adapter;
    private RecyclerView recyclerView;

    private ArrayList<MovieEntry> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView = findViewById(R.id.recyclerView_movieList);
        getMovieList();
        setActionBarTitle();
        prepareRecyclerView();
    }


    private void setActionBarTitle() {
        String title = getIntent().getStringExtra("EXTRA2");
        if (title != null)
            getSupportActionBar().setTitle(title);
        else
            getSupportActionBar().setTitle(movieList.get(0).getGenre());
    }


    private void prepareRecyclerView() {
        adapter = new MovieListAdapter(movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void getMovieList() {
        movieList = (ArrayList<MovieEntry>) getIntent().getSerializableExtra("EXTRA");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_back)
            finish();
        return super.onOptionsItemSelected(item);
    }
}