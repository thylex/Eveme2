/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thylex.eveme2.io.local.Db;
import org.thylex.eveme2.io.local.dynWorker;
import org.thylex.eveme2.io.local.sdeWorker;
import org.thylex.eveme2.gui.EvemeFrame;

/**
 *
 * @author thyle
 */
public class App {
    private Db DB = null;
    private EvemeFrame gui = null;
    private EvemeSettings Settings = null;
    private sdeWorker SDE = null;
    private dynWorker DYN = null;

    public App() {
        // Set base directory for all application related files, create if it doesn't exist
        File AppDir = null;
        if ("Windows 10".equals(System.getProperty("os.name"))) {
            String homedir = System.getProperty("user.home").concat("\\Documents\\EveMe2");
            Path path = Path.of(homedir);
            if (Files.exists(path) ) {
                AppDir = path.toFile();
            } else {
                try {
                    path = Files.createDirectories(path);
                    AppDir = path.toFile();
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
            //System.out.print(AppDir.getPath().toString());
        }
        
        // Initialize databases
        Settings = new EvemeSettings(AppDir.toString());
        DB = new Db(this);
        SDE = new sdeWorker(DB.getSdeEntityManager());
        DYN = new dynWorker(DB.getDynEntityManager());
        this.testingShit();
        gui = new EvemeFrame(this);
        
    }
    
    public void getNewSDE() {
        SDE.Close();
        DB.refreshSDE();
        SDE = new sdeWorker(DB.getSdeEntityManager());
        
    }
    
    public void testingShit() {
//        InvCategories BPCat = SDE.findCategoriesByName("Blueprint");
//        System.out.println(BPCat.getInvGroups().size());
//        for (InvGroups group : BPCat.getInvGroups()) {
//            System.out.println(group.getGroupName() + ": " + group.getInvTypes().size());
//        }
//        InvGroups group = SDE.findGroupByName("Cruiser Blueprint");
//        for (InvTypes type : group.getInvTypes()) {
//            System.out.println(type.getTypeName());
//        }
//        System.out.println(SDE.findRaceByName("Caldari").getShortDescription());
        // TypeID 968 is Moa Blueprint
        // TypeID 33519 is MTU BP
//        InvTypes moaBP = SDE.findTypeByID(33519);
//        List<IndustryActivityMaterials> moaBPMats = SDE.findIndyMaterials(moaBP.getTypeID(), IndActivityTypes.Manufacturing);
//        for (IndustryActivityMaterials mat : moaBPMats) {
//            System.out.println("MTU mats: " + mat.getMaterial().getTypeName() + " x " + mat.getQuantity());
//            System.out.println("Mat type: " + mat.getMaterial().getInvGroup().getInvCategory().getCategoryName());
//        }
    }
    
    public EvemeSettings getSettings() {
        return this.Settings;
    }
    
    public sdeWorker getSdeWorker() {
        return this.SDE;
    }
    
    public dynWorker getDynWorker() {
        return this.DYN;
    }
    
    public void CloseAndExit() {
        SDE.Close();
        DYN.Close();
        DB.CloseAndExit();
        
        // Save size and position
        String position = String.format( "%d,%d,%d,%d", gui.getX(), gui.getY(), gui.getWidth(), gui.getHeight());
        //System.out.println("GUI position: " + position);
        Settings.setProp("AppPos", position);
        
        gui.dispose();
    }
}
