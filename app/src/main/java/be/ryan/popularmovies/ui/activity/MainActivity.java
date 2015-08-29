package be.ryan.popularmovies.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.PopularMoviesPage;
import be.ryan.popularmovies.tmdb.TmdbService;
import be.ryan.popularmovies.tmdb.TmdbWebServiceContract;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements RequestInterceptor, Callback<PopularMoviesPage> {

    private TextView mDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(TmdbWebServiceContract.BASE_URL)
                .setRequestInterceptor(this)
                .build();
        final TmdbService tmdbService = restAdapter.create(TmdbService.class);
        tmdbService.listPopularMovies(this);
        mDescriptionView = (TextView) findViewById(R.id.Description);
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
    }

    @Override
    public void failure(RetrofitError error) {
        //TODO respond properly to errors

    }
}
