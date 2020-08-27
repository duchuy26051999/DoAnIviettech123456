package com.example.Auction.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestInput {
    public int space(String input){
        int count = 0;
        for(int i = 0; i < input.length(); i++){
            char kt = input.charAt(i);
            if(Character.isSpace(kt)){
                count++;
            }
        }
        return count;
    }

    public int characterCheck(String input){
        int count = 0;
        for(int i = 0; i < input.length(); i++){
            count++;
        }
        return count;
    }

    public boolean checkNumber(String i){
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasDigit = digit.matcher(i);
        return hasDigit.find();
    }
}
