import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

public class Test {
    public static void main(String[] args) throws IOException{
        int[] m ={2,3,4,5,6};
//        char num = m[2]+'0';
//        char x='8';
//        System.out.println(0+'0');
//
//        String u ="01234567";
//        int number =0;
//        System.out.println(u.charAt(0)-'0');
//        System.out.println(u.charAt(0)+'0');
//        System.out.println(u.charAt(1));
//        System.out.println(0);
//        System.out.println(""+(u.charAt(2)-'0')+(u.charAt(1)-'0'));
//        String a =""+(u.charAt(0)-'0')+(u.charAt(6)-'0');
//        String b =""+(u.charAt(0)-'0')+(u.charAt(7)-'0');
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(a.compareTo(b)<0);
//        if(u.charAt(0)-'0'==0) System.out.println("true");

//        String e="abc";
//        String f ="abcdefg";
//        if(e.compareTo(f)<0) System.out.println("true");
//        String path = "D:/UM/WIX1002 Fundamentals of Programming/try1.xlsx";
//        FileInputStream fileInputStream = new FileInputStream(path);
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
//        Sheet sheet = xssfWorkbook.getSheet("Sheet1");
//        Row row = sheet.getRow(0);
//        System.out.println(row.getCell(0));
        String name="fdfd";
        String amount ="amount";
        String[] x = {"a","b","c","d"};
        int[] y = {1,2,3,4};
        tables(name,amount,x,y);
    }

    public static void tables(String name, String amount, String[] x,  int[] y) throws IOException {
        InputStream inputStream = new  FileInputStream("path of Log4j2.xml"); ConfigurationSource source = new ConfigurationSource(inputStream); Configurator.initialize(null, source);
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
                    cell.setCellValue(valueOf(obj));
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
//                cell.setCellValue(valueOf(obj));
            }
        }
        //
        System.out.println("hello");
        try{

             FileOutputStream out = new FileOutputStream(new File("d:/UM/WIX1002 Fundamentals of Programming/try1.xlsx")) ;

        workbook.write(out);
        out.close();

        System.out.println("success");
     }catch(Exception e){
         System.out.println("failed");
     }


    }

}

//    public static void partition(){
//        Scanner input = new Scanner(System.in);
//        try{
//            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/org.example.Assignment/extracted_log.txt"));
//            String imonth="", fmonth = null;
//            String[] arrayMonth = {"06","07","08","09","10","11","12"};
//            String[] Month ={"June","July","August","September","October","November","December"};
//            String[] arrayPartition = {"gpu-k10","gpu-k40c","gpu-v100s","gpu-titan","cpu-opteron","cpu-epyc"};
//            int[] numPartition =new int[6];
//
//            System.out.print("Which month do you want to see?(june...december or all): "); //To ask the range of month or all month
//            String ask = input.next();
//            if(!ask.equalsIgnoreCase("all")) {
//                System.out.print("To :");
//                String month2 = input.next();
//                for (int i = 0; i < arrayMonth.length; i++) {
//                    if (ask.equalsIgnoreCase(Month[i]))
//                        imonth = arrayMonth[i];
//                    if (month2.equalsIgnoreCase(Month[i]))
//                        fmonth = arrayMonth[i];
//                }
//            }
//
//            System.out.printf("%-20s%-10s\n","Type of partition","Amount");
//
//            if(!ask.equalsIgnoreCase("all")) {
//                while (inputstream.hasNextLine()) {
//                    String read = inputstream.nextLine();//read the text word by word
//                    Pattern pattern = Pattern.compile("\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
//                    Matcher matcher = pattern.matcher(read);
//                    Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
//                    Matcher matcherDate = patternDate.matcher(read);
//                    matcherDate.find();
//                    if (matcherDate.group(1).compareTo(imonth)>=0 && matcherDate.group(1).compareTo(fmonth)<=0) {   //to check the month
//                        if (matcher.matches()) {
//                            for (int i = 0; i < arrayPartition.length; i++) {
//                                if(matcher.group(5)==arrayPartition[i]) numPartition[i]++;
//                            }
//                        }
//                    }
//                    if (matcherDate.group(1).compareTo(fmonth) > 0)
//                        break;  //to break if the program look the line which already pass the needed month
//                }
//            }
//
//
//            if(ask.equalsIgnoreCase("all")) {
//                while (inputstream.hasNextLine()) {
//                    String read = inputstream.nextLine();
//                    for (int i = 0; i < arrayMonth.length; i++) {
//                        Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
//                        Matcher matcherDate = patternDate.matcher(read);
//                        Pattern pattern = Pattern.compile("\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
//                        Matcher matcher = pattern.matcher(read);
//                        matcherDate.find();
//                        if (!matcherDate.group(1).equals(arrayMonth[i]) && matcher.matches()) {
//                            numPartition[i + 1]++;
//                            break;
//                        }
//                        if (matcher.matches())
//                            numPartition[i]++;
//                    }
//                }
//            }
//
//
//            for (int i = 0; i < numPartition.length; i++)
//                System.out.printf("| %-20s | %5s%-10d|\n",arrayPartition[i]," ",numPartition[i]);
//
////            String name = "Type of Partition";
////            String amount = "Amount";
////            tables(name,amount,arrayPartition,numPartition);
//
//            inputstream.close();
//        }catch (FileNotFoundException e){
//            System.out.println("File not found");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

