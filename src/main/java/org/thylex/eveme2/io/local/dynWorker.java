/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.io.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.thylex.eveme2.io.local.dyn.ItemPrice;
import org.thylex.eveme2.io.online.evemarketer.JsonReply;

/**
 *
 * @author thyle
 */
public class dynWorker {
    private EntityManager em = null;
    
    public dynWorker(EntityManager worker) {
        this.em = worker;
    }
    
    public ItemPrice findItemPriceByID(Integer itemID) {
        try {
            Query q = em.createQuery("SELECT p FROM ItemPrice p WHERE p.itemID = :id");
            q.setParameter("id", itemID);
            return (ItemPrice) q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public Map<Integer, ItemPrice> getPrices(Set<Integer> itemIDs, Boolean refreshOldAndNotFound) {
        HashMap<Integer, ItemPrice> result = new HashMap();
        HashSet<Integer> notFound = new HashSet();
        
        for(Integer id : itemIDs) {
            ItemPrice temp = findItemPriceByID(id);
            if (temp == null) {
                System.out.println("No cached price found for ID: " + id.toString());
                // Add ID to check with EVEMarketer later
                notFound.add(id);
            } else {
                result.put(id, temp);
            }
        }
        
        if (refreshOldAndNotFound && (! notFound.isEmpty())) {
            System.out.println("Have items to check with EVEMarketer: " + notFound.size());
            result.putAll(checkEVEMarketer(notFound));
        }
        
        return result;
    }
    
    private Map<Integer, ItemPrice> checkEVEMarketer(Set<Integer> itemIDs) {
        HashMap<Integer, ItemPrice> result = new HashMap();
        String baseURL = "https://api.evemarketer.com/ec/marketstat/json";
        
        // Build URL suffix
        StringBuilder itemString = new StringBuilder("?typeid=");
        for (Integer id : itemIDs) {
            itemString.append(id);
            itemString.append(",");
        }
        String webURL = baseURL + itemString.toString();
        // Strip trailing comma sign
        webURL = webURL.substring(0, (webURL.length() -1));
        // Limit to The Forge region for now
        webURL = webURL + "&regionlimit=10000002";
        System.out.println(webURL);
        
        try {
            URL url = new URL(webURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            
            con.setRequestMethod("POST");
            con.setConnectTimeout(30000);
            con.setReadTimeout(30000);
            con.setDoOutput(true);
            
            con.connect();
            
            StringBuilder response = new StringBuilder();
            BufferedReader bIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String input;
            while((input = bIn.readLine()) != null) {
                response.append(input);
            }
            // PArse the reply
            ObjectMapper jackson = new ObjectMapper();
            //JsonNode fullReply = jackson.readTree(bIn);
            System.out.println(response.toString());
            JsonReply[] jsonReplies = jackson.readValue(response.toString(), JsonReply[].class);
            System.out.println("Json reply size: " + jsonReplies.length);
            
            for (JsonReply reply : jsonReplies) {
                ItemPrice newPrice = new ItemPrice();
                newPrice.setCheckedAt(reply.getBuy().getGenerated());
                newPrice.setItemID(reply.getBuy().getForQuery().getTypes()[0].intValue());
                newPrice.setHighBuyPrice(reply.getBuy().getMax().floatValue());
                newPrice.setLowBuyPrice(reply.getBuy().getMin().floatValue());
                newPrice.setHighSellPrice(reply.getSell().getMax().floatValue());
                newPrice.setLowSellPrice(reply.getSell().getMin().floatValue());
                persistItemPrice(newPrice);
                result.put(newPrice.getItemID(), newPrice);
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(dynWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(dynWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void persistItemPrice(ItemPrice ip) {
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(ip);
        t.commit();
        
    }
    
    public void Close() {
        this.em.close();
    }
    
}
