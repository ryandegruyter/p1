package be.ryan.popularmovies.domain;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan on 29/08/2015.
 */
@Parcel
public class PopularMovie {
    @SerializedName("adult")
    private boolean isAdult;

    @SerializedName("backdrop_path")
    private String backdropImgPath;

    @SerializedName("genre_ids")
    private int[] genreIds;

    private int id;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("overview")
    private String overView;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterImgPath;

    @SerializedName("popularity")
    private Double popularity;

    private String title;

    @SerializedName("video")
    private boolean isVideo;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    public boolean isAdult() {
        return isAdult;
    }

    public String getBackdropImgPath() {
        return backdropImgPath;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterImgPath() {
        return posterImgPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "PopularMovie{" +
                "isAdult=" + isAdult +
                ", backdropImgPath='" + backdropImgPath + '\'' +
                ", genreIds=" + Arrays.toString(genreIds) +
                ", id=" + id +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overView='" + overView + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterImgPath='" + posterImgPath + '\'' +
                ", popularity=" + popularity +
                ", title='" + title + '\'' +
                ", isVideo=" + isVideo +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                '}';
    }
}
