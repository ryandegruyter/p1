package be.ryan.popularmovies.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainActivity extends Activity implements RequestInterceptor, Callback<PopularMoviesPage> {

    private RecyclerView mPopularMoviesRecyclerView;
    private RecyclerView.Adapter mPopularMoviesAdapter;
    private RecyclerView.LayoutManager mPopularMoviesLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPopularMoviesRecyclerView = (RecyclerView) findViewById(R.id.popular_movies_recycler_view);
        initRecyclerView();

        initWebService();
    }

    private void initRecyclerView() {
        mPopularMoviesRecyclerView.setHasFixedSize(true);
        mPopularMoviesLayoutManager = new GridLayoutManager(this, 3);
        mPopularMoviesRecyclerView.setLayoutManager(mPopularMoviesLayoutManager);
    }

    private void setRecyclerViewAdapter(List<PopularMovie> popularMovieList){
        //TODO: Set Adapter
        mPopularMoviesAdapter = new PopularMoviesAdapter(this,popularMovieList);
        mPopularMoviesRecyclerView.setAdapter(mPopularMoviesAdapter);
    }

    private void initWebService() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(TmdbWebServiceContract.BASE_URL)
                .setRequestInterceptor(this)
                .build();
        final TmdbService tmdbService = restAdapter.create(TmdbService.class);
        tmdbService.listPopularMovies(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(TmdbWebServiceContract.QUERY_PARAM_API_KEY, TmdbWebServiceContract.API_KEY);
    }

    @Override
    public void success(PopularMoviesPage popularMoviesPage, Response response) {
        //TODO: populate grid/recycler view with popular movies
        setRecyclerViewAdapter(popularMoviesPage.getPopularMovieList());

    }

    @Override
    public void failure(RetrofitError error) {
        //TODO respond properly to errors

    }
}
