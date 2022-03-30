/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henrik
 */
public class EvemeSettings {
    private Properties props = null;

    public EvemeSettings(String appDir) {
        String XMLFile = appDir + "\\settings.xml";
        props = new Properties();
        props.setProperty("SettingsFile", XMLFile);
        props.setProperty("AppDir", appDir);
        if (Files.exists(Path.of(XMLFile))) {
            this.load();
        } else {
            this.save();
        }
    }
    
    public void save() {
        try {
            OutputStream outstream = new FileOutputStream(new File(props.getProperty("SettingsFile")));
            //System.out.println(props.toString());
            props.storeToXML(outstream, "Eveme2 Properties");
            outstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvemeSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EvemeSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void load() {
        try {
            InputStream instream = new FileInputStream(new File(props.getProperty("SettingsFile")));
            //System.out.println(props.toString());
            props.loadFromXML(instream);
            instream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvemeSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EvemeSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getProp(String PropKey) {
        return props.getProperty(PropKey, null);
    }
    
    public void setProp(String PropKey, String PropVal) {
        props.setProperty(PropKey, PropVal);
        this.save();
    }
}
