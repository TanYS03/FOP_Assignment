package org.example;

import org.jfree.data.general.DefaultPieDataset;

import java.io.*;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.util.regex.Pattern;
import java .util.regex.Matcher;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import java.awt.*;

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
//                    partition();
                    break;
                case "-1":
                    System.out.println("Thank you for using our program （^^)");
                    break;
                case "check":
//                    check();
                    break;
                case "check2":
//                    check2();
                    break;
                case "day":
                    JobRangeDay();
                    break;
                case "kill":
                    kill();
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
                if(choice=='C' || choice =='c') PieChartforjobcreated(countAmount);
                if(choice=='E' || choice =='e') PieChartforjobended(countAmount); //for graph
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

    public static void PieChartforjobcreated(int[] countAmount){

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 24% ", countAmount[0]);
        result.setValue("July\n" + " 14% ", countAmount[1]);
        result.setValue("August\n" + " 13% ", countAmount[2]);
        result.setValue("September\n" + " 14% ", countAmount[3]);
        result.setValue("October\n" + " 19% ", countAmount[4]);
        result.setValue("November\n" + " 10% ", countAmount[5]);
        result.setValue("December\n" + " 6% ", countAmount[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total Number of Job Created ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);


        PiePlot3D plot=(PiePlot3D)chart.getPlot();
        plot.setSectionPaint(0, Color.magenta);
        plot.setSectionPaint(1, Color.LIGHT_GRAY);
        plot.setSectionPaint(2, Color.CYAN);
        plot.setSectionPaint(3, Color.YELLOW);
        plot.setSectionPaint(4, Color.blue);
        plot.setSectionPaint(5, Color.RED);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial",Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void PieChartforjobended(int[] countAmount){

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 22% ", countAmount[0]);
        result.setValue("July\n" + " 13% ", countAmount[1]);
        result.setValue("August\n" + " 14% ", countAmount[2]);
        result.setValue("September\n" + " 14% ", countAmount[3]);
        result.setValue("October\n" + " 18% ", countAmount[4]);
        result.setValue("November\n" + " 13% ", countAmount[5]);
        result.setValue("December\n" + " 5% ", countAmount[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total Number of Job Ended ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);


        PiePlot3D plot=(PiePlot3D)chart.getPlot();
        plot.setSectionPaint(0, Color.GREEN);
        plot.setSectionPaint(1, Color.ORANGE);
        plot.setSectionPaint(2, Color.BLUE);
        plot.setSectionPaint(3, Color.magenta);
        plot.setSectionPaint(4, Color.YELLOW);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial",Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
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
        System.out.printf("| %-15s | %-15s|\n","Month","Success request");
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


