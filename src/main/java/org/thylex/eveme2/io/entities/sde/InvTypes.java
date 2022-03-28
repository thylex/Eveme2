/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.sde;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Integer typeID;
    @OneToMany(mappedBy = "invType", fetch = FetchType.LAZY)
    private List<InvItems> invItems;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupID")
    private InvGroups invGroup;
    //private Integer groupID;
    private String typeName;
    private String description;
    private Float mass;
    private Float volume;
    private Float capacity;
    private Integer portionSize;
    private Integer raceID;
    private Float basePrice;
    private Boolean published;
    private Integer marketGroupID;
    private Integer iconID;
    private Integer soundID;
    private Integer graphicID;

    public List<InvItems> getInvItems() {
        return invItems;
    }

    public void setInvItems(List<InvItems> invItems) {
        this.invItems = invItems;
    }

    public InvGroups getInvGroup() {
        return invGroup;
    }

    public void setInvGroup(InvGroups invGroup) {
        this.invGroup = invGroup;
    }

//    public Integer getGroupID() {
//        return groupID;
//    }
//
//    public void setGroupID(Integer groupID) {
//        this.groupID = groupID;
//    }

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

    public Integer getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(Integer portionSize) {
        this.portionSize = portionSize;
    }

    public Integer getRaceID() {
        return raceID;
    }

    public void setRaceID(Integer raceID) {
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

    public Integer getMarketGroupID() {
        return marketGroupID;
    }

    public void setMarketGroupID(Integer marketGroupID) {
        this.marketGroupID = marketGroupID;
    }

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public Integer getSoundID() {
        return soundID;
    }

    public void setSoundID(Integer soundID) {
        this.soundID = soundID;
    }

    public Integer getGraphicID() {
        return graphicID;
    }

    public void setGraphicID(Integer graphicID) {
        this.graphicID = graphicID;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
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
