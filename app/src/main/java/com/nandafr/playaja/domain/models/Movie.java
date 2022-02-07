package com.nandafr.playaja.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandafr.playaja.data.movie.model.MovieDataClass;
import com.nandafr.playaja.data.movie.model.MovieResultDataClass;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private Integer page;
    private List<MovieResultDataClass> results = null;
    private Integer totalPages;
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
