package org.example;

import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java .util.regex.Matcher;

public class Assignment {
    private static final String path = "D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String find = "";
        do{
            System.out.printf("%-50s %-10s\n","Description","Command");
            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-50s %-10s\n","number of job created/ended in particular month","jobRange");
            System.out.printf("%-50s %-10s\n","number of job created/ended in each month","allJobs");
            System.out.printf("%-50s %-10s\n","number of job created/ended in particular time range","day");
            System.out.printf("%-50s %-10s\n","number of partitions","partition");
            System.out.print("What do you want to find?: ");
            find = input.nextLine();

            switch(find){
                case "allJobs":
                    allJobs();
                    break;
                case "jobRange":
                    jobRange();
                    break;
                case "partition":
                    partition();
                    break;
                case "-1":
                    System.out.println("Thank you for using our program （^^)");
                    break;
                case "check":
                    check();
                    break;
                case "check2":
                    check2();
                    break;
                case "day":
                    JobRangeDay();
                    break;
                default:
                    System.out.println("Please rewrite again.");
            }
            System.out.println("*----------------------------------------------------------*");
        }while(!find.equals("-1"));
    }


    public static void allJobs(){
        Scanner input = new Scanner(System.in);
        try{
            BufferedReader inputstream = new BufferedReader (new FileReader(path));
            int month=0;
            String check="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            System.out.print("Find job created or ended (C/E): ");
            char choice = input.next().charAt(0);

            if(choice=='C' || choice =='c')
                check="\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";//
            else if(choice=='E' || choice =='e')
                check="\\[(.*)] _job_complete: JobId=(\\d+) done";

            System.out.println("------------------------------------");
            System.out.printf("| %-15s | %-15s|\n","Month","Number of jobs");
            System.out.println("------------------------------------");
            int[] countAmount = new int[7];

                String line;

                for (int i = 0; i < arrayMonth.length; i++) {
                    while((line = inputstream.readLine())!= null) {
                        Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                        Matcher matcherDate = patternDate.matcher(line);
                        Pattern pattern = Pattern.compile(check);
                        Matcher matcher = pattern.matcher(line);
                        matcherDate.find();
                        if(!matcherDate.group(1).equals(arrayMonth[i]) && matcher.matches()){
                                countAmount[i+1]++;
                                break;
                        }

                            if(matcher.matches())
                                countAmount[i]++;

                    }
                    System.out.printf("| %-15s | %5s%-10d|\n",Month[i]," ",countAmount[i]);
                }

            System.out.println("------------------------------------\n");
            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }

    }

    public static void jobRange(){
        Scanner input = new Scanner(System.in);
        String imonth="", fmonth = null;

        System.out.print("Find job created or ended (C/E): ");
        char choice = input.next().charAt(0);

        try{
            Scanner inputstream = new Scanner (new FileInputStream(path));
            String check="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            if(choice=='C' || choice =='c')
                check="\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";//
            else if(choice=='E' || choice =='e')
                check="\\[(.*)] _job_complete: JobId=(\\d+) done";


            System.out.print("Range of month from (june...december.) : ");//input initial month
            String month1 = input.next();
            System.out.print("To : ");  //input until month
            String month2 = input.next();

            for (int i = 0; i < arrayMonth.length; i++) {
                if(month1.equalsIgnoreCase(Month[i]))
                    imonth = arrayMonth[i];
                if(month2.equalsIgnoreCase(Month[i]))
                    fmonth = arrayMonth[i];

            }
            int num=0;

                while(inputstream.hasNextLine()) {
                    String read = inputstream.nextLine(); //read the text word by word
                    Pattern pattern = Pattern.compile(check);
                    Matcher matcher = pattern.matcher(read);
                    Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                    Matcher matcherDate = patternDate.matcher(read);

                    matcherDate.find();
                    if (matcherDate.group(1).compareToIgnoreCase(imonth)>=0 && matcherDate.group(1).compareToIgnoreCase(fmonth)<=0 ) {   //to check the month
                            if (matcher.matches()) num++; //to compare the word allocate for job created and job_complete for job ended
                        }
                    if (matcherDate.group(1).compareTo(fmonth)>0)
                        break;  //to break if the program look the line which already pass the needed month
                }


            System.out.printf("%s to %s : %d\n",month1,month2,num);
            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }


    }

    public static void partition(){
        Scanner input = new Scanner(System.in);
        String imonth="", fmonth = "";
        try{
            Scanner inputstream = new Scanner (new FileInputStream(path));
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};
            String[] arrayPartition = {"gpu-k10","gpu-k40c","gpu-v100s","gpu-titan","cpu-opteron","cpu-epyc"};
            int[] numPartition =new int[6];

            System.out.print("Which month do you want to see?(june...december): "); //To ask the range of month or all month
            String ask = input.next();
            System.out.print("To :");
            String month2 = input.next();

            for (int i = 0; i < arrayMonth.length; i++) {
                if (ask.equalsIgnoreCase(Month[i]))
                    imonth = arrayMonth[i];
                if (month2.equalsIgnoreCase(Month[i]))
                    fmonth = arrayMonth[i];
                }

            System.out.printf("%-20s%-10s\n","Type of partition","Amount");

            while(inputstream.hasNextLine()){
                String read = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.*)T(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
                Matcher matcher = pattern.matcher(read);
                Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                Matcher matcherDate = patternDate.matcher(read);

                matcherDate.find();
                if(matcherDate.group(1).compareToIgnoreCase(imonth)>=0 && matcherDate.group(1).compareToIgnoreCase(fmonth)<=0) {
                    if(matcher.matches())
                        for (int i = 0; i < arrayPartition.length; i++) {
                        if (matcher.group(7).equalsIgnoreCase(arrayPartition[i]) ) {
                            numPartition[i]++;
                            break;
                        }
                    }
                }
                if(matcherDate.group(2).compareTo(fmonth)>0) break;
            }

            for (int i = 0; i < numPartition.length; i++)
                System.out.printf("%-20s%-10d\n",arrayPartition[i],numPartition[i]);
            inputstream.close();
            if(imonth.equals("06")&&fmonth.equals("12")){//only show graph when user want june to december

            }
        }catch (IOException e){
            System.out.println("Input file problem");
        }
        System.out.println("Which partition do you want to look");
        String partition = input.next();
        partitionDetail(partition, imonth, fmonth);
    }
