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
@Table(name = "industryActivityRaces")
@NamedQueries({
    @NamedQuery(name = "IndustryActivityRaces.findAll", query = "SELECT i FROM IndustryActivityRaces i")})
public class IndustryActivityRaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "activityID")
    private Integer activityID;
    @Column(name = "productTypeID")
    private Integer productTypeID;
    @Column(name = "raceID")
    private Integer raceID;
    @Id
    @Column(name = "indActRaceID")
    private Integer indActRaceID;

    public IndustryActivityRaces() {
    }

    public IndustryActivityRaces(Integer indActRaceID) {
        this.indActRaceID = indActRaceID;
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

    public Integer getRaceID() {
        return raceID;
    }

    public void setRaceID(Integer raceID) {
        this.raceID = raceID;
    }

    public Integer getIndActRaceID() {
        return indActRaceID;
    }

    public void setIndActRaceID(Integer indActRaceID) {
        this.indActRaceID = indActRaceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indActRaceID != null ? indActRaceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivityRaces)) {
            return false;
        }
        IndustryActivityRaces other = (IndustryActivityRaces) object;
        if ((this.indActRaceID == null && other.indActRaceID != null) || (this.indActRaceID != null && !this.indActRaceID.equals(other.indActRaceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivityRaces[ indActRaceID=" + indActRaceID + " ]";
    }
    
}
