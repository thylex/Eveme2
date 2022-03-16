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
@Table(name = "industryBlueprints")
@NamedQueries({
    @NamedQuery(name = "IndustryBlueprints.findAll", query = "SELECT i FROM IndustryBlueprints i")})
public class IndustryBlueprints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "maxProductionLimit")
    private Integer maxProductionLimit;

    public IndustryBlueprints() {
    }

    public IndustryBlueprints(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getMaxProductionLimit() {
        return maxProductionLimit;
    }

    public void setMaxProductionLimit(Integer maxProductionLimit) {
        this.maxProductionLimit = maxProductionLimit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryBlueprints)) {
            return false;
        }
        IndustryBlueprints other = (IndustryBlueprints) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryBlueprints[ typeID=" + typeID + " ]";
    }
    
}