public static void check()  {
        try{
            PrintWriter outputstream = new PrintWriter(new FileOutputStream("D:/UM/WIX1002 Fundamentals of Programming/check.txt"));
            Scanner inputstream = new Scanner(new FileInputStream(path));
            while(inputstream.hasNextLine()){
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.*)T(.*) _slurm_rpc_kill_job: (.*)");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches())
                    outputstream.println(line);

            }
            inputstream.close();
            outputstream.flush();
        }catch(IOException e ){
            e.getMessage();
        }

    }

    public static void check2(){
        try{
            PrintWriter outputstream = new PrintWriter(new FileOutputStream("D:/UM/WIX1002 Fundamentals of Programming/check1.txt"));
            Scanner inputstream = new Scanner(new FileInputStream(path));
            while(inputstream.hasNextLine()){
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d) Partition=(.*)");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches())
                    outputstream.println(line);

            }
            inputstream.close();
            outputstream.flush();
        }catch(IOException e ){
            e.getMessage();
        }

    }

    protected String[] month;
    protected int[] amount;


    public static void partitionDetail(String partition, String imonth, String fmonth){
        int count =0;
    try{
        Scanner inputstream = new Scanner(new FileInputStream(path));
        System.out.println("------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-15s|\n","Date","Time","Job ID");
        System.out.println("------------------------------------------------------");
        while(inputstream.hasNextLine()){
            String line = inputstream.nextLine();
            Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
            Matcher matcher = pattern.matcher(line);
            Pattern patternPrint = Pattern.compile("\\[(.*)T(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
            Matcher matcherPrint = patternPrint.matcher(line);

            matcher.find();
            if(matcher.group(1).compareToIgnoreCase(imonth)>=0 && matcher.group(1).compareToIgnoreCase(fmonth)<=0){
                if(matcherPrint.matches())
                if(matcherPrint.group(6).equalsIgnoreCase(partition))
                    System.out.printf("| %-15s | %-15s | %-15s|\n",matcherPrint.group(1),matcherPrint.group(2),matcherPrint.group(3));
            }
        }

        inputstream.close();
    }catch(IOException e){
        e.getMessage();
    }
        System.out.println("------------------------------------------------------\n");
    }

    public static void JobRangeDay(){
        try{
            Scanner inputstream = new Scanner (new FileInputStream(path));
            Scanner input = new Scanner(System.in);
            String imonth="", fmonth = null;
            int checkDouble;
            int checkmonth;

            System.out.print("Find job created or ended (C/E): ");
            char choice = input.next().charAt(0);
            String check="", date="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            if(choice=='C' || choice =='c'){
                check="\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";
                date ="\\[2022-(\\d+)-(\\d+)T(.+)] (.*)";
            }
            else if(choice=='E' || choice =='e'){
                check="\\[(.*)] _job_complete: JobId=(\\d+) done";
                date="\\[2022-(\\d+)-(\\d+)T(.+)] (.*)";
            }

            System.out.print("Range of month(june...december) and day(1-31): ");//input initial month
            String month1 = input.next();
            int day1 = input.nextInt();
            System.out.print("To : ");  //input until month
            String month2 = input.next();
            int day2 = input.nextInt();

            for (int i = 0; i < arrayMonth.length; i++) {
                if(month1.equalsIgnoreCase(Month[i]))
                    imonth = arrayMonth[i];
                if(month2.equalsIgnoreCase(Month[i]))
                    fmonth = arrayMonth[i];
            }
            String ini = day1>9? "" : "0";
            String fin = day2>9? "" : "0";
            String iday = ini+day1;
            String fday = fin +day2;
            int count =0;
            String previousDate = iday;
            String checkMonth = imonth;
            String previousDay = iday;

            System.out.println("------------------------------------------------------");
            System.out.printf("| %-15s | %-15s | %-15s|\n","Month","Day","Number");
            System.out.println("------------------------------------------------------");
            while(inputstream.hasNextLine()) {
                String line = inputstream.nextLine();
                Pattern patternDate = Pattern.compile(date);
                Matcher matcherDate = patternDate.matcher(line);
                Pattern pattern = Pattern.compile(check);
                Matcher matcher = pattern.matcher(line);

                matcherDate.find();
                if (matcher.matches()) {   //to check the month
                    if (matcherDate.group(1).compareToIgnoreCase(imonth) >= 0 && matcherDate.group(1).compareToIgnoreCase(fmonth) <= 0) {
                        if (matcherDate.group(2).compareTo(iday) == 0)
                            count++; //to compare the word allocate for job created and job_complete for job ended
                        else if (matcherDate.group(2).compareTo(iday) >= 0 && matcherDate.group(1).compareTo(fmonth) < 0) { //need to put more than 0 to prevent the initial day is not 1
                            System.out.printf("|%-15s s| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, count);
                            count = 1;
                            checkDouble = Integer.parseInt(iday) + 1;
                            iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                        } else if (matcherDate.group(2).compareTo(iday) >= 0 && matcherDate.group(2).compareTo(fday) <= 0) {// use during the final month
                            System.out.printf("|%-15s t| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(previousDate), count);
                            count = 1;
                            checkDouble = Integer.parseInt(iday) + 1;
                            iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                        } else if (matcherDate.group(2).compareTo(iday) < 0 && matcherDate.group(1).compareTo(checkMonth) > 0) {// use when the period that change month
                            System.out.printf("|%-15d r| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)) - 1, Integer.parseInt(previousDate), count);
                            count = 1;
                            checkmonth = Integer.parseInt(checkMonth) + 1;
                            checkMonth = checkmonth > 9 ? checkMonth + "" : "0" + checkmonth;
                            iday = "01";
                        }
                        else if (matcherDate.group(1).compareTo(fmonth) == 0 && matcherDate.group(2).compareTo(fday) > 0) {//for the last day
                            System.out.printf("|%-15s a| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, count);
                            break;
                        }
                        previousDate = matcherDate.group(2);
                    }
                    if(matcherDate.group(1).compareTo(fmonth)>0){//use when the last day is the end of the month
                        System.out.printf("|%-15d d| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)) - 1, Integer.parseInt(previousDate), count);
                        break;
                    }
                    int checkNo = Integer.parseInt(iday)+1;
                    String isCount = checkNo > 9 ? checkNo + "" : "0" + checkNo;
                    if(matcherDate.group(2).compareTo(isCount) >= 0 && count ==1){ //when one day is no change
                        System.out.printf("|%-15s b| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, 0);
                        checkDouble = Integer.parseInt(iday) + 1;
                        iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                    }

                }

            }
            inputstream.close();
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static void kill(){
    try{
        Scanner inputstream = new Scanner(new FileInputStream(path));

    }catch (IOException e){
        e.getMessage();
    }

    }

}


