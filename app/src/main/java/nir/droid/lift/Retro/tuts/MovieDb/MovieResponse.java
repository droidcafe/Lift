package nir.droid.lift.Retro.tuts.MovieDb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by droidcafe on 10/9/2016.
 */
public class MovieResponse {

    private String overview;
    private String release_date;
    @SerializedName(value = "title",alternate = {"name"})
    private String title;
    @SerializedName("poster_path")
    private String poster;
    private float popularity;
    private int votes;
    private int vote_count;
    private float vote_average;

    @SerializedName("results")
    private List<MovieResponse> responses;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public List<MovieResponse> getResults(){
        return responses;
    }

    public String getPoster() {
        return poster;
    }
//    private int ;
//    private int ;
//    private int ;


}

