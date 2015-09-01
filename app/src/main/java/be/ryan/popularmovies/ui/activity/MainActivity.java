package be.ryan.popularmovies.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.PopularMovie;
import be.ryan.popularmovies.ui.fragment.DetailMovieFragment;
import be.ryan.popularmovies.ui.fragment.MovieListFragment;
import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity  {

    public String TAG_MOVIE_LIST_FRAGMENT = "movie_list";
    public String TAG_MOVIE_DETAIL_FRAGMENT = "detail_movie";

    RelativeLayout mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: handle orientation change to retain fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContainerView = (RelativeLayout) findViewById(R.id.main_container);

        getSupportFragmentManager().beginTransaction().replace(mContainerView.getId(), new MovieListFragment(), TAG_MOVIE_LIST_FRAGMENT).commit();
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
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(PopularMovie movie) {
        getSupportFragmentManager().beginTransaction()
                .replace(mContainerView.getId(), DetailMovieFragment.newInstance(movie), TAG_MOVIE_DETAIL_FRAGMENT)
                .addToBackStack(null)
                .commit();

    }
}
