
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer Nitro 5
 */
public class Enkripsi {
    public static void enkripsi(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdirs();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                enkripsi(srcFile, destFile);
            }

        } else {
            try {
                //if file, then copy it
                //Use bytes stream to support all file types
                InputStream in = new FileInputStream(src);
                OutputStream out = new FileOutputStream(dest);
                
                MessageDigest md = MessageDigest.getInstance("MD5");
                //byte[] digest = md.digest();
                
                byte[] buffer = new byte[1024];
            
                int length;
                //copy the file content in bytes
                while ((length = in.read(buffer)) > 0) {
                    md.update(buffer,0,length);
                    buffer = md.digest();
                    String encHasil = new BigInteger(1,md.digest()).toString(16);
                    String hasil = encHasil.toUpperCase();
                    out.write(hasil.getBytes());
                }
                in.close();
                out.close();
                System.out.println("File copied from " + src + " to " + dest);
            } catch (NoSuchAlgorithmException ex) {
                
            }
        }
    }
}
