/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.db.sde;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "industryActivityProbabilities")
@NamedQueries({
    @NamedQuery(name = "IndustryActivityProbabilities.findAll", query = "SELECT i FROM IndustryActivityProbabilities i")})
public class IndustryActivityProbabilities implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "activityID")
    private Integer activityID;
    @Column(name = "productTypeID")
    private Integer productTypeID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "probability")
    private Double probability;
    @Id
    @Column(name = "indActProbID")
    private Integer indActProbID;

    public IndustryActivityProbabilities() {
    }

    public IndustryActivityProbabilities(Integer indActProbID) {
        this.indActProbID = indActProbID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public Integer getProductTypeID() {
        return productTypeID;
    }

    public void setProductTypeID(Integer productTypeID) {
        this.productTypeID = productTypeID;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Integer getIndActProbID() {
        return indActProbID;
    }

    public void setIndActProbID(Integer indActProbID) {
        this.indActProbID = indActProbID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indActProbID != null ? indActProbID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivityProbabilities)) {
            return false;
        }
        IndustryActivityProbabilities other = (IndustryActivityProbabilities) object;
        if ((this.indActProbID == null && other.indActProbID != null) || (this.indActProbID != null && !this.indActProbID.equals(other.indActProbID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivityProbabilities[ indActProbID=" + indActProbID + " ]";
    }
    
}
