/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.eveme2.db;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.thylex.eveme2.app.App;

/**
 *
 * @author thyle
 */
public class Db {
    private App app = null;
    private final String staticDbFileName = "eve-sde.db";
    private File staticDbFile = null;
    private final String dynDbFileName = "eveme-cache.db";
    private File dynDbFile = null;
    private static EntityManagerFactory sdeFactory;
    private static EntityManagerFactory dynFactory;
    
    public Db(App application) {
        app = application;
        staticDbFile = new File(app.getSettings().getProp("AppDir").concat("\\").concat(staticDbFileName));
        dynDbFile = new File(app.getSettings().getProp("AppDir").concat("\\").concat(dynDbFileName));
        
        if (! CheckFilesForStart()) {
            System.out.println("Files missing during startup");
            System.exit(1);
        }
        
        Map sdeProps = new HashMap();
        sdeProps.put("javax.persistence.jdbc.url", "jdbc:sqlite:".concat(staticDbFile.getAbsolutePath()));
        sdeProps.put("javax.persistence.jdbc.user", "");
        sdeProps.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");
        sdeProps.put("javax.persistence.jdbc.password", "");
        sdeFactory = Persistence.createEntityManagerFactory("EVE-SDE", sdeProps);
        
        Map dynProps = new HashMap();
        dynProps.put("javax.persistence.jdbc.url", "jdbc:sqlite:".concat(dynDbFile.getAbsolutePath()));
        dynProps.put("javax.persistence.jdbc.user", "");
        dynProps.put("javax.persistence.jdbc.driver", "org.sqlite.JDBC");
        dynProps.put("javax.persistence.jdbc.password", "");
        dynProps.put("javax.persistence.schema-generation.database.action", "create");
        dynFactory = Persistence.createEntityManagerFactory("EVE-DYN", dynProps);

    }
    
    public EntityManager getSdeEntityManager() {
        return sdeFactory.createEntityManager();
    }
    
    public EntityManager getDynEntityManager() {
        return dynFactory.createEntityManager();
    }
    
    public void CloseAndExit() {
        if (sdeFactory.isOpen()) {
            sdeFactory.close();
        }
        if (dynFactory.isOpen()) {
            dynFactory.close();
        }
    }
        
