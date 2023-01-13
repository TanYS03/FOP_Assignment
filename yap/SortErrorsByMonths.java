import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import javax.swing.*;

public class SortErrorsByMonths {
    
    public static final String DEFAULT = "C:\\Users\\User\\Downloads\\extracted_log";
    public static final String DEFAULT1 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt";
    public static final String DEFAULT2 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt";

    public static void main(String[] args) {
        
        while(true){
        String in = JOptionPane.showInputDialog (
         "1 --> Check total errors caused by users.\n" + 
         "2 --> Display a table with usernames, time, association number and types of errors.\n" + 
         "3 --> Show a username list and number of errors caused by each user.\n" + 
         "4 --> Count errors in a given range of months.\n" + 
         "Q --> Quit the system." ) ;

        Scanner input = new Scanner(System.in);
        // System.out.println(" ");
        // System.out.println("_".repeat(90) + "\n");
        // System.out.println("1 --> Check total errors caused by users.");
        // System.out.println("2 --> Display a table with usernames, time, association number and types of errors.");
        // System.out.println("3 --> Show a username list and number of errors caused by each user.");
        // System.out.println("4 --> Count errors in a given range of months.");
        // System.out.println("Q --> Quit the system.");
        // System.out.println("_".repeat(90) + "\n");
        // System.out.print("Please enter your command --> ");
        // String cmd = input.nextLine();

        HashSet<String> months = sortErrorsByMonths();
        inputErrorsByMonths(months);
        ArrayList<String> month = inputErrorsByMonths(months);
        getAssociationNumber(month);
        HashSet<String> users = findAndInputUsernames();

            if(in.equals("1")){
                int errors = countErrors();
                JOptionPane.showMessageDialog(null, "Total number of errors caused by users : " + errors, "Total errors", JOptionPane.INFORMATION_MESSAGE);
            }
            
            else if(in.equals("2")){
                JOptionPane.showMessageDialog(null, "Table will be displayed in the terminal.", "Table display", JOptionPane.INFORMATION_MESSAGE);
                displayInfoInTable(users);
            }

            else if(in.equals("3")) {
                JOptionPane.showMessageDialog(null, "List will be displayed in the terminal.", "Total errors are displayed at the terminal.", JOptionPane.INFORMATION_MESSAGE);
                countErrorsCausedByUsers(users);
            }

            else if(in.equals("4")){

                String a = JOptionPane.showInputDialog("Please enter the start of the month: ");                
                String b = JOptionPane.showInputDialog("Please enter the end of the month: ");               
                // System.out.println(" ");
                if(checkIfInputIsInteger(a, b)){
                    int x = Integer.parseInt(a);
                    int y = Integer.parseInt(b);
                    int sum= countErrorsInAGivenRange(x, y);
                    JOptionPane.showMessageDialog(null, "Total number of errors from " + a + " to " + b + " are : " + sum, "Number of errors in a given range", JOptionPane.INFORMATION_MESSAGE);
                } else {                    
                    JOptionPane.showMessageDialog(null, "Not a valid command. Please enter proper month !", "Invalid Command", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
            else if(in.equalsIgnoreCase("q")){
                JOptionPane.showMessageDialog(null, "System closed ! Thanks for using ! ", "System Closed", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            else{
                JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE);
            }  
            System.out.println("\n*".repeat(5));          
        }
    }

    // function: read raw datas, find and count errors caused by users
    public static int countErrors() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT1));
                
                String line;     
                
                while((line = reader.readLine()) != null)
                {
                    Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                            writer.println(line);
                            count++;
                    }
                }
                // System.out.println("\n=> Total errors caused by users are: " + count);
                // System.out.println(" ");
                writer.close();
                reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
        return count;
}


    // function: write the usernames into a file named "usernames.txt".
    public static HashSet<String> findAndInputUsernames() {
    HashSet<String> users = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT2));
                
                String line;     

                while((line = reader.readLine()) != null)
                {
                    Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*(user=)'(.*)'(.*)(,).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()) {                            
                            writer.println(matcher.group(3));
                            users.add(matcher.group(3));
                    }                
                }
                // System.out.println("Users list:\n " + users);
                // System.out.println("The number of users are: " + users.size());
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
        try { BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
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
                reader = new BufferedReader(new FileReader(DEFAULT1));
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
    public static void getAssociationNumber(ArrayList<String> months) {
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
        } catch(FileNotFoundException e){System.out.println("File not found");}
          catch(IOException ex){}
    }


    // function: display username, time ,association number , and types of errors in a table.
    public static void displayInfoInTable(HashSet<String> users) {
        ArrayList<String> usersname = new ArrayList<String>();
        
        try{
            BufferedReader reader = null;
            
            for (String sets : users) {
                usersname.add(sets);
            }
            Collections.sort(usersname);
            // System.out.println("Usernames list:\n " + usersname);

            String line;
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n","           Name","            Time","        Associations num","     Error Types");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            for (String name : usersname) {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*(does not have access to )(.*)");
                    Matcher matcher = pattern.matcher(line);
                        if(line.contains(name) && matcher.matches()){
                            System.out.printf("|            %-16s |     %-26s |               %-16s |      %-15s |\n",name, matcher.group(1),matcher.group(3), matcher.group(5));
                        }
                }
            }
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            reader.close();
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }


    // function: display the number of errors caused by each users.
    public static void countErrorsCausedByUsers(HashSet<String> users) {
        
        ArrayList<String> usernames = new ArrayList<>();
        for(String set : users){
            usernames.add(set);
        }
       
        Collections.sort(usernames);
        System.out.println(" ");
            try {
                BufferedReader reader = null;                
                String line;

                    for (String name : usernames) {
                        reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt"));
                        int count = 0;
                            while((line = reader.readLine()) != null) {
                                if(line.contains(name)){
                                    count++;
                                }
                        } 
                        
                        System.out.printf("%s %-17s |   %-1d\n", "Number of errors caused by ===>", name, count );
                    } 
                reader.close();
            } catch (FileNotFoundException e) {System.out.println("File not found");}
              catch(IOException ex) {};
    }


    // function: count total number of errors in given range of time inputted by user
    public static int countErrorsInAGivenRange(int startMonth, int endMonth) {
        int sum = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            String line;
            
            while ((line = reader.readLine()) != null){
                Pattern pattern = Pattern.compile("\\[.*-(.*)-.*\\] .*");
                Matcher matcher = pattern.matcher(line);
                matcher.matches();
                
                int month = Integer.parseInt(matcher.group(1));
                
                if (month <= endMonth && month >= startMonth) {
                    sum++;
                    continue;
                }                  

                if (month > endMonth)
                    break;
            }
            System.out.println("The total number of errors in the given range is: " + sum);
            reader.close();
        }catch (FileNotFoundException e) {System.out.println("File not found");}
         catch (IOException ex) {}
         return sum;
    }

    
    // function: to check whether the input of the range of months is an integer or not.
    public static boolean checkIfInputIsInteger(String a, String b){
        try{
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            return true;

        } catch(NumberFormatException e) {
            // System.out.println("Input is not an integer value.");
            return false;
        }          
    }
}
