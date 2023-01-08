import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExtractText {
    public static void main(String[] args){
        StringBuilder str = readAndWriteTextIntoFile("C:\\Users\\User\\Downloads\\extracted_log");  
         
        countTypesOfTasks("epyc");     
        countTypesOfTasks("opteron");
        countTypesOfTasks("gpu");
        
        findAndInputUsernames();
        countErrors();
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
                    System.out.println(count);
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
                ArrayList<String> month = new ArrayList<>();
                month.add("June");
                month.add("July");
                month.add("August");
                month.add("September");
                month.add("October");
                month.add("November");
                month.add("December");
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
                System.out.println(users);
                System.out.println(users.size());
                writer.close();
                reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
    }
}
   
   

