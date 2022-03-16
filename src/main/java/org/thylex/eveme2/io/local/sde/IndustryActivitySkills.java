/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.local.sde;

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
@Table(name = "industryActivitySkills")
@NamedQueries({
    @NamedQuery(name = "IndustryActivitySkills.findAll", query = "SELECT i FROM IndustryActivitySkills i")})
public class IndustryActivitySkills implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "typeID")
    private Integer typeID;
    @Column(name = "activityID")
    private Integer activityID;
    @Column(name = "skillID")
    private Integer skillID;
    @Column(name = "level")
    private Integer level;
    @Id
    @Column(name = "indActSkillID")
    private Integer indActSkillID;

    public IndustryActivitySkills() {
    }

    public IndustryActivitySkills(Integer indActSkillID) {
        this.indActSkillID = indActSkillID;
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

    public Integer getSkillID() {
        return skillID;
    }

    public void setSkillID(Integer skillID) {
        this.skillID = skillID;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIndActSkillID() {
        return indActSkillID;
    }

    public void setIndActSkillID(Integer indActSkillID) {
        this.indActSkillID = indActSkillID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indActSkillID != null ? indActSkillID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndustryActivitySkills)) {
            return false;
        }
        IndustryActivitySkills other = (IndustryActivitySkills) object;
        if ((this.indActSkillID == null && other.indActSkillID != null) || (this.indActSkillID != null && !this.indActSkillID.equals(other.indActSkillID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.IndustryActivitySkills[ indActSkillID=" + indActSkillID + " ]";
    }
    
}
