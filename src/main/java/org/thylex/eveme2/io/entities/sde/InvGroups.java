/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.entities.sde;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thyle
 */
@Entity
@Table(name = "invGroups")
@NamedQueries({
    @NamedQuery(name = "InvGroups.findAll", query = "SELECT i FROM InvGroups i")})
public class InvGroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "groupID")
    private Integer groupID;
//    @Column(name = "categoryID")
//    private Integer categoryID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryID")
    private InvCategories invCategory;
    @OneToMany(mappedBy = "invGroup", fetch = FetchType.LAZY)
    private List<InvTypes> invTypes;
    @Column(name = "groupName")
    private String groupName;
    @Column(name = "iconID")
    private Integer iconID;
    @Column(name = "useBasePrice")
    private Integer useBasePrice;
    @Column(name = "anchored")
    private Integer anchored;
    @Column(name = "anchorable")
    private Integer anchorable;
    @Column(name = "fittableNonSingleton")
    private Integer fittableNonSingleton;
    @Column(name = "published")
    private Integer published;

    public InvGroups() {
    }

    public InvGroups(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public List<InvTypes> getInvTypes() {
        return invTypes;
    }

    public void setInvTypes(List<InvTypes> invTypes) {
        this.invTypes = invTypes;
    }

//    public Integer getCategoryID() {
//        return categoryID;
//    }
//
//    public void setCategoryID(Integer categoryID) {
//        this.categoryID = categoryID;
//    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getIconID() {
        return iconID;
    }

    public void setIconID(Integer iconID) {
        this.iconID = iconID;
    }

    public Integer getUseBasePrice() {
        return useBasePrice;
    }

    public void setUseBasePrice(Integer useBasePrice) {
        this.useBasePrice = useBasePrice;
    }

    public Integer getAnchored() {
        return anchored;
    }

    public void setAnchored(Integer anchored) {
        this.anchored = anchored;
    }

    public Integer getAnchorable() {
        return anchorable;
    }

    public void setAnchorable(Integer anchorable) {
        this.anchorable = anchorable;
    }

    public Integer getFittableNonSingleton() {
        return fittableNonSingleton;
    }

    public void setFittableNonSingleton(Integer fittableNonSingleton) {
        this.fittableNonSingleton = fittableNonSingleton;
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
        hash += (groupID != null ? groupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvGroups)) {
            return false;
        }
        InvGroups other = (InvGroups) object;
        if ((this.groupID == null && other.groupID != null) || (this.groupID != null && !this.groupID.equals(other.groupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.thylex.eveme2.db.sde.InvGroups[ groupID=" + groupID + " ]";
    }

    /**
     * @return the invCategory
     */
    public InvCategories getInvCategory() {
        return invCategory;
    }

    /**
     * @param invCategory the invCategory to set
     */
    public void setInvCategory(InvCategories invCategory) {
        this.invCategory = invCategory;
    }
    
}
