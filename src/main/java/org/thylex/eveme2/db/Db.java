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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.thylex.eveme2.app.App;
import org.thylex.eveme2.db.sde.*;

/**
 *
 * @author thyle
 */
public class Db {
    private App app = null;
    private final String staticDbFileName = "eve-sde-20210427.db";
    private File staticDbFile = null;
    private final String dynDbFileName = "eveme-cache.db";
    private File dynDbFile = null;
    private static EntityManagerFactory sdeFactory;
    private static EntityManagerFactory dynFactory;
    
    public Db(App application) {
        app = application;
        staticDbFile = new File(app.getAppDir().getAbsolutePath().concat("\\").concat(staticDbFileName));
        dynDbFile = new File(app.getAppDir().getAbsolutePath().concat("\\").concat(dynDbFileName));
        
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
            staticOK = DownloadNewStaticDump(app.getAppDir());
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
    
    public InvItems GetItemByID(Integer ItemID) {
        EntityManager em = sdeFactory.createEntityManager();
        Query q = em.createQuery("Select i from InvItems i where i.itemID = :id");
        q.setParameter("id", ItemID);
        return (InvItems) q.getSingleResult();
    }
    
    private boolean DownloadNewStaticDump(File basePath) {
        String URL = "https://www.fuzzwork.co.uk/dump/sde-20210427-TRANQUILITY/eve.db.bz2";
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
        File oFile = new File(app.getAppDir().getAbsolutePath().concat("\\".concat("eve.db")));
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

        return true;
    }
}
