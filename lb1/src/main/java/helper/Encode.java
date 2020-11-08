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
    
    public static String stringToBin(String text) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);           
            res.append(String.format("%12s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
        }

        return res.toString();
    }
    
    public static String stringToBin(byte[] text) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < text.length; i++) {
            int number = Byte.toUnsignedInt(text[i]);
            res.append(String.format("%12s", Integer.toBinaryString(number)).replaceAll(" ", "0"));       
        }

        return res.toString();
    }
    
    public static String stringToHex(String text) throws UnsupportedEncodingException {
        StringBuilder res = new StringBuilder();
        byte[] ba = text.getBytes("cp1251");
        
        for(int i = 0; i < ba.length; i++)
            res.append(String.format("%04x", ba[i]));
        
        return res.toString();
    }
    
    public static String hexToString(String hex) throws UnsupportedEncodingException {
        byte[] bytes = DatatypeConverter.parseHexBinary(hex);
        String result= new String(bytes, "cp1251");
        return result;
    }
    
    public static String binToString(String bin) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < bin.length(); i+=12) {
            res.append((char) Integer.parseInt(bin.substring(i, i + 12), 2));
        }

        return res.toString();
    }
}
