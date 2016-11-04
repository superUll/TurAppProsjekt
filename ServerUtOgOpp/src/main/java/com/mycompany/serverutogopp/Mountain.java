/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverutogopp;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vidar
 */
@Entity
@Table(name = "mountain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Mountain.FIND_ALL_MOUNTAINS, query = "SELECT m FROM Mountain m")
    , @NamedQuery(name = "Mountain.findByMId", query = "SELECT m FROM Mountain m WHERE m.mId = :mId")
    , @NamedQuery(name = "Mountain.findByMThumbnail", query = "SELECT m FROM Mountain m WHERE m.mThumbnail = :mThumbnail")
    , @NamedQuery(name = "Mountain.findByMImage", query = "SELECT m FROM Mountain m WHERE m.mImage = :mImage")
    , @NamedQuery(name = "Mountain.findByMMunicipality", query = "SELECT m FROM Mountain m WHERE m.mMunicipality = :mMunicipality")
    , @NamedQuery(name = "Mountain.findByMName", query = "SELECT m FROM Mountain m WHERE m.mName = :mName")
    , @NamedQuery(name = "Mountain.findByMPath", query = "SELECT m FROM Mountain m WHERE m.mPath = :mPath")
    , @NamedQuery(name = "Mountain.findByMHeight", query = "SELECT m FROM Mountain m WHERE m.mHeight = :mHeight")
    , @NamedQuery(name = "Mountain.findByMAltitude", query = "SELECT m FROM Mountain m WHERE m.mAltitude = :mAltitude")
    , @NamedQuery(name = "Mountain.findByMLenght", query = "SELECT m FROM Mountain m WHERE m.mLenght = :mLenght")
    , @NamedQuery(name = "Mountain.findByMTimeSpan", query = "SELECT m FROM Mountain m WHERE m.mTimeSpan = :mTimeSpan")
    , @NamedQuery(name = "Mountain.findByMTerrain", query = "SELECT m FROM Mountain m WHERE m.mTerrain = :mTerrain")
    , @NamedQuery(name = "Mountain.findByMDifficulty", query = "SELECT m FROM Mountain m WHERE m.mDifficulty = :mDifficulty")})
public class Mountain implements Serializable {

    public static final String FIND_ALL_MOUNTAINS = "Mountain.findAll";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MId")
    private Integer mId;
    @Size(max = 100)
    @Column(name = "MThumbnail")
    private String mThumbnail;
    @Size(max = 100)
    @Column(name = "MImage")
    private String mImage;
    @Size(max = 300)
    @Column(name = "MMunicipality")
    private String mMunicipality;
    @Size(max = 300)
    @Column(name = "MName")
    private String mName;
    @Size(max = 300)
    @Column(name = "MPath")
    private String mPath;
    @Size(max = 300)
    @Column(name = "MHeight")
    private String mHeight;
    @Size(max = 300)
    @Column(name = "MAltitude")
    private String mAltitude;
    @Size(max = 300)
    @Column(name = "MLenght")
    private String mLenght;
    @Size(max = 300)
    @Column(name = "MTimeSpan")
    private String mTimeSpan;
    @Size(max = 100)
    @Column(name = "MTerrain")
    private String mTerrain;
    @Size(max = 300)
    @Column(name = "MDifficulty")
    private String mDifficulty;
    @OneToMany(mappedBy = "mId")
    private Collection<Image> imageCollection;
    @OneToMany(mappedBy = "mId")
    private Collection<Rating> ratingCollection;

    public Mountain() {
    }

    public Mountain(Integer mId) {
        this.mId = mId;
    }

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    public String getMThumbnail() {
        return mThumbnail;
    }

    public void setMThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getMImage() {
        return mImage;
    }

    public void setMImage(String mImage) {
        this.mImage = mImage;
    }

    public String getMMunicipality() {
        return mMunicipality;
    }

    public void setMMunicipality(String mMunicipality) {
        this.mMunicipality = mMunicipality;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMPath() {
        return mPath;
    }

    public void setMPath(String mPath) {
        this.mPath = mPath;
    }

    public String getMHeight() {
        return mHeight;
    }

    public void setMHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public String getMAltitude() {
        return mAltitude;
    }

    public void setMAltitude(String mAltitude) {
        this.mAltitude = mAltitude;
    }

    public String getMLenght() {
        return mLenght;
    }

    public void setMLenght(String mLenght) {
        this.mLenght = mLenght;
    }

    public String getMTimeSpan() {
        return mTimeSpan;
    }

    public void setMTimeSpan(String mTimeSpan) {
        this.mTimeSpan = mTimeSpan;
    }

    public String getMTerrain() {
        return mTerrain;
    }

    public void setMTerrain(String mTerrain) {
        this.mTerrain = mTerrain;
    }

    public String getMDifficulty() {
        return mDifficulty;
    }

    public void setMDifficulty(String mDifficulty) {
        this.mDifficulty = mDifficulty;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mId != null ? mId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mountain)) {
            return false;
        }
        Mountain other = (Mountain) object;
        if ((this.mId == null && other.mId != null) || (this.mId != null && !this.mId.equals(other.mId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.serverutogopp.Mountain[ mId=" + mId + " ]";
    }
    
}
