package be.ryan.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.PopularMovie;

/**
 * Created by Ryan on 29/08/2015.
 */
public class PopularMoviesAdapter extends android.support.v7.widget.RecyclerView.Adapter<MovieHolder> {

    private final Context mContext;
    private final List<PopularMovie> mPopularMoviesList;

    public PopularMoviesAdapter(Context context, List<PopularMovie> popularMovieList) {
        mContext = context;
        mPopularMoviesList = popularMovieList;
    }
    
    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item = inflater.inflate(R.layout.popular_movies_item, parent, false);
        return new MovieHolder(item);
    }

    @Override
    public void onBindViewHolder(MovieHolder viewHolder, int position) {
        viewHolder.bindData(mPopularMoviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPopularMoviesList.size();
    }
}
