package be.ryan.popularmovies.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.PopularMovie;
import be.ryan.popularmovies.domain.PopularMoviesPage;
import be.ryan.popularmovies.tmdb.TmdbService;
import be.ryan.popularmovies.tmdb.TmdbWebServiceContract;
import be.ryan.popularmovies.ui.adapter.PopularMoviesAdapter;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieListFragment extends android.support.v4.app.Fragment implements RequestInterceptor, Callback<PopularMoviesPage> {

    private RecyclerView mMovieListRecyclerView = null;
    private RecyclerView.Adapter mPopularMoviesAdapter = null;
    private RecyclerView.LayoutManager mPopularMoviesLayoutManager = null;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        mMovieListRecyclerView = (RecyclerView) view.findViewById(R.id.popular_movies_recycler_view);

        initRecyclerView(view.getContext());
        initWebService();

        return view;
    }

    private void initWebService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(TmdbWebServiceContract.BASE_URL)
                .setRequestInterceptor(this)
                .build();
        final TmdbService tmdbService = restAdapter.create(TmdbService.class);
        tmdbService.listPopularMovies(this);
    }

    private void setRecyclerViewAdapter(List<PopularMovie> popularMovieList) {
        //TODO: Set Adapter
        mPopularMoviesAdapter = new PopularMoviesAdapter(getActivity(), popularMovieList);
        mMovieListRecyclerView.setAdapter(mPopularMoviesAdapter);
    }

    private void initRecyclerView(Context context) {
        mMovieListRecyclerView.setHasFixedSize(true);
        mPopularMoviesLayoutManager = new GridLayoutManager(context, 3);
        mMovieListRecyclerView.setLayoutManager(mPopularMoviesLayoutManager);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(TmdbWebServiceContract.QUERY_PARAM_API_KEY, TmdbWebServiceContract.API_KEY);
    }

    @Override
    public void success(PopularMoviesPage popularMoviesPage, Response response) {
        setRecyclerViewAdapter(popularMoviesPage.getPopularMovieList());
    }

    @Override
    public void failure(RetrofitError error) {
        //TODO respond properly to errors
    }
}
