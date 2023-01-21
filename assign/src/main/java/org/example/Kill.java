package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kill {
    private static final String path = "D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt";

    public static void main(String[] args) {
        kill();
    }

    public static void kill(){
        int[] sucessCount = new int[7];
        int[] requestCount = new int[7];
        int[] failureCount = new int[7];
        String[] Month ={"June","July","August","September","October","November","December"};

        try{
            Scanner inputstream = new Scanner(new FileInputStream(path));
            while(inputstream.hasNextLine()){
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[2022-(.+)] _slurm_rpc_kill_job: (.+) JobId:(\\d) uid (.+)");
                Matcher matcher = pattern.matcher(line);
                Pattern pattern1 = Pattern.compile("\\[*(\\d{4}-\\d{2}-\\w+:\\d{2}:\\d{2}.\\d{3})]* (.*)job: (\\w*_*\\w*_*\\w*) JobId=(\\d+) uid (\\d+)");
                Matcher matcher1 = pattern1.matcher(line);
                matcher.find();
//                matcher1.find();

                if(matcher1.matches()||matcher.matches()){
//                    matcher.find();
                    if(matcher.group(2).compareTo("REQUEST_KILL_JOB")==0) checkMonth(line,requestCount);
//                    matcher1.find();
                    if(matcher1.group(2).compareTo("job_str_signal()")==0) checkMonth(line,failureCount);
                }
            }
            inputstream.close();
        }catch (IOException e){
            e.getMessage();
        }
        for (int i = 0; i < sucessCount.length; i++) {
            sucessCount[i] = requestCount[i] - failureCount[i];
        }
        System.out.println("------------------------------------");
        System.out.printf("| %-15s | %-15s|\n","Month","Number of success request");
        System.out.println("------------------------------------");
        for (int i = 0; i < sucessCount.length; i++) {
            System.out.printf("| %-15s | %5s%-10d|\n",Month[i]," ",sucessCount[i]);
        }
        System.out.println("------------------------------------");

        System.out.println("------------------------------------");
        System.out.printf("| %-15s | %-15s|\n","Month","Failure request");
        System.out.println("------------------------------------");
        for (int i = 0; i < sucessCount.length; i++) {
            System.out.printf("| %-15s | %5s%-10d|\n",Month[i]," ",failureCount[i]);
        }
        System.out.println("------------------------------------");

    }

    static void checkMonth(String line, int[] monthCount){
        Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.+)](.+)");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        switch(matcher.group(1)){
            case "06" :
                monthCount[0]++;
                break;
            case "07":
                monthCount[1]++;
                break;
            case "08":
                monthCount[2]++;
                break;
            case "09":
                monthCount[3]++;
                break;
            case "10":
                monthCount[4]++;
                break;
            case "11":
                monthCount[5]++;
                break;
            case "12":
                monthCount[6]++;
                break;
        }
    }

}
