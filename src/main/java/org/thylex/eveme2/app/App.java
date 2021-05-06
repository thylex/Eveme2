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
import org.thylex.eveme2.gui.MainFrame;

/**
 *
 * @author thyle
 */
public class App {
    private File AppDir = null;
    private Db DB = null;
    private MainFrame gui = null;
    private EvemeSettings Settings = null;

    public App() {
        // Set base directory for all application related files, create if it doesn't exist
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
                }
            }
            //System.out.print(AppDir.getPath().toString());
        }
        
        // Initialize databases
        Settings = new EvemeSettings(AppDir.toString());
        DB = new Db(this);
        gui = new MainFrame(this);
        
    }
    
    public File getAppDir() {
        return AppDir;
    }
    
    public EvemeSettings getSettings() {
        return this.Settings;
    }
    
    public void CloseAndExit() {
        DB.CloseAndExit();
        gui.dispose();
    }
}
