package com.example.roadtomadagascar.Domains;

import java.io.Serializable;

public class PlaceDomain implements Serializable {
    private String id;
    private String idCategorie;
    private String title;
    private String location;
    private String description;
    private int distance;
    private boolean guide;
    private double score;
    private String pic;
    private boolean isPopulaire;
    private String url;


    public PlaceDomain(String id, String idCategorie, String title, String location, String description, int distance, boolean guide, double score, String pic, boolean isPopulaire, String url) {
        this.id = id;
        this.idCategorie = idCategorie;
        this.title = title;
        this.location = location;
        this.description = description;
        this.distance = distance;
        this.guide = guide;
        this.score = score;
        this.pic = pic;
        this.isPopulaire = isPopulaire;
        this.url = url;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isGuide() {
        return guide;
    }

    public void setGuide(boolean guide) {
        this.guide = guide;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "PlaceDomain{" +
                "id='" + id + '\'' +
                ", idCategorie='" + idCategorie + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", distance=" + distance +
                ", guide=" + guide +
                ", score=" + score +
                ", pic='" + pic + '\'' +
                ", isPopulaire=" + isPopulaire +
                '}';
    }

    public boolean isPopulaire() {
        return isPopulaire;
    }

    public void setPopulaire(boolean populaire) {
        isPopulaire = populaire;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
