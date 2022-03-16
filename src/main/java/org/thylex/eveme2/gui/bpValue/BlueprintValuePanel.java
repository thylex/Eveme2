/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.gui.bpValue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import org.thylex.eveme2.app.App;
import org.thylex.eveme2.io.local.sde.InvCategories;
import org.thylex.eveme2.io.local.sde.InvGroups;
import org.thylex.eveme2.io.local.sde.InvTypes;

/**
 *
 * @author thyle
 */
public class BlueprintValuePanel extends javax.swing.JPanel {

    private App app = null;
    private InvCategories bpCategory = null;
    private InvGroups bpGroup = null;
    private JComboBox cmbGroups = null;
    private JComboBox cmbBlueprint = null;
    private JComboBox cmbME = null;
    private JComboBox cmbTE = null;
    private static String[] MEvalues = {"20", "18", "16", "14", "12", "10", "8", "6", "4", "2", "0"};
    private static String[] TEvalues = {"10", "9", "8", "7", "6", "5", "4", "3", "2", "1", "0"};
    private bpValSplit parent = null;
    private InvTypes selectedItem = null;
    
    /**
     * Creates new form BlueprintValuePanel
     */
    public BlueprintValuePanel(App appl, bpValSplit parentPanel) {
        this.app = appl;
        this.parent = parentPanel;
        
        initComponents();
        
        GridBagConstraints row1 = new GridBagConstraints();
        row1.gridx = GridBagConstraints.RELATIVE;
        row1.gridy = 0;
        row1.weightx = 1.0;
        row1.weighty = 1.0;
        row1.anchor = GridBagConstraints.FIRST_LINE_START;
        row1.fill = GridBagConstraints.HORIZONTAL;
        this.setLayout(new GridBagLayout());
        
        // Get the base category object
        this.bpCategory = app.getSdeWorker().findCategoriesByName("Blueprint");

        // Prep the group combobox
        Vector<String> groups = new Vector();
        groups.add("All");
        bpCategory.getInvGroups().forEach(group -> {
            groups.add(group.getGroupName());
        });
        cmbGroups = new JComboBox(groups);
        String selectedGroup = app.getSettings().getProp("bpValueSelectedGroup");
        if ((selectedGroup != null) && (!"Blueprint".equals(selectedGroup))) {
            System.out.println("Looking for bpGroup: " + selectedGroup);
            bpGroup = app.getSdeWorker().findGroupByName(selectedGroup);
            cmbGroups.setSelectedItem(selectedGroup);
        }
        cmbGroups.addActionListener((ActionEvent e) -> {
            cmbGroupsActionPerformed(e);
        });
        
        JLabel lblGroups = new JLabel("Blueprint group: ");
        lblGroups.setLabelFor(cmbGroups);
        
        this.add(lblGroups, row1);
        this.add(cmbGroups, row1);
        
        // Prep the blueprints based on group selection
        GridBagConstraints row2 = (GridBagConstraints) row1.clone();
        row2.gridy = 1;
        row2.anchor = GridBagConstraints.LINE_START;
        
        cmbBlueprint = new JComboBox(getBlueprints());
        String selectedBP = app.getSettings().getProp("bpValueSelectedBP");
        if (selectedBP != null) {
            cmbBlueprint.setSelectedItem(selectedBP);
            selectedItem = app.getSdeWorker().findTypeByName(selectedBP);
        }
        cmbBlueprint.addActionListener((e) -> {
            cmbBlueprintActionPerformed(e);
        });
        
        JLabel lblBlueprint = new JLabel("Blueprint: ");
        lblBlueprint.setLabelFor(cmbBlueprint);
        
        this.add(lblBlueprint, row2);
        this.add(cmbBlueprint, row2);
        
        // Prep ME and TE
        GridBagConstraints row3 = (GridBagConstraints) row2.clone();
        row3.gridy = 3;
        
        cmbME = new JComboBox(MEvalues);
        cmbME.addActionListener((e) -> {
            cmdMeTeActionPerformed(e);
        });
        JLabel lblME = new JLabel("Material eff.: ");
        lblME.setLabelFor(cmbME);
        
        GridBagConstraints row4 = (GridBagConstraints) row3.clone();
        row4.gridy = 4;
        
        cmbTE = new JComboBox(TEvalues);
        cmbTE.addActionListener((e) -> {
            cmdMeTeActionPerformed(e);
        });
        JLabel lblTE = new JLabel("Time eff.: ");
        lblTE.setLabelFor(cmbTE);
        
        this.add(lblME, row3);
        this.add(cmbME, row3);
        this.add(lblTE, row4);
        this.add(cmbTE, row4);
        
        this.doLayout();
        this.validate();
        this.setVisible(true);
    }

    public InvTypes getSelectedItem() {
        if (selectedItem != null) {
            return selectedItem;
        } else {
            return null;
        }
    }
    
    private void cmbGroupsActionPerformed(ActionEvent e) {
        JComboBox box = (JComboBox) e.getSource();
        String selection = box.getSelectedItem().toString();
        if ("All".equals(selection)) {
            selection = "Blueprint";
            app.getSettings().setProp("bpValueSelectedGroup", selection);
        } else {
            bpGroup = app.getSdeWorker().findGroupByName(selection);
            app.getSettings().setProp("bpValueSelectedGroup", selection);
        }
        JComboBox temp = new JComboBox(getBlueprints());
        cmbBlueprint.setModel(temp.getModel());
        
        this.validate();
    }
    
    private void cmbBlueprintActionPerformed(ActionEvent e) {
        app.getSettings().setProp("bpValueSelectedBP", cmbBlueprint.getSelectedItem().toString());
        selectedItem = app.getSdeWorker().findTypeByName(cmbBlueprint.getSelectedItem().toString());
        parent.calcBPValue(selectedItem);
    }
    
    private void cmdMeTeActionPerformed(ActionEvent e) {
        app.getSettings().setProp("bpValueSelectedME", cmbME.getSelectedItem().toString());
        app.getSettings().setProp("bpValueSelectedTE", cmbTE.getSelectedItem().toString());
    }
    
    private Vector<String> getBlueprints() {
        Vector<String> retVal = new Vector<>();
        
        System.out.println("Getting blueprints for: " + bpGroup.getGroupName());
        if ((bpGroup == null) || ("Blueprint".equals(bpGroup.getGroupName()))){
            for (InvGroups group : bpCategory.getInvGroups()) {
                for (InvTypes item : group.getInvTypes()) {
                    retVal.add(item.getTypeName());
                }
            }
        } else {
            for (InvTypes item : bpGroup.getInvTypes()) {
                retVal.add(item.getTypeName());
            }
        }
        
        return retVal;
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
            .addGap(0, 745, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
