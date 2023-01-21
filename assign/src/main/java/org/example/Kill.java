package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kill {
    private static final String path = "D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt";
    private static final String kill = "D:/UM/WIX1002 Fundamentals of Programming/Assignment/check.txt";

    public static void main(String[] args) {
        kill();
        totalkill();
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
                if(line.contains("_slurm_rpc_kill_job:")){
                    if(line.contains("REQUEST_KILL_JOB")) checkMonth(line,requestCount);
                    if(line.contains("job_str_signal()")) checkMonth(line,failureCount);
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

    static void totalkill(){
        String[] successDate = new String[2336];
        String[] successRequest = new String[2336];
        String[] successId = new String[2336];
        String[] successUid = new String[2336];
        String[] failDate = new String[44];
        String[] failRequest = new String[44];
        String[] failId = new String[44];
        String[] failUid = new String[44];
        String[] failReason = new String[44];
        int successCount =0;
        int failCount =0;
        String id = null;

        try{
            Scanner inputstream = new Scanner(new FileInputStream(path));
            while(inputstream.hasNextLine()){
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (REQUEST_KILL_JOB) JobId=(\\d+) uid (\\d+)");
                Matcher matcher = pattern.matcher(line);
                Pattern failedpattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (job_str_signal(.+)) uid=(\\d+) JobId=(\\d+) sig=9 returned: (.+)");
                Matcher failedmatcher = failedpattern.matcher(line);
                matcher.find();
                failedmatcher.find();
                if(line.contains("job_str_signal()")){
                    if(failedmatcher.group(5).compareToIgnoreCase(id)==0){
                        successDate[successCount-1]=null;
                        successRequest[successCount-1]=null;
                        successId[successCount-1]=null;
                        successUid[successCount-1]=null;
                        successCount--;
                    }
                    failDate[failCount]= failedmatcher.group(1);
                    failRequest[failCount]=failedmatcher.group(2);
                    failId[failCount]=failedmatcher.group(5);
                    failUid[failCount]=failedmatcher.group(4);
                    failReason[failCount] = failedmatcher.group(6);
                    failCount++;
                }
                if(matcher.matches()){
                    successDate[successCount]= matcher.group(1);
                    successRequest[successCount]=matcher.group(2);
                    successId[successCount]=matcher.group(3);
                    successUid[successCount]=matcher.group(4);
                    id = matcher.group(3);
                    successCount++;
                }
            }
            inputstream.close();
            System.out.println(successCount);

            System.out.println("Succes kill job");
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.printf("|%9s%-5s%9s | %7s%-7s%8s | %3s%-5s%3s | %6s%-3s%6s|\n"," ","Date"," "," ","Request"," "," ","JobId"," "," ","Uid"," ");
            System.out.println("+--------------------------------------------------------------------------------+");
            for (int i = 0; i < successDate.length; i++) {
                System.out.printf("|%-23s | %3s%-19s | %3s%-5s%3s | %3s%-9s%3s|\n",successDate[i]," ",successRequest[i]," ",successId[i]," "," ",successUid[i]," ");
            }
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.printf("|%-12s : %-63d |\n","Total success",successCount);
            System.out.println("+--------------------------------------------------------------------------------+");
            System.out.println();

            System.out.println("Fail kill job");
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%9s%-5s%9s | %7s%-7s%8s | %3s%-5s%4s | %6s%-3s%6s | %9s%-6s%10s|\n"," ","Date"," "," ","Request"," "," ","JobId"," "," ","Uid"," "," ","Reason"," ");
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < failDate.length; i++) {
                System.out.printf("|%-23s | %3s%-19s | %3s%-8s%1s | %3s%-9s%3s | %-18s |\n",failDate[i]," ",failRequest[i]," ",failId[i]," "," ",failUid[i]," ",failReason[i]);
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-12s : %-93d |\n","Total fail",failCount);
            System.out.println("+-------------------------------------------------------------------------------------------------------------+");


        }catch(IOException e){
            e.getMessage();
        }
    }
}
