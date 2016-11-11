package com.example.prosjektfjell.oppogg;

import java.io.Serializable;

/**
 * Created by helt0 on 11.11.2016.
 */

public class DetailAttributes implements Serializable {

    private String MThumbnail;
    private String MId;
    private String MImage;
    private String MMunicipality;
    private String MName;
    private String MPath;
    private String MHeight;
    private String MAltitude;
    private String MLength;
    private String MTimespan;
    private String MTerrain;
    private String MDifficulty;

    public DetailAttributes(){

    }

    public DetailAttributes(String MThumbnail,String MId, String MImage, String MMunicipality, String MName, String MPath, String MHeight, String MAltitude, String MLength, String MTimespan, String MTerrain, String MDifficulty){
        this.MThumbnail =MThumbnail;
        this.MId = MId;
        this.MImage = MImage;
        this.MMunicipality = MMunicipality;
        this.MName = MName;
        this.MPath = MPath;
        this.MHeight = MHeight;
        this.MAltitude = MAltitude;
        this.MLength = MLength;
        this.MTimespan = MTimespan;
        this.MTerrain = MTerrain;
        this.MDifficulty = MDifficulty;

    }

    public String getMThumbnails(){
        return MThumbnail;
    }
    public String setMThumbnails(String MThumbnail){
        return this.MThumbnail = MThumbnail;
    }

    public String getMId(){ return MId;}
    public String setMId(String MId){ return  this.MId = MId;}

    public String getMImage(){
        return MImage;
    }
    public String setMImage(String MImage){
        return this.MImage = MImage;
    }

    public String getMMunicipality(){
        return MMunicipality;
    }
    public String setMMunicipality(String MMunicipality){
        return this.MMunicipality = MMunicipality;
    }

    public String getMName(){
        return MName;
    }
    public String setMName(String MName){
        return this.MName = MName;
    }

    public String getMPath(){
        return MPath;
    }
    public String setMPath(String MPath){
        return this.MPath = MPath;
    }

    public String getMHeight(){
        return MHeight;
    }
    public String setMHeigth(String MHeight){
        return this.MHeight = MHeight;
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
