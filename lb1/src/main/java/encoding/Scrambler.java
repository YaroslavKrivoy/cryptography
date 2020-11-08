/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encoding;

/**
 *
 * @author yaroslav
 */
public class Scrambler {
    
    private int value = 122;
    
    public String scrambler(String polinom, int lengthText) {
        String input;
        String[] snumbers = polinom.split(" ");
        int[] numbers = new int[snumbers.length];
        for (int i = 0; i < snumbers.length; i++) {
            numbers[i] = Integer.parseInt(snumbers[i].trim());
        }
        StringBuilder sb = new StringBuilder(numbers[0]+1);
        String valueBin = intToBinary(value, numbers[0]+1);
        for (int i = 0; i <= numbers[0]; i++) {
            sb.append(valueBin.charAt(numbers[0]));
            input = getInput(valueBin, numbers);
            valueBin = rightShift(valueBin, input);
        }
        return getFullKey(sb.toString(), lengthText);
    }
    
    private String intToBinary(int value, int lengthRegister) {
        String format = "%" + lengthRegister + "s";
        String result = Integer.toBinaryString(value);
        return String.format(format, result).replaceAll(" ", "0");
    }
    
    private String getInput(String valueBin, int[] numbers) {
        String res = "";
        for (int i = 0; i < valueBin.length(); i++) {
            for (int j = 1; j < numbers.length; j++) {
                if (i == numbers[j]) {
                    if ("".equals(res)) {
                        res = String.valueOf(valueBin.charAt(i));
                    } else {
                        res = Integer.toString(Integer.parseInt(res) ^ Integer.parseInt(String.valueOf(valueBin.charAt(i))));
                    }
                }
            }
        }
        return res;
    }
    
    private String rightShift(String valueBin, String input) {
        return input + valueBin.substring(0, valueBin.length() - 1);
    }
    
    private String getFullKey(String key, int lengthText) {
        String res = "";     
        int count = lengthText / key.length();    
        for (int i = 0; i < count; i++) {
            res += key;
        }
        if(res.length() < lengthText) {
            res += key.substring(0, (lengthText % key.length()));
        }
        
        return res;
    }
    
}
