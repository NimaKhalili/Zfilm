package com.example.zfilm;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<MovieEntry> getAllMovies();

    @Insert
    void insertAllMovies(List<MovieEntry> movieList);

    @Query("SELECT * FROM movie WHERE genre != 'سریال' and genre != 'انیمیشن'")
    List<MovieEntry> getFilms();

    @Query("SELECT * FROM movie WHERE genre = 'سریال'")
    List<MovieEntry> getSerials();

    @Query("SELECT * FROM movie WHERE genre = 'انیمیشن'")
    List<MovieEntry> getAnimations();

    @Query("SELECT * FROM movie ORDER BY year DESC")
    List<MovieEntry> getAllMoviesOrderByYear();

    @Query("SELECT * FROM movie WHERE genre = 'اکشن'")
    List<MovieEntry> getActions();

    @Query("SELECT * FROM movie WHERE genre = 'کمدی'")
    List<MovieEntry> getComedies();

    @Query("SELECT * FROM movie WHERE genre = 'جنایی'")
    List<MovieEntry> getCrimes();

    @Query("SELECT * FROM movie WHERE genre = 'ترسناک'")
    List<MovieEntry> getHorrors();

    @Query("SELECT * FROM movie WHERE genre = 'عاشقانه'")
    List<MovieEntry> getRomances();

    @Query("SELECT * FROM movie WHERE genre = 'علمی تخیلی'")
    List<MovieEntry> getSciFis();

    @Query("SELECT * FROM movie WHERE genre = 'جنگی'")
    List<MovieEntry> getWars();
}
