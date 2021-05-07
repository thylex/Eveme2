/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.db;

import javax.persistence.EntityManager;

/**
 *
 * @author thyle
 */
public class dynWorker {
    private EntityManager em = null;
    
    public dynWorker(EntityManager worker) {
        this.em = worker;
    }
    
    public void Close() {
        this.em.close();
    }
    
}
