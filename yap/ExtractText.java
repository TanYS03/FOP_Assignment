import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ExtractText {
    public static void main(String[] args){
        StringBuilder str = readAndWriteTextIntoFile("C:\\Users\\User\\Downloads\\extracted_log");  
        countTypesOfTasks("error");  
        countTypesOfTasks("epyc");     
        countTypesOfTasks("opteron");
        countTypesOfTasks("gpu");
        findKeyWords("C:\\Users\\User\\Downloads\\extracted_log");
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
           }catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e){}           
    }    

    //function: read through the text files and sort out different types of partititons.
    public static void findKeyWords(String filePath){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                PrintWriter writer = new PrintWriter("C:\\Users\\User\\Downloads\\keyword.txt");
                    ArrayList<String> ls = new ArrayList<String>();
                    HashSet<String> set = new HashSet<String>();
                    String line;

                    while((line = reader.readLine()) != null) {
                        Pattern pattern = Pattern.compile("\\[.*\\] (.*): (.*)");
                        Matcher matcher = pattern.matcher(line);
                            if (matcher.matches()) {         
                                ls.add(matcher.group(1));
                            }
                    }
                    ls.forEach(set::add);
                    set.forEach(writer::println);
                    writer.close();
        }catch(FileNotFoundException ex){System.out.println("File  not found");}
            catch(IOException ex){}
    }       


    public static void countErrors(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
            
                String line;      

                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("error:");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                        System.out.println(line);
                    }
                }
                writer.close();
                reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
    }
}
   
   

