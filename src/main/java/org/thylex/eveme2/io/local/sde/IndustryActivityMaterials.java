/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.local.sde;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "industryActivityMaterials")
@NamedQueries({
    @NamedQuery(name = "IndustryActivityMaterials.findAll", query = "SELECT i FROM IndustryActivityMaterials i")})
public class IndustryActivityMaterials implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "activityID")
    private Integer activityID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "materialTypeID", referencedColumnName = "typeID")
    private InvTypes material;
//    @Column(name = "materialTypeID")
//    private Integer materialTypeID;
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @Column(name = "indActMatID")
    private Integer indActMatID;

    public IndustryActivityMaterials() {
    }

    public IndustryActivityMaterials(Integer indActMatID) {
        this.indActMatID = indActMatID;
    }

    public InvTypes getMaterial() {
        return material;
    }

    public void setMaterial(InvTypes material) {
        this.material = material;
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

//    public Integer getMaterialTypeID() {
//        return materialTypeID;
//    }
//
//    public void setMaterialTypeID(Integer materialTypeID) {
//        this.materialTypeID = materialTypeID;
//    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIndActMatID() {
        return indActMatID;
    }

    public void setIndActMatID(Integer indActMatID) {
        this.indActMatID = indActMatID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indActMatID != null ? indActMatID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivityMaterials)) {
            return false;
        }
        IndustryActivityMaterials other = (IndustryActivityMaterials) object;
        if ((this.indActMatID == null && other.indActMatID != null) || (this.indActMatID != null && !this.indActMatID.equals(other.indActMatID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivityMaterials[ indActMatID=" + indActMatID + " ]";
    }
    
}
