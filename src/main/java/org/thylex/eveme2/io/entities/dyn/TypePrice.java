/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.dyn;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "TypePrices")
public class TypePrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PriceID")
    private Long priceId;
    private Integer typeID;
    private Float lowBuyPrice;
    private Float highBuyPrice;
    private Float lowSellPrice;
    private Float highSellPrice;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date checkedAt;

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Float getLowBuyPrice() {
        return lowBuyPrice;
    }

    public void setLowBuyPrice(Float lowBuyPrice) {
        this.lowBuyPrice = lowBuyPrice;
    }

    public Float getHighBuyPrice() {
        return highBuyPrice;
    }

    public void setHighBuyPrice(Float highBuyPrice) {
        this.highBuyPrice = highBuyPrice;
    }

    public Float getLowSellPrice() {
        return lowSellPrice;
    }

    public void setLowSellPrice(Float lowSellPrice) {
        this.lowSellPrice = lowSellPrice;
    }

    public Float getHighSellPrice() {
        return highSellPrice;
    }

    public void setHighSellPrice(Float highSellPrice) {
        this.highSellPrice = highSellPrice;
    }

    public Date getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(Date checkedAt) {
        this.checkedAt = checkedAt;
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
        if (!(object instanceof TypePrice)) {
            return false;
        }
        TypePrice other = (TypePrice) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.dyn.ItemPrice[ id=" + typeID + " ]";
    }
    
}