    private boolean CheckFilesForStart() {
        boolean staticOK = false;
        boolean dynOK = false;
        
        if (staticDbFile.exists()) {
            staticOK = true;
        } else {
            staticOK = DownloadNewStaticDump(app.getSettings().getProp("AppDir"));
        }
        
        try {
            if (dynDbFile.exists()) {
                dynOK = true;
            } else {
                dynOK = dynDbFile.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return staticOK && dynOK;
    }
    
    private boolean DownloadNewStaticDump(String path) {
        File basePath = new File(path);
        String URL = "https://www.fuzzwork.co.uk/dump/latest/eve.db.bz2";
        Path dlPath = Path.of(basePath.getAbsolutePath().concat("\\").concat("eve.db.bz2"));
        OkHttpClient client = new OkHttpClient();
        Response resp = null;
        Request req = new Request.Builder()
                .url(URL)
                .build();
        
        try {
            resp = client.newCall(req).execute();
        } catch (IOException ex) {
            System.out.println("HTTP Get error: " + ex.getMessage()); 
            return false;
        }
        
        InputStream input = resp.body().byteStream();
        File dlFile = dlPath.toFile();
        byte[] dlBuffer = new byte[1024];
        int bytesRead;
        
        try {
            dlFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(dlFile);
            
            while ( (bytesRead = input.read(dlBuffer)) > 0) {
                fout.write(dlBuffer, 0, bytesRead);
            }
            fout.flush();
            fout.close();
            System.out.println("Download complete, file closed");
        } catch (IOException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        // Uncompress downloaded file
        System.out.println("Uncompressing downloaded file");
        File oFile = new File(app.getSettings().getProp("AppDir").concat("\\".concat("eve.db")));
        try {
            // Set up input
            FileInputStream fin = new FileInputStream(dlFile);
            BufferedInputStream bis = new BufferedInputStream(fin);
            CompressorInputStream bz2Input = new CompressorStreamFactory().createCompressorInputStream(bis);
            // Set up output
            FileOutputStream fileOut = new FileOutputStream(oFile);
            final byte[] buffer = new byte[1024];
            int bzBytesRead;
            while ( (bzBytesRead = bz2Input.read(buffer)) > 0 ) {
                fileOut.write(buffer, 0, bzBytesRead);
            }
            fileOut.flush();
            fileOut.close();
            fin.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (CompressorException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        System.out.println("Uncompress complete.");

        //TODO: Clean up files, delete and rename as needed
        dlFile.delete();
        if (staticDbFile.exists() && staticDbFile != null) {
            staticDbFile.delete();
        }
        oFile.renameTo(staticDbFile);
        
        // Fix tables missing primary keys
        FixSDE();

        return true;
    }
    
    private void FixSDE() {
        Connection sdeConn = null;
        String sdeUrl = "jdbc:sqlite:" + staticDbFile.getAbsolutePath();
        
        List<String> IndActMats = new ArrayList<>(List.of(
                "PRAGMA foreign_keys=off;",
                "BEGIN TRANSACTION;",
                "ALTER TABLE industryActivityMaterials RENAME TO industryActivityMaterials_old;",
                "CREATE TABLE industryActivityMaterials (typeID INTEGER,activityID INTEGER,materialTypeID INTEGER,quantity INTEGER,indActMatID INTEGER PRIMARY KEY);",
                "INSERT INTO industryActivityMaterials (typeID,activityID,materialTypeID,quantity) SELECT * FROM industryActivityMaterials_old;",
                "COMMIT;",
                "PRAGMA foreign_keys=on;"
        ));
        List<String> IndActProb = new ArrayList<>(List.of(
                "PRAGMA foreign_keys=off;",
                "BEGIN TRANSACTION;",
                "ALTER TABLE industryActivityProbabilities RENAME TO industryActivityProbabilities_old;",
                "CREATE TABLE industryActivityProbabilities (typeID INTEGER,activityID INTEGER,productTypeID INTEGER,probability DECIMAL(3,2),indActProbID INTEGER PRIMARY KEY);",
                "INSERT INTO industryActivityProbabilities (typeID,activityID,productTypeID,probability) SELECT * FROM industryActivityProbabilities_old;",
                "COMMIT;",
                "PRAGMA foreign_keys=on;"
        ));
        List<String> IndActProds = new ArrayList<>(List.of(
                "PRAGMA foreign_keys=off;",
                "BEGIN TRANSACTION;",
                "ALTER TABLE industryActivityProducts RENAME TO industryActivityProducts_old;",
                "CREATE TABLE industryActivityProducts (typeID INTEGER,activityID INTEGER,productTypeID INTEGER,quantity INTEGER,indActProdID INTEGER PRIMARY KEY);",
                "INSERT INTO industryActivityProducts (typeID,activityID,productTypeID,quantity) SELECT * FROM industryActivityProducts_old;",
                "COMMIT;",
                "PRAGMA foreign_keys=on;"
        ));
        List<String> IndActRaces = new ArrayList<>(List.of(
                "PRAGMA foreign_keys=off;",
                "BEGIN TRANSACTION;",
                "ALTER TABLE industryActivityRaces RENAME TO industryActivityRaces_old;",
                "CREATE TABLE industryActivityRaces (typeID INTEGER,activityID INTEGER,productTypeID INTEGER,raceID INTEGER,indActRaceID INTEGER PRIMARY KEY);",
                "INSERT INTO industryActivityRaces (typeID,activityID,productTypeID,raceID) SELECT * FROM industryActivityRaces_old;",
                "COMMIT;",
                "PRAGMA foreign_keys=on;"
        ));
        List<String> IndActSkills = new ArrayList<>(List.of(
                "PRAGMA foreign_keys=off;",
                "BEGIN TRANSACTION;",
                "ALTER TABLE industryActivitySkills RENAME TO industryActivitySkills_old;",
                "CREATE TABLE industryActivitySkills (typeID INTEGER,activityID INTEGER,skillID INTEGER,level INTEGER,indActSkillID INTEGER PRIMARY KEY);",
                "INSERT INTO industryActivitySkills (typeID,activityID,skillID,level) SELECT * FROM industryActivitySkills_old;",
                "COMMIT;",
                "PRAGMA foreign_keys=on;"
        ));
        
        try {
            sdeConn = DriverManager.getConnection(sdeUrl);
            
            // Fixing industryActivityMaterials
            for (String line : IndActMats) {
//                System.out.println("Executing line: " + line);
                Statement stmt = sdeConn.createStatement();
                stmt.execute(line);
            }
            
            // Fixing industryActivityProbabilities
            for (String line : IndActProb) {
//                System.out.println("Executing line: " + line);
                Statement stmt = sdeConn.createStatement();
                stmt.execute(line);
            }
            
            // Fixing industryActivityProducts
            for (String line : IndActProds) {
//                System.out.println("Executing line: " + line);
                Statement stmt = sdeConn.createStatement();
                stmt.execute(line);
            }
            
            // Fixing industryActivityRaces
            for (String line : IndActRaces) {
//                System.out.println("Executing line: " + line);
                Statement stmt = sdeConn.createStatement();
                stmt.execute(line);
            }
            
            // Fixing industryActivitySkills
            for (String line : IndActSkills) {
//                System.out.println("Executing line: " + line);
                Statement stmt = sdeConn.createStatement();
                stmt.execute(line);
            }
            
            sdeConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
