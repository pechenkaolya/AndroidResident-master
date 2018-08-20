package com.buildinglink.mainapp.additionalClasses;
import java.util.Random;

public class RandomValueGenerator {

    public static String generateRandomValue(int len, String type) {
        Random random = new Random();
        String result = "";
        String dic = "";
        switch(type){
            case "numeral": dic = "0123456789";
                break;
            case "string": dic = "abcdefghijklmnopqrstuvwxyz";
                break;
            case "numString": dic = "0123456789abcdefghijklmnopqrstuvwxyz";
        }
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            result += dic.charAt(index);
        }
        return result;
    }

    public static String generateRandomValue(int maxValue){
        Random random = new Random();
        int getRandomValue = random.nextInt(maxValue);
        String strRandomValue = Integer.toString(getRandomValue);
        return strRandomValue;
    }
}
