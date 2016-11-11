package com.example.prosjektfjell.oppogg.gallery.model;

import java.io.Serializable;


public class Image implements Serializable{

    private String IName;
    private String IPath;
    private String MThumbnail;

    public Image(){

    }

    public Image(String IName, String IPath, String MThumbnail){
        this.IName = IName;
        this.IPath = IPath;
        this.MThumbnail = MThumbnail;
    }

    public String getName(){
        return IName;
    }
    public String setName(String IName){
        return this.IName = IName;
    }

    public String getPath(){
        return IPath;
    }
    public String setPath(String IPath){
        return this.IPath = IPath;
    }



}
