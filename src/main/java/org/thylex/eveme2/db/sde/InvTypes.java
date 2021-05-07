/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.db.sde;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "invTypes")
public class InvTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "typeID")
    private Long typeID;
    @OneToMany(mappedBy = "invType")
    private Set<InvItems> invItems;
    private Long groupID;
    private String typeName;
    private String description;
    private Float mass;
    private Float volume;
    private Float capacity;
    private Long portionSize;
    private Long raceID;
    private Float basePrice;
    private Boolean published;
    private Long marketGroupID;
    private Long iconID;
    private Long soundID;
    private Long graphicID;

    public Set<InvItems> getInvItems() {
        return invItems;
    }

    public void setInvItems(Set<InvItems> invItems) {
        this.invItems = invItems;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMass() {
        return mass;
    }

    public void setMass(Float mass) {
        this.mass = mass;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Long getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(Long portionSize) {
        this.portionSize = portionSize;
    }

    public Long getRaceID() {
        return raceID;
    }

    public void setRaceID(Long raceID) {
        this.raceID = raceID;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Long getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(Long marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public Long getIconID() {
        return iconID;
    }

    public void setIconID(Long iconID) {
        this.iconID = iconID;
    }

    public Long getSoundID() {
        return soundID;
    }

    public void setSoundID(Long soundID) {
        this.soundID = soundID;
    }

    public Long getGraphicID() {
        return graphicID;
    }

    public void setGraphicID(Long graphicID) {
        this.graphicID = graphicID;
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
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
        if (!(object instanceof InvTypes)) {
            return false;
        }
        InvTypes other = (InvTypes) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.InvTypes[ id=" + typeID + " ]";
    }
    
}
