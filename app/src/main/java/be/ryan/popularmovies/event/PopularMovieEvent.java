package be.ryan.popularmovies.event;

import be.ryan.popularmovies.domain.PopularMovie;

/**
 * Created by Ryan on 31/08/2015.
 */
public class PopularMovieEvent {
    public final PopularMovie movie;

    public PopularMovieEvent(PopularMovie movie) {
        this.movie = movie;
    }
}
