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
        
        findAndInputUsernames();
        countErrors();
        HashSet<String> months = sortErrorsByMonths();
        inputErrorsByMonths(months);
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

    
    // function: read raw datas, find and count errors caused by users
    public static void countErrors(){
            try {
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
                PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                    
                    String line;     
                    int count = 0;
                    while((line = reader.readLine()) != null)
                    {
                        Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*");
                        Matcher matcher = pattern.matcher(line);
                            if(matcher.matches()){
                                writer.println(line);
                                count++;
                        }
                    }
                    System.out.println("Total errors caused by users are: " + count);
                    System.out.println(" ");
                    writer.close();
                    reader.close();
           }catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e){}       
    }


    // function: write the usernames into a file named "usernames.txt".
    public static void findAndInputUsernames(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt"));
                HashSet<String> users = new HashSet<>();
                String line;     

                while((line = reader.readLine()) != null)
                {
                    Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*(user=)(.*)(,).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()) {                            
                            writer.println(matcher.group(3));
                            users.add(matcher.group(3));
                    }
                    
                }
                System.out.println("Users list:\n " + users);
                System.out.println("The number of users are: " + users.size());
                System.out.println(" ");
                writer.close();
                reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
    }


    // function: sort errors into different months
    public static HashSet<String> sortErrorsByMonths() {
        HashSet<String> months = new HashSet<>();
        try { BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                String line;     
                
               
                while((line = reader.readLine()) != null)
                    {   
                        Pattern pattern = Pattern.compile("\\[.*-(.*)-[0-9]{2}.*\\].*(error: This association).*");
                        Matcher matcher = pattern.matcher(line);
                                if(matcher.matches()) {
                                    months.add(matcher.group(1));
                            }
                    }
                    
                    reader.close();
                   
            } catch (FileNotFoundException ex) {System.out.println("File not found");}
              catch (IOException e) {}    
              return months;     
       }
       
    // function: input error according to different months into its respective file.
    public static void inputErrorsByMonths(HashSet<String> months) {
         
            try { 
                BufferedReader reader = null;
                PrintWriter writer = null;
                
                String line;   
                ArrayList<String> month = new ArrayList<String> ();

                for(String set: months)
                    {
                        month.add(set);
                    } 
                        Collections.sort(month);
                        // System.out.println(month);
                                          
                     
                for (int i = 0; i < month.size(); i++)                                 
                {
                    reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                    writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\" + month.get(i) + ".txt"));

                    while((line = reader.readLine()) != null) {
                        Pattern pattern = Pattern.compile("\\[.*-(" + month.get(i) + ")-[0-9]{2}.*\\].*(error: This association).*");
                        Matcher matcher = pattern.matcher(line);

                        if(matcher.matches()) {
                            // System.out.println(line);
                            writer.println(line);
                        }
                    }

                    writer.close();
                }
            
            reader.close();                    
            } catch (FileNotFoundException ex) {System.out.println("File not found");}
             catch (IOException e) {}    
        }
}
   
   

