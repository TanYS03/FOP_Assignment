import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.io.File;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java .util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument;

import static java.lang.String.valueOf;


public class Assignment {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String find = "";
        do{
            System.out.printf("%-50s %-10s\n","Description","Command");
            System.out.println("-----------------------------------------------------------");
//            System.out.printf("%-50s %-10s\n","number of job created in particular month","created");
//            System.out.printf("%-50s %-10s\n","number of job ended in particular month","ended");
            System.out.printf("%-50s %-10s\n","number of job created/ended in particular month","jobRange");
            System.out.printf("%-50s %-10s\n","number of job created/ended in each month","allJobs");
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
                default:
                    System.out.println("Please rewrite again.");
            }
            System.out.println("*----------------------------------------------------------*");
        }while(!find.equals("-1"));
//        JOptionPane.showM
    }


    public static void allJobs(){
        Scanner input = new Scanner(System.in);
        try{
            BufferedReader inputstream = new BufferedReader (new FileReader("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
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
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
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
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            String imonth="", fmonth = null;
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};
            String[] arrayPartition = {"gpu-k10","gpu-k40c","gpu-v100s","gpu-titan","cpu-opteron","cpu-epyc"};
            int[] numPartition =new int[6];

            System.out.println("Which month do you want to see?(june...december or all)"); //To ask the range of month or all month
            String ask = input.next();

            if(!ask.equalsIgnoreCase("all")) {
                System.out.println("To :");
                String month2 = input.next();

                for (int i = 0; i < arrayMonth.length; i++) {
                    if (ask.equalsIgnoreCase(Month[i]))
                        imonth = arrayMonth[i];
                    if (month2.equalsIgnoreCase(Month[i]))
                        fmonth = arrayMonth[i];
                }
            }

            System.out.printf("%-20s%-10s\n","Type of partition","Amount");

            if(!ask.equalsIgnoreCase("all")) {
                while (inputstream.hasNextLine()) {
                    String[] read = inputstream.nextLine().split(" "); //read the text word by word
                    String[] typePartition = read[read.length - 1].split("="); //split the partition and the type of partition

                    String date = "" + (read[0].charAt(6) - '0') + (read[0].charAt(7) - '0'); //to form a String month with two digits
                    if (date.compareTo(imonth)>=0 && date.compareTo(fmonth)<=0 ) {   //to check the month
                        for (int i = 0; i < arrayPartition.length; i++) {
                            if (typePartition[typePartition.length - 1].equals(arrayPartition[i])) {
                                numPartition[i]++;
                                break;
                            }
                        }
                    }
                    if (date.compareTo(fmonth) > 0)
                        break;  //to break if the program look the line which already pass the needed month
                }
            }

            if(ask.equalsIgnoreCase("all")){
                while(inputstream.hasNextLine()) {
                    String[] read = inputstream.nextLine().split(" "); //read the text word by word
                    String[] typePartition = read[read.length-1].split("="); //split the partition and the type of partition

                    for (int i = 0; i < arrayPartition.length; i++)
                        if (typePartition[typePartition.length-1].equals(arrayPartition[i]) ) {
                            numPartition[i]++;
                            break;
                        }
                }
            }
            for (int i = 0; i < numPartition.length; i++)
                System.out.printf("%-20s%-10d\n",arrayPartition[i],numPartition[i]);


            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }
    }
 public static void tables(String name, String amount, String[] x,  int[] y)  {
     try {
//     }catch(Exception e) {
//         System.out.println("IS you");
//     }
//     try{
         XSSFWorkbook workbook = new XSSFWorkbook();
         System.out.println("ds");
        XSSFSheet sheet = workbook.createSheet("Data");

//        XSSFRow row;

        Map<String, Object[]> Data = new TreeMap<String, Object[]>();

        Data.put("1",new Object[]{name,amount});
        for (int i = 0; i < y.length; i++) {
            Data.put(valueOf(i+2),new Object[]{x[i],valueOf(y[i])});
        }

        Set<String> keyid = Data.keySet();

        int rowid=0;
        //writing the data into the sheets.

        for(String tableData : keyid){
            Row row = sheet.createRow(rowid++);
            Object[] objectArr = Data.get(tableData);
            int cellid=0;

            for (Object obj : objectArr){
                Cell cell = row.createCell(cellid++);
                if(obj instanceof String)
                    cell.setCellValue((String)(obj));
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
//                cell.setCellValue(valueOf(obj));
            }
        }
        //
         System.out.println("hello");
        File file = new File("d:/UM/WIX1002 Fundamentals of Programming/try1.xlsx");
         FileOutputStream out = new FileOutputStream(file) ;

         workbook.write(out);
         out.close();

         System.out.println("success");
     }catch(Exception e){
         System.out.println(e.getMessage());
     }


    }

public static void check()  {
        try{
            PrintWriter outputstream = new PrintWriter(new FileOutputStream("D:/UM/WIX1002 Fundamentals of Programming/check.txt"));
            Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            while(inputstream.hasNextLine()){
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[(.*)] sched: Allocate (.*)");
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
            Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
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

}
