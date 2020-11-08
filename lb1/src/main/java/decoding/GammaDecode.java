/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoding;

import helper.Encode;

/**
 *
 * @author yaroslav
 */
public class GammaDecode {
    
    public String decode(String text, String key) { 
        StringBuilder sb = new StringBuilder(text.length());
        
        String bText = Encode.stringToBin(text);
        String bKey = Encode.stringToBin(key);
        
        for (int i = 0;i < bText.length(); i++) {
            sb.append(bText.charAt(i) ^ bKey.charAt(i % bKey.length()));
        }
               
        return Encode.binToString(sb.toString());
    } 
    
}
