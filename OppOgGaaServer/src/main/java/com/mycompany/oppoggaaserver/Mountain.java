/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oppoggaaserver;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vidar
 */
@Entity
@Table(name = "mountain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Mountain.ALL_MOUNTAINS, query = "SELECT m FROM Mountain m")
    , @NamedQuery(name = "Mountain.findByMTId", query = "SELECT m FROM Mountain m WHERE m.mTId = :mTId")
    , @NamedQuery(name = "Mountain.findByMImage", query = "SELECT m FROM Mountain m WHERE m.mImage = :mImage")
    , @NamedQuery(name = "Mountain.findByMName", query = "SELECT m FROM Mountain m WHERE m.mName = :mName")
    , @NamedQuery(name = "Mountain.findByMMunicipality", query = "SELECT m FROM Mountain m WHERE m.mMunicipality = :mMunicipality")
    , @NamedQuery(name = "Mountain.findByMHeight", query = "SELECT m FROM Mountain m WHERE m.mHeight = :mHeight")
    , @NamedQuery(name = "Mountain.findByMAltitude", query = "SELECT m FROM Mountain m WHERE m.mAltitude = :mAltitude")
    , @NamedQuery(name = "Mountain.findByMLenght", query = "SELECT m FROM Mountain m WHERE m.mLenght = :mLenght")
    , @NamedQuery(name = "Mountain.findByMTimeSpan", query = "SELECT m FROM Mountain m WHERE m.mTimeSpan = :mTimeSpan")
    , @NamedQuery(name = "Mountain.findByMPath", query = "SELECT m FROM Mountain m WHERE m.mPath = :mPath")
    , @NamedQuery(name = "Mountain.findByMTerrain", query = "SELECT m FROM Mountain m WHERE m.mTerrain = :mTerrain")
    , @NamedQuery(name = "Mountain.findByMDifficulty", query = "SELECT m FROM Mountain m WHERE m.mDifficulty = :mDifficulty")})
public class Mountain implements Serializable {

    public static final String ALL_MOUNTAINS = "Mountain.findAll";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MT_Id")
    private Integer mTId;
    @Lob
    @Column(name = "MThumbnail")
    private byte[] mThumbnail;
    @Size(max = 100)
    @Column(name = "MImage")
    private String mImage;
    @Size(max = 45)
    @Column(name = "MName")
    private String mName;
    @Size(max = 45)
    @Column(name = "MMunicipality")
    private String mMunicipality;
    @Size(max = 45)
    @Column(name = "MHeight")
    private String mHeight;
    @Size(max = 45)
    @Column(name = "MAltitude")
    private String mAltitude;
    @Size(max = 45)
    @Column(name = "MLenght")
    private String mLenght;
    @Size(max = 45)
    @Column(name = "MTimeSpan")
    private String mTimeSpan;
    @Size(max = 45)
    @Column(name = "MPath")
    private String mPath;
    @Size(max = 45)
    @Column(name = "MTerrain")
    private String mTerrain;
    @Size(max = 45)
    @Column(name = "MDifficulty")
    private String mDifficulty;
    @JoinColumn(name = "IImage_Id", referencedColumnName = "IImage_Id")
    @ManyToOne
    private Image iImageId;
    @JoinColumn(name = "RRating_Id", referencedColumnName = "RRating_Id")
    @ManyToOne
    private Raiting rRatingId;

    public Mountain() {
    }

    public Mountain(Integer mTId) {
        this.mTId = mTId;
    }

    public Integer getMTId() {
        return mTId;
    }

    public void setMTId(Integer mTId) {
        this.mTId = mTId;
    }

    public byte[] getMThumbnail() {
        return mThumbnail;
    }

    public void setMThumbnail(byte[] mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getMImage() {
        return mImage;
    }

    public void setMImage(String mImage) {
        this.mImage = mImage;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public String getMMunicipality() {
        return mMunicipality;
    }

    public void setMMunicipality(String mMunicipality) {
        this.mMunicipality = mMunicipality;
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

    public String getMPath() {
        return mPath;
    }

    public void setMPath(String mPath) {
        this.mPath = mPath;
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

    public Image getIImageId() {
        return iImageId;
    }

    public void setIImageId(Image iImageId) {
        this.iImageId = iImageId;
    }

    public Raiting getRRatingId() {
        return rRatingId;
    }

    public void setRRatingId(Raiting rRatingId) {
        this.rRatingId = rRatingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mTId != null ? mTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mountain)) {
            return false;
        }
        Mountain other = (Mountain) object;
        if ((this.mTId == null && other.mTId != null) || (this.mTId != null && !this.mTId.equals(other.mTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.oppoggaaserver.Mountain[ mTId=" + mTId + " ]";
    }
    
}
