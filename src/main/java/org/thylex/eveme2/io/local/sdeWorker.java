/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.local;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.thylex.eveme2.io.entities.sde.ChrRaces;
import org.thylex.eveme2.io.entities.sde.IndustryActivityMaterials;
import org.thylex.eveme2.io.entities.sde.InvCategories;
import org.thylex.eveme2.io.entities.sde.InvGroups;
import org.thylex.eveme2.io.entities.sde.InvItems;
import org.thylex.eveme2.io.entities.sde.InvMarketGroups;
import org.thylex.eveme2.io.entities.sde.InvTypes;

/**
 *
 * @author thyle
 */
public class sdeWorker {
    private EntityManager em = null;
    
    public sdeWorker(EntityManager worker) {
        this.em = worker;
    }
    
    public InvItems findItemByID(Integer ItemID) {
        Query q = em.createQuery("Select i from InvItems i where i.itemID = :id");
        q.setParameter("id", ItemID);
        return (InvItems) q.getSingleResult();
    }
    
    public InvTypes findTypeByID(Integer typeID) {
        Query q = em.createQuery("SELECT i FROM InvTypes i WHERE i.typeID = :id");
        q.setParameter("id", typeID);
        return (InvTypes) q.getSingleResult();
    }
    
    public List<InvTypes> findTypeByMarketGroupID(Integer marketGroupID) {
        Query q = em.createQuery("SELECT i FROM InvTypes i WHERE i.marketGroupID = :id");
        q.setParameter("id", marketGroupID);
        return q.getResultList();
    }
    
    public InvTypes findTypeByName(String name) {
        Query q = em.createQuery("SELECT i FROM InvTypes i WHERE i.typeName = :id");
        q.setParameter("id", name);
        return (InvTypes) q.getSingleResult();
    }
    
    public InvCategories findCategoriesByName(String name){
        Query q = em.createQuery("SELECT i FROM InvCategories i WHERE i.categoryName = :name AND i.published = 1 ORDER BY i.categoryName DESC");
        q.setParameter("name", name);
        return (InvCategories) q.getSingleResult();
    }
    
    public String findCategoryNameById(Integer categoryID) {
        Query q = em.createQuery("SELECT i.categoryName FROM InvCategories i WHERE i.categoryID = :id");
        q.setParameter("id", categoryID);
        return (String) q.getSingleResult();
    }
    
    public InvGroups findGroupByName(String name) {
        Query q = em.createQuery("SELECT i FROM InvGroups i WHERE i.groupName = :name AND i.published = 1");
        q.setParameter("name", name);
        return (InvGroups) q.getSingleResult();
    }
    
    public List<InvGroups> findGroupsByCategoryID(Integer categoryID) {
        Query q = em.createQuery("SELECT i FROM InvGroups i WHERE i.published = 1 AND i.categoryID = :id ORDER BY i.groupName DESC");
        q.setParameter("id", categoryID);
        return q.getResultList();
    }
    
    public InvMarketGroups findMarketGroupsByName(String name) {
        Query q = em.createQuery("SELECT g FROM InvMarketGroups g WHERE g.marketGroupName = :groupName");
        q.setParameter("groupName", name);
        return (InvMarketGroups) q.getSingleResult();
    }
    
    public List<InvMarketGroups> findMarketGroupsByParentID(Integer parentID) {
        Query q = em.createQuery("SELECT g FROM InvMarketGroups g WHERE g.parentGroupID = :id");
        q.setParameter("id", parentID);
        return q.getResultList();
    }
    
    public ChrRaces findRaceByName(String name) {
        Query q = em.createQuery("SELECT c FROM ChrRaces c WHERE c.raceName = :name");
        q.setParameter("name", name);
        return (ChrRaces) q.getSingleResult();
    }
    
    public List<IndustryActivityMaterials> findIndyMaterials(Integer typeID, Integer activity) {
        Query q = em.createQuery("SELECT m FROM IndustryActivityMaterials m WHERE m.typeID = :type AND m.activityID = :act");
        q.setParameter("type", typeID);
        q.setParameter("act", activity);
        return q.getResultList();
    }
    
    public void Close() {
        this.em.close();
    }

}
