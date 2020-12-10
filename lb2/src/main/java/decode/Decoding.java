/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decode;

import encode.Coding;
import helper.Encode;
import helper.Files;
import helper.SubkeyGenerator;

/**
 *
 * @author yaroslav
 */
public class Decoding {
    
    StringBuilder sk = new StringBuilder();
    
    public String getDecodedText(String text, String key, int numberRounds, int typeFunc, int typeKey) {
        StringBuilder res = new StringBuilder();
        String binText = Encode.stringToBin(text, 8);
        String binKey = Encode.stringToBin(key, 8);
        String block;
        System.out.println("BinText: " + binText);
        sk.setLength(0);
        for (int i = binText.length(); i > 0 ; i -= 64) {
            block = binText.substring(i - 64, i);
            System.out.println("Block: " + block);
            res.append(coding(block, binKey, numberRounds, typeFunc, typeKey));        
        }
        Files.saveToFile("/home/yaroslav/NetBeansProjects/lb2/files/subkeys/subkeys_decode", sk.toString());
        return res.toString();
    }
    
    private String coding(String block, String key, int numberRounds, int typeFunc, int typeKey) {
        Coding c = new Coding();
        StringBuilder res = new StringBuilder();
        SubkeyGenerator sbkg = new SubkeyGenerator();
        String subKey;
        String right = block.substring(0, block.length() / 2);
        String left = block.substring(block.length() / 2);
        System.out.println("Base l: " + left);
        System.out.println("Base r: " +right);
        System.out.println("//////////////////////");
        for (int i = numberRounds-1; i >= 0; i--) {
            res.setLength(0);
            subKey = sbkg.getSubkey(typeKey, key, i);
            if (typeFunc == 0) {
                left = c.single(left, subKey);
            } else {             
                
                left = c.xor(left, subKey);
            }
            sk.append(subKey).append('\n');
            for (int k = 0; k < left.length(); k++) {
                 res.append(Integer.toString(Integer.parseInt(String.valueOf(left.charAt(k))) ^ Integer.parseInt(String.valueOf(right.charAt(k)))));
            }
            System.out.println(right);
            System.out.println("----------------------");
            if (i > 0) {
                right = left;
                left = res.toString();
            } else {
                left = res.toString();
            }
            System.out.println(left);
            System.out.println(right);
            System.out.println("**********************");
        }
        
        return right.concat(left);
    }
}
