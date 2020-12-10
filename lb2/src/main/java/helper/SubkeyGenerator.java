/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author yaroslav
 */
public class SubkeyGenerator {
    
    private final int BIT_SUBKEY_LENGTH = 32;
    
    public String getSubkey(int type, String key, int round) {
        String subkey;
        if (type == 0) {
            subkey = getSubkeyCycle(key, round, BIT_SUBKEY_LENGTH);
        } else {
            subkey = getSubkeyScrambler(key, round);
        }
        return subkey;
    }
    
    private String getSubkeyCycle(String key, int round, int subKeyLength) {
        String subKey;
        if (key.substring(round).length() < subKeyLength) {
            subKey = key.substring(round);
            subKey += key.substring(1, subKeyLength - subKey.length());
        } else {
            subKey = key.substring(round, round + subKeyLength);
        }
        return subKey;
    }
    
    private String getSubkeyScrambler(String key, int round) {
        String value = getSubkeyCycle(key, round, 8);
        String polinom = "00000011";
        Scrambler scr = new Scrambler(value, polinom, 32);
        return scr.scrambler();
    }
}
