package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String line= " fdfdff ddkf kdcf";
        Pattern pattern = Pattern.compile(" fdfdff d(.+) kdcf");
        Matcher matcher = pattern.matcher(line);
        Pattern pattern1 = Pattern.compile(" fdfdff (.+) (.+)");
        Matcher matcher1 = pattern1.matcher(line);
        matcher.find();
        matcher1.find();
        if(matcher.group(1).compareTo("dkf")==0)
        System.out.println(matcher.group(1));
        if(matcher1.group(1).compareTo("ddkf")==0)
        System.out.println(matcher1.group(1));

    }
}