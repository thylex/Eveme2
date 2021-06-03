/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.db;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.thylex.eveme2.db.sde.InvCategories;
import org.thylex.eveme2.db.sde.InvItems;

/**
 *
 * @author thyle
 */
public class sdeWorker {
    private EntityManager em = null;
    
    public sdeWorker(EntityManager worker) {
        this.em = worker;
    }
    public InvItems GetItemByID(Integer ItemID) {
        Query q = em.createQuery("Select i from InvItems i where i.itemID = :id");
        q.setParameter("id", ItemID);
        return (InvItems) q.getSingleResult();
    }
    
    public InvCategories findCategoriesByName(String name){
        Query q = em.createQuery("SELECT i FROM InvCategories i WHERE i.categoryName = :name");
        q.setParameter("name", name);
        return (InvCategories) q.getSingleResult();
    }
    
    public void Close() {
        this.em.close();
    }

}
