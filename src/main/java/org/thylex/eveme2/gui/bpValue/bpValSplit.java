/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.gui.bpValue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import org.thylex.eveme2.app.App;
import org.thylex.eveme2.io.entities.dyn.TypePrice;
import org.thylex.eveme2.io.entities.sde.IndActivityTypes;
import org.thylex.eveme2.io.entities.sde.IndustryActivityMaterials;
import org.thylex.eveme2.io.entities.sde.InvTypes;

/**
 *
 * @author thyle
 */
public class bpValSplit extends javax.swing.JPanel {

    private App app = null;
    private BlueprintValuePanel2 left = null;
    private JPanel right = null;
    private JSplitPane split = null;
    private static final Logger logger = Logger.getLogger(bpValSplit.class.getName());
    /**
     * Creates new form bpValSplit
     */
    public bpValSplit(App appl) {
        app = appl;
        logger.log(Level.INFO, "Initializing BP Value Splitpane");
        
        initComponents();
        
        left = new BlueprintValuePanel2(app, this);
        left.setMinimumSize(new Dimension(200,100));
        right = new JPanel();
        right.setLayout(new GridBagLayout());
        right.setMinimumSize(new Dimension(200,100));
        
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        split.setContinuousLayout(false);
        split.setOneTouchExpandable(false);
        split.setResizeWeight(0.5);
        
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(400, 200));
        this.add(split);
        
        this.doLayout();
        this.validate();
        
        this.setVisible(true);
    }
    
    public void calcBPTable(InvTypes item) {
        right = new MaterialsTable(item, app);
        split.setRightComponent(right);
        split.doLayout();
    }
    
    public void calcBPValue(InvTypes item) {
        logger.log(Level.INFO, "Calculating value of ".concat(item.getTypeName()));
        
        HashMap<String, Set<IndustryActivityMaterials>> sorted = sortItems(item);
        HashSet<Integer> itemIDs = new HashSet();
        HashMap<Integer, TypePrice> prices;
        
        // Clean any old subcomponents
        for (Component c : right.getComponents()) {
            right.remove(c);
        }
        
        logger.log(Level.INFO, "Finding materials for production");
        // Build list of item IDs and get prices for them
        for (IndustryActivityMaterials mat : app.getSdeWorker().findIndyMaterials(item.getTypeID(), IndActivityTypes.Manufacturing)) {
            itemIDs.add(mat.getMaterial().getTypeID());
        }

        logger.log(Level.INFO, "Checking prices for materials");
        prices = (HashMap<Integer, TypePrice>) app.getDynWorker().getPrices(itemIDs, Boolean.TRUE);
        
        // Create new subcompoents
        int row = 0;
        for (String key : sorted.keySet()) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            right.add(new MaterialsPanel(key, sorted.get(key), prices), gbc);
        }
        this.validate();
    }
    
    private HashMap<String, Set<IndustryActivityMaterials>> sortItems(InvTypes items) {
        HashMap<String, Set<IndustryActivityMaterials>> result = new HashMap<>();
        for (IndustryActivityMaterials mat : app.getSdeWorker().findIndyMaterials(items.getTypeID(), IndActivityTypes.Manufacturing)) {
            //String key = mat.getMaterial().getInvGroup().getInvCategory().getCategoryName();
            String key = app.getSdeWorker().findCategoryNameById(mat.getMaterial().getInvGroup().getCategoryID());
            if (result.containsKey(key)) {
                Set<IndustryActivityMaterials> temp = result.get(key);
                temp.add(mat);
                result.put(key, temp);
            } else {
                Set<IndustryActivityMaterials> temp;
                temp = new HashSet<>();
                temp.add(mat);
                result.put(key, temp);
            }
        }
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
