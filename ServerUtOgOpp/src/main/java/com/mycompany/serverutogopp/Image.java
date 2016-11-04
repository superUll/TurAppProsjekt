/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverutogopp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "image")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i")
    , @NamedQuery(name = "Image.findByIImageId", query = "SELECT i FROM Image i WHERE i.iImageId = :iImageId")
    , @NamedQuery(name = "Image.findByIName", query = "SELECT i FROM Image i WHERE i.iName = :iName")
    , @NamedQuery(name = "Image.findByIPath", query = "SELECT i FROM Image i WHERE i.iPath = :iPath")
    , @NamedQuery(name = "Image.findByIType", query = "SELECT i FROM Image i WHERE i.iType = :iType")})
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IImageId")
    private Integer iImageId;
    @Size(max = 100)
    @Column(name = "IName")
    private String iName;
    @Size(max = 300)
    @Column(name = "IPath")
    private String iPath;
    @Size(max = 100)
    @Column(name = "IType")
    private String iType;
    @JoinColumn(name = "MId", referencedColumnName = "MId")
    @ManyToOne
    private Mountain mId;

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

    public String getIName() {
        return iName;
    }

    public void setIName(String iName) {
        this.iName = iName;
    }

    public String getIPath() {
        return iPath;
    }

    public void setIPath(String iPath) {
        this.iPath = iPath;
    }

    public String getIType() {
        return iType;
    }

    public void setIType(String iType) {
        this.iType = iType;
    }

    public Mountain getMId() {
        return mId;
    }

    public void setMId(Mountain mId) {
        this.mId = mId;
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
        return "com.mycompany.serverutogopp.Image[ iImageId=" + iImageId + " ]";
    }
    
}
