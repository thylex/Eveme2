/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.sde;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "industryActivity")
@NamedQueries({
    @NamedQuery(name = "IndustryActivity.findAll", query = "SELECT i FROM IndustryActivity i")})
public class IndustryActivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IndustryActivityPK industryActivityPK;
    @Column(name = "time")
    private Integer time;

    public IndustryActivity() {
    }

    public IndustryActivity(IndustryActivityPK industryActivityPK) {
        this.industryActivityPK = industryActivityPK;
    }

    public IndustryActivity(int typeID, int activityID) {
        this.industryActivityPK = new IndustryActivityPK(typeID, activityID);
    }

    public IndustryActivityPK getIndustryActivityPK() {
        return industryActivityPK;
    }

    public void setIndustryActivityPK(IndustryActivityPK industryActivityPK) {
        this.industryActivityPK = industryActivityPK;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (industryActivityPK != null ? industryActivityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivity)) {
            return false;
        }
        IndustryActivity other = (IndustryActivity) object;
        if ((this.industryActivityPK == null && other.industryActivityPK != null) || (this.industryActivityPK != null && !this.industryActivityPK.equals(other.industryActivityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivity[ industryActivityPK=" + industryActivityPK + " ]";
    }
    
}
