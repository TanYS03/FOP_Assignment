import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

public class ExtractText {
    public static void main(String[] args){
        StringBuilder str = readAndWriteTextIntoFile("C:\\Users\\User\\Downloads\\extracted_log");  
         
        countTypesOfTasks("epyc");     
        countTypesOfTasks("opteron");
        countTypesOfTasks("gpu");
    }

    // function: read and write datas into a new file.
    public static StringBuilder readAndWriteTextIntoFile(String filePath) {
        StringBuilder builder = new StringBuilder(); 
            try{BufferedReader reader = new BufferedReader(new FileReader(filePath));
                PrintWriter writer = new PrintWriter(new FileWriter("extracted.txt"));                                  
                String line = reader.readLine();
                    while(line != null)
                    {
                        builder.append(line);
                        builder.append(System.getProperty("line.separator"));
                        writer.println(line);
                        line = reader.readLine();                        
                    }
                        writer.flush();
                        writer.close();
                        reader.close();
           }catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e){}        
        return builder;
    }

    //function: read and count the total number of each type of tasks.
    public static void countTypesOfTasks(String tasks) {        
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
                PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                String line = reader.readLine();               
                int count = 0;

                    while(line != null) 
                    {             
                        if(line.contains(tasks)) {
                            writer.println(line);
                            count++;
                        }                      
                        line = reader.readLine(); 
                    }
                    System.out.println( "The number of '" + tasks + "' found is: " + count);
                    System.out.println(" ");
           }catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e){}           
    }    
}
   
   

