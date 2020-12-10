/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author yaroslav
 */
public class Encode {
    
    public static String stringToBin(String text, int length) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);      
            res.append(String.format("%"+ length +"s", Integer.toBinaryString(c)).replaceAll(" ", "0"));      
        }
        
        return res.toString();
    }
    
    public static String stringToBin(byte[] text, int length) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length; i++) {
            int number = Byte.toUnsignedInt(text[i]);
            res.append(String.format("%"+ length +"s", Integer.toBinaryString(number)).replaceAll(" ", "0"));       
        }

        return res.toString();
    }
    
    public static String stringToHex(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("UTF-8");
        return DatatypeConverter.printHexBinary(bytes);
    }
    
    public static String hexToString(String hex) throws UnsupportedEncodingException {
        byte[] bytes = DatatypeConverter.parseHexBinary(hex);
        String result= new String(bytes, "UTF-8");
        return result;
    }
    
    public static String binToString(String bin, int length) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < bin.length(); i+=length) {
            res.append((char) Integer.parseInt(bin.substring(i, i + length), 2));
        }

        return res.toString();
    }
}
