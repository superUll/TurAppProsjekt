package com.example.prosjektfjell.oppogg.model;

/**
 * Created by oleandreheggdal on 12.11.2016.
 */

import java.util.ArrayList;

public class Mountain {
    private String name, thumbnailUrl;
    private String muni, height;
    private String MId;
    private String MImage;
    private String MPath;
    private String MAltitude;
    private String MLength;
    private String MTimespan;
    private String MTerrain;
    private String MDifficulty;


    public Mountain() {
    }

    public Mountain(String name, String muni,String height, String thumbnailUrl,String MId, String MImage, String MPath, String MAltitude, String MLength,
                    String MTimespan, String MTerrain, String MDifficulty) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.muni = muni;
        this.height = height;
        this.MId = MId;
        this.MImage = MImage;
        this.MPath = MPath;
        this.MAltitude = MAltitude;
        this.MLength = MLength;
        this.MTimespan = MTimespan;
        this.MTerrain = MTerrain;
        this.MDifficulty = MDifficulty;

    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getMuni() {
        return muni;
    }

    public void setMuni(String muni) {
        this.muni = muni;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() { return MId;}
    public String setId(String MId){ return  this.MId = MId;}

    public String getMImage(){
        return MImage;
    }
    public String setMImage(String MImage){
        return this.MImage = MImage;
    }

    public String getMPath(){
        return MPath;
    }
    public String setMPath(String MPath){
        return this.MPath = MPath;
    }

    public String getMAltitude(){
        return MAltitude;
    }
    public String setMAltitide(String MAltitude){
        return this.MAltitude = MAltitude;
    }

    public String getMLength(){
        return MLength;
    }
    public String setMLength(String MLength){
        return this.MLength = MLength;
    }

    public String getMTimespan(){
        return MTimespan;
    }
    public String setMTimespan(String MTimespan){
        return this.MTimespan = MTimespan;
    }

    public String getMTerrain(){
        return MTerrain;
    }
    public String setMTerrain(String MTerrain){
        return this.MTerrain = MTerrain;
    }

    public String getMDifficulty(){
        return MDifficulty;
    }
    public String setMDifficulty(String MDifficulty){
        return this.MDifficulty = MDifficulty;
    }






}
