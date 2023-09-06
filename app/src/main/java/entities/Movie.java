package entities;

import java.io.Serializable;

public class Movie implements Serializable {
    private String id;
    private String name;
    private String imageURL;
    private float evaluation;
    private String synopsis;
    private String duration;
    private String rating;
    private String type;
    private String price;
    private String npOrder;

    public Movie() {
    }

    public String getNpOrder() {
        return npOrder;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getId() {
        return id;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }

}
