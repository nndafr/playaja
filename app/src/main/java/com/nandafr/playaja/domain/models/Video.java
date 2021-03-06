package com.nandafr.playaja.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nandafr.playaja.data.movie.model.VideoDataClass;
import com.nandafr.playaja.data.movie.model.VideoResultDataClass;

import java.util.List;

public class Video {

    private Integer id;
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
