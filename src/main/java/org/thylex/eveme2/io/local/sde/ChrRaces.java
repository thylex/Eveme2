/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.local.sde;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "chrRaces")
@NamedQueries({
    @NamedQuery(name = "ChrRaces.findAll", query = "SELECT c FROM ChrRaces c")})
public class ChrRaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "raceID")
    private Integer raceID;
    @Column(name = "raceName")
    private String raceName;
    @Column(name = "description")
    private String description;
    @Column(name = "iconID")
    private Integer iconID;
    @Column(name = "shortDescription")
    private String shortDescription;

    public ChrRaces() {
    }

    public ChrRaces(Integer raceID) {
        this.raceID = raceID;
    }

    public Integer getRaceID() {
        return raceID;
    }

    public void setRaceID(Integer raceID) {
        this.raceID = raceID;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raceID != null ? raceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChrRaces)) {
            return false;
        }
        ChrRaces other = (ChrRaces) object;
        if ((this.raceID == null && other.raceID != null) || (this.raceID != null && !this.raceID.equals(other.raceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.ChrRaces[ raceID=" + raceID + " ]";
    }
    
}
