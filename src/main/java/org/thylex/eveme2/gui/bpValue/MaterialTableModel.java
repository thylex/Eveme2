/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.gui.bpValue;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.thylex.eveme2.io.entities.dyn.TypePrice;
import org.thylex.eveme2.io.entities.sde.IndustryActivityMaterials;

/**
 *
 * @author thyle
 */
public class MaterialTableModel extends AbstractTableModel {

    private static final Logger logger = Logger.getLogger(MaterialTableModel.class.toString());
    private static final String[] columnNames = {"Name", "Type", "Quantity", "Price"};
    private int rows = 0;
    private Object[][] tableData = null;
    private IndustryActivityMaterials rowMaterial[] = null;
    private TypePrice rowPrice[] = null;
    
    
    public MaterialTableModel(HashMap<String, Set<IndustryActivityMaterials>> materials, HashMap<Integer, TypePrice> prices) {
        logger.log(Level.INFO, "Calculating number of rows in model");
        materials.keySet().stream().map(keyString -> materials.get(keyString)).forEachOrdered(temp -> {
            rows += temp.size();
        });
        
        logger.log(Level.INFO, "Creating new tableData array of rows: ".concat(Integer.toString(rows)));
        tableData = new Object[rows][];
        rowMaterial = new IndustryActivityMaterials[rows];
        rowPrice = new TypePrice[rows];
        int row = 0;
        int col = 0;
        for (String key : materials.keySet()) {
            for (IndustryActivityMaterials material : materials.get(key)) {
                Object[] line = new Object[4];
                col = 0;
                line[col++] = material.getMaterial().getTypeName();
                line[col++] = key;
                line[col++] = material.getQuantity();
                if (prices.containsKey(material.getMaterial().getTypeID())) {
                    line[col++] = prices.get(material.getMaterial().getTypeID()).getLowSellPrice();
                } else {
                    line[col++] = 0;
                }
                rowMaterial[row] = material;
                rowPrice[row] = prices.get(material.getMaterial().getTypeID());
                tableData[row++] = line;
            }
            
        }
    }
    
    public IndustryActivityMaterials getRowMaterial(int rowIndex) {
        return rowMaterial[rowIndex];
    }
    
    public TypePrice getRowPrice(int rowIndex) {
        return rowPrice[rowIndex];
    }
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return rows;
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return tableData[1][columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return tableData[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
