/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.sde;

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
@Table(name = "industryActivityProducts")
@NamedQueries({
    @NamedQuery(name = "IndustryActivityProducts.findAll", query = "SELECT i FROM IndustryActivityProducts i")})
public class IndustryActivityProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "activityID")
    private Integer activityID;
    @Column(name = "productTypeID")
    private Integer productTypeID;
    @Column(name = "quantity")
    private Integer quantity;
    @Id
    @Column(name = "indActProdID")
    private Integer indActProdID;

    public IndustryActivityProducts() {
    }

    public IndustryActivityProducts(Integer indActProdID) {
        this.indActProdID = indActProdID;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIndActProdID() {
        return indActProdID;
    }

    public void setIndActProdID(Integer indActProdID) {
        this.indActProdID = indActProdID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indActProdID != null ? indActProdID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivityProducts)) {
            return false;
        }
        IndustryActivityProducts other = (IndustryActivityProducts) object;
        if ((this.indActProdID == null && other.indActProdID != null) || (this.indActProdID != null && !this.indActProdID.equals(other.indActProdID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivityProducts[ indActProdID=" + indActProdID + " ]";
    }
    
}
