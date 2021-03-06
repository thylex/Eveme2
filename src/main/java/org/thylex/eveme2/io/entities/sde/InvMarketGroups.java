/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.sde;

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
@Table(name = "invMarketGroups")
@NamedQueries({
    @NamedQuery(name = "InvMarketGroups.findAll", query = "SELECT i FROM InvMarketGroups i"),
    @NamedQuery(name = "InvMarketGroups.findByMarketGroupID", query = "SELECT i FROM InvMarketGroups i WHERE i.marketGroupID = :marketGroupID"),
    @NamedQuery(name = "InvMarketGroups.findByParentGroupID", query = "SELECT i FROM InvMarketGroups i WHERE i.parentGroupID = :parentGroupID"),
    @NamedQuery(name = "InvMarketGroups.findByMarketGroupName", query = "SELECT i FROM InvMarketGroups i WHERE i.marketGroupName = :marketGroupName"),
    @NamedQuery(name = "InvMarketGroups.findByDescription", query = "SELECT i FROM InvMarketGroups i WHERE i.description = :description"),
    @NamedQuery(name = "InvMarketGroups.findByIconID", query = "SELECT i FROM InvMarketGroups i WHERE i.iconID = :iconID"),
    @NamedQuery(name = "InvMarketGroups.findByHasTypes", query = "SELECT i FROM InvMarketGroups i WHERE i.hasTypes = :hasTypes")})
public class InvMarketGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "marketGroupID")
    private Integer marketGroupID;
    @Column(name = "parentGroupID")
    private Integer parentGroupID;
    @Column(name = "marketGroupName")
    private String marketGroupName;
    @Column(name = "description")
    private String description;
    @Column(name = "iconID")
    private Integer iconID;
    @Column(name = "hasTypes")
    private Integer hasTypes;

    public InvMarketGroups() {
    }

    public InvMarketGroups(Integer marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public Integer getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(Integer marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public Integer getParentGroupID() {
        return parentGroupID;
    }

    public void setParentGroupID(Integer parentGroupID) {
        this.parentGroupID = parentGroupID;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }

    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
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

    public Integer getHasTypes() {
        return hasTypes;
    }

    public void setHasTypes(Integer hasTypes) {
        this.hasTypes = hasTypes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (marketGroupID != null ? marketGroupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvMarketGroups)) {
            return false;
        }
        InvMarketGroups other = (InvMarketGroups) object;
        if ((this.marketGroupID == null && other.marketGroupID != null) || (this.marketGroupID != null && !this.marketGroupID.equals(other.marketGroupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.io.entities.sde.InvMarketGroups[ marketGroupID=" + marketGroupID + " ]";
    }
    
}
