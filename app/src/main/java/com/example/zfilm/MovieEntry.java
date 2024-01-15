package com.example.zfilm;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntry implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "explain")
    private String explain;

    @ColumnInfo(name = "poster")
    private int poster;

    public MovieEntry(int id, String name, String genre, int year, String explain, int poster) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.explain = explain;
        this.poster = poster;
    }

    @Ignore
    public MovieEntry(String name, String genre, int year, String explain, int poster) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.explain = explain;
        this.poster = poster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.genre);
        dest.writeInt(this.year);
        dest.writeString(this.explain);
        dest.writeInt(this.poster);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.genre = source.readString();
        this.year = source.readInt();
        this.explain = source.readString();
        this.poster = source.readInt();
    }

    protected MovieEntry(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.genre = in.readString();
        this.year = in.readInt();
        this.explain = in.readString();
        this.poster = in.readInt();
    }

    public static final Creator<MovieEntry> CREATOR = new Creator<MovieEntry>() {
        @Override
        public MovieEntry createFromParcel(Parcel source) {
            return new MovieEntry(source);
        }

        @Override
        public MovieEntry[] newArray(int size) {
            return new MovieEntry[size];
        }
    };
}
