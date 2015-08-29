package be.ryan.popularmovies.domain;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Ryan on 27/08/2015.
 */
@Parcel
public class PopularMoviesPage {

    @SerializedName("page")
    public int pageNr;

    @SerializedName("results")
    private List<PopularMovie> popularMovieList;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_results")
    private int total_results;

    public int getPageNr() {
        return pageNr;
    }

    public List<PopularMovie> getPopularMovieList() {
        return popularMovieList;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    @Override
    public String toString() {
        return "PopularMoviesPage{" +
                "pageNr=" + pageNr +
                ", popularMovieList=" + popularMovieList +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                '}';
    }
}
