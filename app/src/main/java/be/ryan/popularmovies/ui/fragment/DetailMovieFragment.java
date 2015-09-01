package be.ryan.popularmovies.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import be.ryan.popularmovies.R;
import be.ryan.popularmovies.domain.TmdbMovie;
import be.ryan.popularmovies.tmdb.TmdbWebServiceContract;

public class DetailMovieFragment extends android.support.v4.app.Fragment {

    private static final String ARG_MOVIE = "movie";

    private TmdbMovie mMovie;
    private ImageView mBackdropView;
    private TextView mTitleView;
    private TextView mReleaseDateView;
    private RatingBar mVoteAverageView;
    private TextView mSynopsisView;

    public static DetailMovieFragment newInstance(TmdbMovie tmdbMovie) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, Parcels.wrap(tmdbMovie));
        fragment.setArguments(args);
        return fragment;
    }

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = Parcels.unwrap(getArguments().getParcelable(ARG_MOVIE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        mBackdropView = (ImageView) view.findViewById(R.id.movie_detail_backdrop);
        String uri = TmdbWebServiceContract.BASE_BACKDROP_IMG_URL + mMovie.getBackdropImgPath();
        Picasso.with(view.getContext()).load(uri).into(mBackdropView);

        mTitleView = (TextView) view.findViewById(R.id.movie_title);
        mTitleView.setText(mMovie.getTitle());

        mReleaseDateView = (TextView) view.findViewById(R.id.release_date);
        mReleaseDateView.setText(mMovie.getReleaseDate());

        mVoteAverageView = (RatingBar) view.findViewById(R.id.vote_average);
        mVoteAverageView.setRating((float) mMovie.getVoteAverage());

        mSynopsisView = (TextView) view.findViewById(R.id.synopsis);
        mSynopsisView.setText(mMovie.getOverView());

        return view;
    }
}
