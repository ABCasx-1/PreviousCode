package com.example.springboot1.Bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "enterprise")
public class Enterprise {
    private String category;
    private String[] play;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getPlay() {
        return play;
    }

    public void setPlay(String[] play) {
        this.play = play;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "category='" + category + '\'' +
                ", play=" + Arrays.toString(play) +
                '}';
    }
}
