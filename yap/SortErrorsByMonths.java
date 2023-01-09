import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
public class SortErrorsByMonths {

    public static void main(String[] args){
        
        findAndInputUsernames();
        countErrors();
        HashSet<String> months = sortErrorsByMonths();
        inputErrorsByMonths(months);
        ArrayList<String> month = inputErrorsByMonths(months);
        getAssociationNumber(month);
        HashSet<String> users = findAndInputUsernames();
        displayInfoInTable(users);
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
public static HashSet<String> findAndInputUsernames(){
    HashSet<String> users = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt"));
                
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
    return users;       
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
public static ArrayList<String> inputErrorsByMonths(HashSet<String> months) {
     ArrayList<String> month = new ArrayList<String> ();
    try { 
            BufferedReader reader = null;
            PrintWriter writer = null;
            
            String line;   
            

            for(String set: months)
                {
                    month.add(set);
                } 
                    Collections.sort(month);
                    // System.out.println(month);
                                                           
            for (int i = 0; i < month.size(); i++)                                 
            {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + month.get(i) + ".txt"));

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
          return month;
    }    


    // function: get association number of each line of error from text files.
    public static void getAssociationNumber(ArrayList<String> months){
        try{
            BufferedReader reader = null;
            PrintWriter writer = null;
            String line;
            
            for(int i = 0; i < months.size(); i++)
            {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + months.get(i) + ".txt"));
                writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\Association_num_month" + months.get(i) + ".txt"));
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[.*-(" + months.get(i) + ")-[0-9]{2}.*\\] (error: This association) ([0-9]*).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                            // System.out.println(matcher.group(3));
                            writer.println(matcher.group(3));
                        }

                }writer.close();
            }
            
            reader.close();
        }catch(FileNotFoundException e){System.out.println("File not found");}
         catch(IOException ex){}
    }

    // function: display username, time and association number in a table
    public static void displayInfoInTable(HashSet<String> users){
        ArrayList<String> usersname = new ArrayList<String>();
        try{
            BufferedReader reader = null;
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\Errors_Table.txt"));
            for (String sets : users) {
                usersname.add(sets);
            }
            Collections.sort(usersname);
            System.out.println(usersname);

            String line;
            writer.println(" ");
            writer.printf(" %-18s |  %-30s          |  %-15s\n","Name","                 Time","Associations num");
            for (String name : usersname) {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*");
                    Matcher matcher = pattern.matcher(line);
                        if(line.contains(name) && matcher.matches()){
                            writer.printf(" %-20s |    %-26s|  %-15s\n",name, matcher.group(1),matcher.group(3));
                        }
                }
            }
            reader.close();
            writer.flush();
            writer.close();
        }catch(FileNotFoundException ex){System.out.println("File not found");}
         catch(IOException ex){}
    }
}
