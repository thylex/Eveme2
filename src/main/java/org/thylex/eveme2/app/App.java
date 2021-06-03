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
import org.thylex.eveme2.db.Db;
import org.thylex.eveme2.db.dynWorker;
import org.thylex.eveme2.db.sde.InvCategories;
import org.thylex.eveme2.db.sde.InvGroups;
import org.thylex.eveme2.db.sdeWorker;
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
    
    public void testingShit() {
        InvCategories BPCat = SDE.findCategoriesByName("Blueprint");
        System.out.println(BPCat.getInvGroups().size());
        for (InvGroups group : BPCat.getInvGroups()) {
            System.out.println(group.getGroupName() + ": " + group.getInvTypes().size());
        }
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
        gui.dispose();
    }
}
