package com.nandafr.playaja.data.movie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandafr.playaja.domain.models.VideoResult;

import java.util.List;

public class VideoDataClass {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoResultDataClass> results = null;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoResultDataClass> getResults() {
        return results;
    }

    public void setResults(List<VideoResultDataClass> results) {
        this.results = results;
    }
}
