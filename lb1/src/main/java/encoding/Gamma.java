/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encoding;

import helper.Encode;
import helper.Files;

/**
 *
 * @author yaroslav
 */
public class Gamma {
    
    public String encode(String path, String key) {
        
        String text = Files.readFile(path);
        StringBuilder sb = new StringBuilder(text.length());
        
        String bText = Encode.stringToBin(text);
        String bKey = Encode.stringToBin(key);
        
        for (int i = 0;i < bText.length(); i++) {
            sb.append(bText.charAt(i) ^ bKey.charAt(i % bKey.length()));
        }
        
        return Encode.binToString(sb.toString());
    }
    
}
