package killjob;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class Killjob {

    /**
     * @param args the command line arguments
     */
    
    private static final String A = "C:\\Users\\User\\Downloads\\extracted_log";
    private static final String B = "C:\\Users\\User\\Downloads\\killedjob.txt";

    
    public static void main(String[] args) {
        TotalKilledJob();
        displayInTable();
        MonthKilledJob();
        displayMonthInTable();
        TotalReturnedJob();
        MonthReturnedJob();
       displaytReturnedMonthInTable();

                      
    }
    
     public static void TotalKilledJob(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(A));
            PrintWriter writer = new PrintWriter(new FileWriter("killedjob.txt"));
            
            String line = "";
            int count = 0;
            
            while((line=reader.readLine())!=null){
                Pattern pattern = Pattern.compile(".*(_slurm_rpc_kill_job: REQUEST_KILL_JOB JobId=)(\\d{5}).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    count++;
                }
            }
           
            System.out.println("Total no of jobs killed: " + count);
                        System.out.println();
            
            reader.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
     
     public static void displayInTable() {
       
        try{
            BufferedReader reader = new BufferedReader(new FileReader("killed job used.txt"));
            
            String line;
        
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
            System.out.printf("| %-27s | %-30s | %-30s |\n","           JobID","            Time","            Status");
            System.out.printf("| %-27s | %-30s | %-30s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
               
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (_slurm_rpc_kill_job: REQUEST_KILL_JOB JobId=)([0-9]*).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches() ){
                            System.out.printf("|            %-16s |     %-26s |           %-20s |\n",matcher.group(3), matcher.group(1),"successful");
                        }
                }
            
            System.out.printf("| %-27s | %-30s | %-16s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
            reader.close();
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }



     public static void MonthKilledJob(){
                 
        try{
           int count = 0;
           Scanner sc = new Scanner(System.in);
           Scanner cs = new Scanner(new FileInputStream(B));
           PrintWriter writer = new PrintWriter(new FileWriter("job.txt"));
           
           int month;
           System.out.print("Enter the month to search for no of killed job (enter -1 to quit): ");
           month = sc.nextInt();
           if(month<6 && month>12){
               System.exit(0);
           } 
           
           while(cs.hasNextLine()){
                 
                String line = cs.nextLine();
                Pattern pattern = Pattern.compile(".*-(\\d+)-.*(_slurm_rpc_kill_job: REQUEST_KILL_JOB).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    if(Integer.parseInt(matcher.group(1)) == month){
                    count++;
                    writer.println(line);
                    }
                }
            }

            System.out.println("Total no of jobs killed in month " + month + " is: " + count);
            System.out.println();
           
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
     
  public static void displayMonthInTable() {

        
        try{
            BufferedReader reader = new BufferedReader(new FileReader("job.txt"));
            
            String line;
            
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
            System.out.printf("| %-27s | %-30s | %-30s |\n","           JobID","            Time","            Status");
            System.out.printf("| %-27s | %-30s | %-30s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
              
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (_slurm_rpc_kill_job: REQUEST_KILL_JOB JobId=)(\\d{5}).*");
                    Matcher matcher = pattern.matcher(line);
                        if( matcher.matches()){
                            System.out.printf("|            %-16s |     %-26s |           %-20s |\n",matcher.group(3), matcher.group(1),"successful");
                        }
                }
            
            System.out.printf("| %-27s | %-30s | %-16s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30));
            reader.close();
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }

     // function: write the job id into a file named "JOBID.txt".
 public static void TotalReturnedJob(){
try{
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\User\\Downloads\\extracted_log"));
            PrintWriter writer = new PrintWriter(new FileWriter("return.txt"));
            
            String line = "";
            int count = 0;
            
            while((line=reader.readLine())!=null){
                Pattern pattern = Pattern.compile(".*(sig=9 returned).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    writer.println(line);
                    count++;
                }
            }
           
            System.out.println("Total no of jobs returned: " + count);
                        System.out.println();
            
            reader.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
      public static void MonthReturnedJob(){
        
         
        try{
           int count = 0;
           Scanner sc = new Scanner(System.in);
           Scanner cs = new Scanner(new FileInputStream("return.txt"));
           PrintWriter writer = new PrintWriter(new FileWriter("return6.txt"));
           
           int month;
           System.out.print("Enter the month to search for no of returned job (enter -1 to quit): ");
           month = sc.nextInt();
           if(month<6 && month>12){
               System.exit(0);
           } 
           
           while(cs.hasNextLine()){
                 
                String line = cs.nextLine();
                Pattern pattern = Pattern.compile(".*-(\\d+)-.*(sig=9 returned).*");
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()){
                    if(Integer.parseInt(matcher.group(1)) == month){
                    count++;
                    writer.println(line);
                    }
                }
            }

            System.out.println("Total no of jobs returned in month " + month + " is: " + count);
            System.out.println();
            
            sc.close();
            writer.close();
           
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
     
public static void displaytReturnedMonthInTable() {

        
        try{
            BufferedReader reader = new BufferedReader(new FileReader("return6.txt"));
            
            String line;
           
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30),"-".repeat(36));
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s    |\n","           JobID","            Time","            Status","               Reason");
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30),"-".repeat(36));

                
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] .* (uid=)([0-9]*) (JobId=)([0-9]*) (sig=9 returned:) (.*)");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                            System.out.printf("|            %-16s |     %-26s |           %-20s |        %10s       | \n",matcher.group(5), matcher.group(1),"returned",matcher.group(7));
                        }
                }
            
            System.out.printf("| %-27s | %-30s | %-16s |  %-30s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30),"-".repeat(36));
            reader.close();
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }
}
