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
@Table(name = "rating")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Rating.FIND_ALL, query = "SELECT r FROM Rating r")
    , @NamedQuery(name = "Rating.findByRRatingId", query = "SELECT r FROM Rating r WHERE r.rRatingId = :rRatingId")
    , @NamedQuery(name = "Rating.findByRRatingView", query = "SELECT r FROM Rating r WHERE r.rRatingView = :rRatingView")
    , @NamedQuery(name = "Rating.findByRRatingPath", query = "SELECT r FROM Rating r WHERE r.rRatingPath = :rRatingPath")
    , @NamedQuery(name = "Rating.findByRRatingAvailability", query = "SELECT r FROM Rating r WHERE r.rRatingAvailability = :rRatingAvailability")
    , @NamedQuery(name = "Rating.findByRRatingMarkup", query = "SELECT r FROM Rating r WHERE r.rRatingMarkup = :rRatingMarkup")
    , @NamedQuery(name = "Rating.findByRRatingTotal", query = "SELECT r FROM Rating r WHERE r.rRatingTotal = :rRatingTotal")
    , @NamedQuery(name = "Rating.findByRRatingComment", query = "SELECT r FROM Rating r WHERE r.rRatingComment = :rRatingComment")})
public class Rating implements Serializable {

    public static final String FIND_ALL = "Rating.findAll";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "RRatingId")
    private Integer rRatingId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RRatingView")
    private Float rRatingView;
    @Column(name = "RRatingPath")
    private Float rRatingPath;
    @Column(name = "RRatingAvailability")
    private Float rRatingAvailability;
    @Column(name = "RRatingMarkup")
    private Float rRatingMarkup;
    @Column(name = "RRatingTotal")
    private Float rRatingTotal;
    @Size(max = 300)
    @Column(name = "RRatingComment")
    private String rRatingComment;
    @JoinColumn(name = "MId", referencedColumnName = "MId")
    @ManyToOne
    private Mountain mId;

    public Rating() {
    }

    public Rating(Integer rRatingId) {
        this.rRatingId = rRatingId;
    }

    public Integer getRRatingId() {
        return rRatingId;
    }

    public void setRRatingId(Integer rRatingId) {
        this.rRatingId = rRatingId;
    }

    public Float getRRatingView() {
        return rRatingView;
    }

    public void setRRatingView(Float rRatingView) {
        this.rRatingView = rRatingView;
    }

    public Float getRRatingPath() {
        return rRatingPath;
    }

    public void setRRatingPath(Float rRatingPath) {
        this.rRatingPath = rRatingPath;
    }

    public Float getRRatingAvailability() {
        return rRatingAvailability;
    }

    public void setRRatingAvailability(Float rRatingAvailability) {
        this.rRatingAvailability = rRatingAvailability;
    }

    public Float getRRatingMarkup() {
        return rRatingMarkup;
    }

    public void setRRatingMarkup(Float rRatingMarkup) {
        this.rRatingMarkup = rRatingMarkup;
    }

    public Float getRRatingTotal() {
        return rRatingTotal;
    }

    public void setRRatingTotal(Float rRatingTotal) {
        this.rRatingTotal = rRatingTotal;
    }

    public String getRRatingComment() {
        return rRatingComment;
    }

    public void setRRatingComment(String rRatingComment) {
        this.rRatingComment = rRatingComment;
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
        hash += (rRatingId != null ? rRatingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.rRatingId == null && other.rRatingId != null) || (this.rRatingId != null && !this.rRatingId.equals(other.rRatingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.serverutogopp.Rating[ rRatingId=" + rRatingId + " ]";
    }
    
}
