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
@Table(name = "invCategories")
@NamedQueries({
    @NamedQuery(name = "InvCategories.findAll", query = "SELECT i FROM InvCategories i"),
    @NamedQuery(name = "InvCategories.findByName", query = "SELECT i FROM InvCategories i WHERE i.categoryName = :name")
})
public class InvCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "categoryID")
    private Integer categoryID;
//    @OneToMany(mappedBy = "invCategory", fetch = FetchType.LAZY)
//    private List<InvGroups> invGroups;
    @Column(name = "categoryName")
    private String categoryName;
    @Column(name = "iconID")
    private Integer iconID;
    @Column(name = "published")
    private Integer published;

    public InvCategories() {
    }
    
    public InvCategories(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvCategories)) {
            return false;
        }
        InvCategories other = (InvCategories) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.InvCategories[ categoryID=" + categoryID + " ]";
    }

//    public List<InvGroups> getInvGroups() {
//        return invGroups;
//    }
//
//    public void setInvGroups(List<InvGroups> invGroups) {
//        this.invGroups = invGroups;
//    }
    
}
