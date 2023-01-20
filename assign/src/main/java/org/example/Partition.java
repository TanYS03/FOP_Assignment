package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Partition {
    private static final String path = "D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt";

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

            System.out.println("-------------------------------------");
            System.out.printf("| %-20s | %-2s%-9s|\n","Type of partition"," ","Amount");
            System.out.println("-------------------------------------");


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
                System.out.printf("| %-20s | %3s%-8d|\n",arrayPartition[i]," ",numPartition[i]);
            System.out.println("-------------------------------------");

            inputstream.close();
            if(imonth.equals("06") && fmonth.equals("12")){//only show graph when user want june to december
                PieChartforpartition(numPartition);
            }
        }catch (IOException e){
            System.out.println("Input file problem");
        }
        System.out.println("Which partition do you want to look");
        String partition = input.next();
        partitionDetail(partition, imonth, fmonth);
    }

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

    public static void PieChartforpartition(int[] numPartition){

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("gpu-k10\n" + " 8% ", numPartition[0]);
        result.setValue("gpu-k40c\n" + " 3% ", numPartition[1]);
        result.setValue("gpu-v100s\n" + " 7% ", numPartition[2]);
        result.setValue("gpu-titan\n" + " 7% ", numPartition[3]);
        result.setValue("cpu-opteron\n" + " 49%", numPartition[4]);
        result.setValue("cpu-epyc\n" + " 26% ", numPartition[5]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Type of org.example.Partition ", result, true, true, false);
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
        plot.setSectionPaint(4, Color.RED);
        plot.setSectionPaint(5, Color.cyan);
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

}
