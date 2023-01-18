package assignmentfop;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AssignmentFOP {

    public static final String D1 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP\\extracted_log";
    public static final String D2 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP\\errors.txt";
    
    public static void main(String[] args) {
    
    totalError();
    monthError();
    rangeMonthError();
    totalUser();
    totalUserMonth();
    totalErrorUserMonth();
    totalErrorUserRangeMonth();
    
    }
    
    public static void totalError(){
        
        System.out.println("Total number of errors occurred within 6 months: ");
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(D1));
            PrintWriter writer = new PrintWriter(new FileWriter("errors.txt"));
            
            String line = "";
            int count = 0;
            
            while((line=reader.readLine())!=null){
                Pattern pattern = Pattern.compile(".*(error: This association).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    writer.println(line);
                    System.out.println(line);
                    count++;
                }
            }
            System.out.println("Total errors caused by users in six months are: " + count);
            System.out.println();
            
            reader.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
    
    public static void monthError(){
        
        System.out.println("Total number of errors occured in a particular month: ");
         
        try{
           int count = 0;
           Scanner abc = new Scanner(System.in);
           Scanner cs = new Scanner(new FileInputStream(D2));
           PrintWriter writer = new PrintWriter(new FileWriter("montherror.txt"));
           
           int month;
           System.out.print("Enter the month to search for number of errors (enter -1 to quit): ");
           month = abc.nextInt();
           if(month<6 && month>12){
               System.exit(0);
           } 
           
           while(cs.hasNextLine()){
                 
                String line = cs.nextLine();
                Pattern pattern = Pattern.compile(".*-(\\d+)-.*(error: This association).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    if(Integer.parseInt(matcher.group(1)) == month){
                        writer.println(line);
                         System.out.println(line);
                    count++;
                    }
                }
            }
           
            System.out.println("Total errors caused by users in month " + month + " is: " + count);
            System.out.println();
           
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
    
    public static void rangeMonthError(){
        
        Scanner abc = new Scanner(System.in);
        System.out.println("Total number of errors occured from month to month: ");
        
        try{
            
            Scanner cs = new Scanner(new FileInputStream(D2));
            PrintWriter writer = new PrintWriter(new FileWriter("rangemontherror.txt"));
            
            int count = 0;
            String content = ".*-(\\d+)-.*(error: This association).*"; //set group 1 and 2 for the pattern
            
            System.out.print("From month: ");  //Set the duration of months we want to search
            int month1 = abc.nextInt();
            System.out.print("Until month: ");
            int month2 = abc.nextInt();
            
            while(cs.hasNextLine()){
                
                String line = cs.nextLine();
                Pattern hi = Pattern.compile(content);
                Matcher matcher = hi.matcher(line);
                
                if(matcher.matches()){
                     if(Integer.parseInt(matcher.group(1)) >= month1 && Integer.parseInt(matcher.group(1)) <= month2){
                         writer.println(line);
                          System.out.println(line);
                           count++;
                     }
                }
            }
            
            System.out.println("The total number of errors occured from month " + month1 + " to " + month2 + " is: " + count);
            System.out.println();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
    
    public static HashSet<String> totalUser(){
        
        System.out.println("Total number of users making errors in all months: ");
        HashSet <String> users = new HashSet <> ();
        
        try{
            Scanner abc = new Scanner(new FileInputStream(D1));
            PrintWriter writer = new PrintWriter(new FileWriter("username.txt"));
            
            String line ="user='(\\w*\\_*\\.*\\w*\\.*\\w*)"; //write every _ , . , to detect and match with people with different names
            
            while(abc.hasNextLine()){
                
                String content = abc.nextLine();
                Pattern cs = Pattern.compile(line);
                Matcher matcher = cs.matcher(content);
                
                if(matcher.find()){
                    writer.println(matcher.group(1));
                    users.add(matcher.group(1));
                }
            }
              System.out.println("User namelist:\n " + users);
              System.out.println("Total number of users making the errors are: " + users.size());
              System.out.println();
            
            abc.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
        return users;
    }
    
    public static HashSet <String> totalUserMonth(){
        
        System.out.println("Total numbers of user making errors by particular month: ");
        Scanner abc = new Scanner(System.in);
        HashSet <String> users = new HashSet <> ();
        
        try{
           
            Scanner cs = new Scanner(new FileInputStream(D2));
            PrintWriter writer = new PrintWriter(new FileWriter("totalusermonth.txt"));
            
            System.out.print("Enter the month: ");
            int month = abc.nextInt();
            
            int count = 0;
            String content = ".*-(\\d+)-.*(error: This association).*user='(\\w*\\_*\\.*\\w*\\.*\\w*).*";
            
            while(cs.hasNextLine()){
                
                String line = cs.nextLine();
                Pattern hello = Pattern.compile(content);
                Matcher matcher = hello.matcher(line);
                
                if(matcher.find()){
                    if(Integer.parseInt(matcher.group(1)) == month){
                        writer.println(matcher.group(3));
                        users.add(matcher.group(3));
                        System.out.println(line);
                    }
                }
            }
            
            System.out.printf("User namelist in month %d: %s\n", month,users);
            System.out.println("Total number of users making the errors are: " + users.size());
            System.out.println();
            
           cs.close();
           writer.close();
           
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
       return users;
    }
    
    public static void totalErrorUserMonth(){
        
        boolean hi = false;
        System.out.println("Total errors caused by particular user in a month: ");
        Scanner abc = new Scanner(System.in);
        
        try{
            
            Scanner cs = new Scanner(new FileInputStream(D2));
            PrintWriter writer = new PrintWriter(new FileWriter("totalerrorusermonth"));
            
            int count = 0;
            System.out.print("Enter the month: ");
            int month = abc.nextInt();
            System.out.print("Enter the name: ");
            String name = abc.next();
            
            String content = ".*-(\\d+)-.*(error: This association).*user='(\\w*\\_*\\.*\\w*\\.*\\w*).*";
            
            while(cs.hasNextLine()){
                
                String line = cs.nextLine();
                Pattern hola = Pattern.compile(content);
                Matcher matcher = hola.matcher(line);
                
                if(matcher.find()){
                    if(Integer.parseInt(matcher.group(1)) == month && matcher.group(3).equalsIgnoreCase(name) ){
                        System.out.println(line);
                        count++;
                   }
                }
            }
            System.out.printf("Total number of errors made by %s in month %d is: %d\n", name, month, count);
            System.out.println();
            
            cs.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
    
    public static void totalErrorUserRangeMonth(){
        
        System.out.println("Total errors caused by particular user from month to month: ");
        Scanner abc = new Scanner(System.in);
        
        try{
            
            Scanner cs = new Scanner(new FileInputStream(D2));
            PrintWriter writer = new PrintWriter(new FileWriter("totalerroruserrangemonth"));
            
            int count = 0;
            System.out.print("From month: ");
            int month1 = abc.nextInt();
            System.out.print("Until month: ");
            int month2 = abc.nextInt();
            System.out.print("Enter the name: ");
            String name = abc.next();
            
            String content = ".*-(\\d+)-.*(error: This association).*user='(\\w*\\_*\\.*\\w*\\.*\\w*).*";
            
            while(cs.hasNextLine()){
                
                String line = cs.nextLine();
                Pattern wick = Pattern.compile(content);
                Matcher matcher = wick.matcher(line);
                
                if(matcher.find()){
                    if(Integer.parseInt(matcher.group(1)) >= month1 && Integer.parseInt(matcher.group(1)) <= month2 && matcher.group(3).equalsIgnoreCase(name) ){
                        System.out.println("+-------------------------------------------+");
                        System.out.println("|   Month   |   UserName  | Number of Error |");
                        System.out.printf("|%-5d |%10s\n%", matcher.group(1), matcher.group(2));
                        System.out.println("+--------------------------------------------+");
                        System.out.println(line);
                        count++;
                    }
                    /*else{
                         throw new IllegalStateException("The name entered is invalid. Please try again.");
                    }*/
                }
            }
            System.out.printf("Total number of errors made by %s from month %d to month %d is: %d\n", name, month1, month2, count);
            System.out.println();
            
            cs.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
  }


