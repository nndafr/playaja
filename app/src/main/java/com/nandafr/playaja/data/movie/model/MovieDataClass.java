package com.nandafr.playaja.data.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandafr.playaja.domain.models.MovieResult;

import java.util.List;

public class MovieDataClass {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieResultDataClass> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieResultDataClass> getResults() {
        return results;
    }

    public void setResults(List<MovieResultDataClass> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
