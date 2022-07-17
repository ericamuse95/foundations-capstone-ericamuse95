package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameDTO {
    @JsonProperty("canon")
    private boolean canon;
    @JsonProperty("aired")
    private String aired;

    public boolean getCanon() {
        return canon;
    }

    public void setCanon(boolean canon) {
        this.canon = canon;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    @Override
    public String toString() {
        return "Game{" +
                "canon=" + canon +
                ", aired='" + aired + '\'' +
                '}';
    }
}