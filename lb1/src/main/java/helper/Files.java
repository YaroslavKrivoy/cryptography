/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author yaroslav
 */
public class Files {
    
    public static void saveToFile(String path, String text) {
        File f = new File(path);       

        try(FileWriter writer = new FileWriter(path, false))
        {
            writer.write(text);
            writer.flush();
            writer.close();
        }
        catch(IOException ex){             
            System.out.println(ex.getMessage());
        } 
    }
    
    public static void saveToFileBin(String path, byte[] text) throws IOException {
        try(FileOutputStream stream = new FileOutputStream(path)){
            stream.write(text);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public static int getFileContentLength(String path) {
        int c, counter = 0;
        try(FileReader reader = new FileReader(path)) {
            while((c=reader.read())!=-1) {
                
                counter++;
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return counter;
    }
    
    public static String getAbsolutePath() {
        JFileChooser fc = new JFileChooser();
        String path = "";
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile(); 
            path = file.getAbsolutePath();
        }
        return path;
    }
    
    public static String readFile(String path) {
        System.out.println(path);
        int length = getFileContentLength(path);
        StringBuilder sb = new StringBuilder(length);
        try(FileReader reader = new FileReader(path))
        {
            int c;
            while((c=reader.read())!=-1){   
                
                sb.append(String.valueOf((char)c));
            } 
        }
        catch(IOException ex){             
            System.out.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    public static byte[] readFileByte(String path) {
        FileInputStream fis = null;
        byte[] bFile = new byte[getFileContentLength(path)];
        try
        {
            fis = new FileInputStream(path);
            fis.read(bFile);
            fis.close();
        }
        catch(IOException e){             
            e.printStackTrace();
        }
        return bFile;
    }
}
