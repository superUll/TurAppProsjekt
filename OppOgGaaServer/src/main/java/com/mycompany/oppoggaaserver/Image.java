/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oppoggaaserver;

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
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIImageId", query = "SELECT i FROM Image i WHERE i.iImageId = :iImageId")
    , @NamedQuery(name = "Image.findByIImage", query = "SELECT i FROM Image i WHERE i.iImage = :iImage")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IImage_Id")
    private Integer iImageId;
    @Size(max = 300)
    @Column(name = "IImage")
    private String iImage;
    @OneToMany(mappedBy = "iImageId")
    private Collection<Mountain> mountainCollection;

    public Image() {
    }

    public Image(Integer iImageId) {
        this.iImageId = iImageId;
    }

    public Integer getIImageId() {
        return iImageId;
    }

    public void setIImageId(Integer iImageId) {
        this.iImageId = iImageId;
    }

    public String getIImage() {
        return iImage;
    }

    public void setIImage(String iImage) {
        this.iImage = iImage;
    }

    @XmlTransient
    public Collection<Mountain> getMountainCollection() {
        return mountainCollection;
    }

    public void setMountainCollection(Collection<Mountain> mountainCollection) {
        this.mountainCollection = mountainCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iImageId != null ? iImageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.iImageId == null && other.iImageId != null) || (this.iImageId != null && !this.iImageId.equals(other.iImageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.oppoggaaserver.Image[ iImageId=" + iImageId + " ]";
    }
    
}
