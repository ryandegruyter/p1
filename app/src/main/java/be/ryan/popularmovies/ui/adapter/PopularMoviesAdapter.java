package be.ryan.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.PopularMovie;
import be.ryan.popularmovies.tmdb.TmdbWebServiceContract;

/**
 * Created by Ryan on 29/08/2015.
 */
public class PopularMoviesAdapter extends android.support.v7.widget.RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder> {

    private final Context mContext;
    private final List<PopularMovie> mPopularMoviesList;

    public PopularMoviesAdapter(Context context, List<PopularMovie> popularMovieList) {
        mContext = context;
        mPopularMoviesList = popularMovieList;
    }

    public static class PopularMoviesViewHolder extends RecyclerView.ViewHolder {

        ImageView poster = null;

        public PopularMoviesViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }

        void bindData(PopularMovie movie, Context mContext, int position) {
            final String posterImgPath = movie.getPosterImgPath();
            String uri = TmdbWebServiceContract.BASE_IMG_URL + "/" + TmdbWebServiceContract.IMG_SIZE_500 + "/" + posterImgPath;
            //TODO: In the future cache the image and get it locally, or does picasso do that automatically?
            //TODO: Set Error Picture
            //TODO: PlaceholdeR?
            Picasso.with(mContext).load(uri).into(poster);
        }
    }
    @Override
    public PopularMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item = inflater.inflate(R.layout.popular_movies_item, parent, false);
        return new PopularMoviesViewHolder(item);
    }

    @Override
    public void onBindViewHolder(PopularMoviesViewHolder viewHolder, int position) {
        viewHolder.bindData(mPopularMoviesList.get(position),mContext, position);
    }

    @Override
    public int getItemCount() {
        return mPopularMoviesList.size();
    }
}
