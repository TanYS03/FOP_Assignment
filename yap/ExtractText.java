import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExtractText {
    public static void main(String[] args){
        // StringBuilder str = readAndWriteTextIntoFile("C:\\Users\\User\\Downloads\\extracted_log");
        // System.out.println(str);
        countTypesOfTasks("_job_complete");  
        countTypesOfTasks("slurm_rpc");   
        countTypesOfTasks("sched");
        countTypesOfTasks("get_job");
        countTypesOfTasks("job_step");
        countTypesOfTasks("error:");
        countTypesOfTasks("job_create");
    }

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

    public static void countTypesOfTasks(String keywords){        
            try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"))){
                String line = reader.readLine();               
                int count = 0;
                    while(line != null)
                    {             
                        if(line.contains(keywords)){
                            count++;
                        }                      
                        line = reader.readLine();
                    }
                        System.out.println(count);
           }catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e){}           
    }    
}
   
   

