package com.sandbox.websitelibrary.WebSiteLibrary.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

@Entity
public class Website {

    // No-argument constructor required for Jackson deserialization
    public Website() {
    }

    public Website(Long id, String description, String url, String comment, Integer rating) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.comment = comment;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @Size(min = 1, max = 255)
    private String url;

    @Size(max = 500)
    private String comment;

    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;




    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
