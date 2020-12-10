/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encode;

import helper.Encode;
import helper.Files;
import helper.Scrambler;
import helper.SubkeyGenerator;

/**
 *
 * @author yaroslav
 */
public class Coding {
    
    StringBuilder sk = new StringBuilder();
    
    public String getEncodedText(String text, String key, int numberRounds, int typeFunc, int typeKey) {
        StringBuilder res = new StringBuilder();
        String binText = Encode.stringToBin(text, 8);
        String binKey = Encode.stringToBin(key, 8);
        String block;
        sk.setLength(0);
        int count = 1;
        for (int i = 0; i < binText.length(); i += 64) {
            if (binText.substring(i).length() < 64) {
                block = String.format("%64s", binText.substring(i)).replaceAll(" ", "0");
            } else {
                block = binText.substring(i, 64 * count);
                count++;
            }
            res.append(coding(block, binKey, numberRounds, typeFunc, typeKey));
           
        }
        Files.saveToFile("/home/yaroslav/NetBeansProjects/lb2/files/subkeys/subkeys", sk.toString());
        return res.toString();
    }
    
    private String coding(String block, String key, int numberRounds, int typeFunc, int typeKey) {
        StringBuilder res = new StringBuilder();
        SubkeyGenerator sbkg = new SubkeyGenerator();
        String subKey;
        String left = block.substring(0, block.length() / 2);
        String right = block.substring(block.length() / 2);
        System.out.println("Base l: " + left);
        System.out.println("Base r: " +right);
        System.out.println("//////////////////////");
        for (int i = 0; i < numberRounds; i++) {
            res.setLength(0);
            subKey = sbkg.getSubkey(typeKey, key, i);
            if (typeFunc == 0) {
                right = single(right, subKey);
            } else {               
                right = xor(right, subKey);
            }
            sk.append(subKey).append('\n');
            for (int k = 0; k < left.length(); k++) {
                 res.append(Integer.toString(Integer.parseInt(String.valueOf(left.charAt(k))) ^ Integer.parseInt(String.valueOf(right.charAt(k)))));
            }
            System.out.println(right);
            System.out.println("----------------------");
            if (i < numberRounds - 1) {
                left = right;
                right = res.toString();
            } else {
                right = res.toString();
            }
            
            System.out.println(left);
            System.out.println(right);
            System.out.println("**********************");
        }
        
        return left.concat(right);
    }
    
    public String single(String left, String subKey){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < left.length(); i++) {
            res.append(Integer.toString(Integer.parseInt(String.valueOf(left.charAt(i))) ^ Integer.parseInt(String.valueOf(subKey.charAt(i)))));
        }
        return res.toString();
    }
    
    public String xor(String left, String subKey) {
        StringBuilder res = new StringBuilder();       
        String polinom = "0100000000000011";       
        Scrambler scr = new Scrambler("1000111100101111", polinom, 32);
        String scrambler = scr.scrambler();
        for (int i = 0; i < left.length(); i++) {
            res.append(Integer.toString(Integer.parseInt(String.valueOf(left.charAt(i))) ^ Integer.parseInt(String.valueOf(scrambler.charAt(i))) ^ Integer.parseInt(String.valueOf(subKey.charAt(i)))));
        }
        System.out.println("XOR start");
         System.out.println(scrambler);
          System.out.println(left);
           System.out.println(subKey);
            System.out.println(res.toString());
             System.out.println("XOR finish");
        return res.toString();
    }
    
}
