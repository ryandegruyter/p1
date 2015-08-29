package be.ryan.popularmovies.tmdb;

import java.util.List;

import be.ryan.popularmovies.domain.PopularMoviesPage;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ryan on 27/08/2015.
 */
public interface TmdbService {

    @GET("/movie/popular")
    void listPopularMovies(Callback<PopularMoviesPage> callback);

}
