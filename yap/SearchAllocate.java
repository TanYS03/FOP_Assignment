import java.io.*;
import java.util.Scanner;

public class SearchAllocate {
    public static void main(String[] args){
        StringBuilder str = getTextFromFile("C:\\Users\\User\\Downloads\\extracted_log");
        System.out.println(str);
    }
         

    public static StringBuilder getTextFromFile(String filePath) {
               
        StringBuilder builder = new StringBuilder(); 
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
        
        String line = reader.readLine();
        while(line != null)
        {
             builder.append(line);
             builder.append(System.getProperty("line.separator"));
             line = reader.readLine();
        }
            
        }catch (FileNotFoundException ex) {
        }catch (IOException e){}
        
        return builder;
    }
}
   

