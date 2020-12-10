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
public class Scrambler {
    
    private String value;
    private String polinom;
    private int lengthSubKey;
    
    public Scrambler(String value, String polinom, int lengthSubKey)
    {
        this.value = value;
        this.polinom = polinom;
        this.lengthSubKey = lengthSubKey;
    }
    
    public String scrambler() {
        String input;
        int[] numbers = new int[this.polinom.length()];
        for (int i = 0; i < this.polinom.length(); i++) {
            numbers[i] = Integer.parseInt(String.valueOf(this.polinom.charAt(i)));
        }
        StringBuilder sb = new StringBuilder(this.polinom.length());
        for (int i = 0; i < this.polinom.length(); i++) {
            sb.append(this.value.charAt(this.polinom.length() - 1));
            input = getInput(this.value, numbers);
            this.value = rightShift(this.value, input);
        }
        return getFullKey(sb.toString());
    }
    
    
    private String getInput(String valueBin, int[] numbers) {
        String res = "";
        for (int i = 0; i < valueBin.length(); i++) {
                if (numbers[i] == 1) {
                    if ("".equals(res)) {
                        res = String.valueOf(valueBin.charAt(i));
                    } else {
                        res = Integer.toString(Integer.parseInt(res) ^ Integer.parseInt(String.valueOf(valueBin.charAt(i))));
                    }
                }
        }
        return res;
    }
    
    private String rightShift(String valueBin, String input) {
        return input + valueBin.substring(0, valueBin.length() - 1);
    }
    
    private String getFullKey(String key) {
        String res = "";     
        int count =  this.lengthSubKey / key.length();    
        for (int i = 0; i < count; i++) {
            res += key;
        }
        if(res.length() <  this.lengthSubKey) {
            res += key.substring(0, (this.lengthSubKey % key.length()));
        }
        
        return res;
    }
}
