package assignmentfop2;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AssignmentFOP2 {

    public static final String D1 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP2\\extracted_log";
    public static final String D2 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP2\\totalkilljob.txt";
    
    public static void main(String[] args) {
      
    totalkilljobmonth();
    totalkilljob();
   // TotalReturnedJob();
    MonthReturnedJob();
    displaytReturnedMonthInTable();
        
    }
        public static void totalkilljob(){
            
            System.out.println("Details for job successfully being killed per month: \n");
            
            try{
                
                Scanner scanner = new Scanner(new FileInputStream(D1));
                PrintWriter writer = new PrintWriter(new FileWriter("totalkilljob.txt"));
                
                int count = 0;
                String content = "\\[*(\\d{4}-\\d{2}-\\w+:\\d{2}:\\d{2}.\\d{3})\\]* .*job: (\\w*\\_*\\w*\\_*\\w*) JobId=(\\d+) uid (\\d+)";
                
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.println("|          Date           |          Request         |   JobId   |     uid    |");
                System.out.println("+-----------------------------------------------------------------------------+");
               
                while(scanner.hasNextLine()){
                    
                    String line = scanner.nextLine();
                    Pattern hi = Pattern.compile(content);
                    Matcher matcher = hi.matcher(line);
                    
                    if(matcher.find()){
                       if((matcher.group(3).length()==5)){
                        System.out.printf("| %15s | %20s     | %7s   | %10s |\n",matcher.group(1),matcher.group(2),matcher.group(3),matcher.group(4));
                        System.out.println("|-----------------------------------------------------------------------------|");
                       // writer.println(matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + " "  + matcher.group(4));
                       writer.println(line);
                        count++;
                       }
                }
                }
                System.out.println("| Total number of jobs being killed in all months is: " + count + "                    |");
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.println();
                
                scanner.close();
                writer.close();
                
            }catch (FileNotFoundException e){
                System.out.println("File Not Found.");
            }catch (IOException e){
                System.out.println("Problem with file input.");
            }
        }
        
        public static void totalkilljobmonth(){
            
            Integer [] count = new Integer [7];
            for (int i = 0; i < 7; i++) {
                count[i]=0;
            }
            System.out.println("Total number of job being killed successfully per month: ");
            System.out.println();
            
            try{
                
                Scanner abc = new Scanner(new FileInputStream(D2));
                //PrintWriter writer = new PrintWriter(new FileWriter("totalkilljobmonth.txt"));
                
                while(abc.hasNextLine()){
                    String line = abc.nextLine();
                    
                    if(line.contains("2022-06")){
                        count [0] += 1;
                    }else if(line.contains("2022-07")){
                        count [1] += 1;
                    }else if(line.contains("2022-08")){
                        count [2] += 1;
                    }else if(line.contains("2022-09")){
                        count [3] += 1;
                    }else if(line.contains("2022-10")){
                        count [4] += 1;
                    }else if(line.contains("2022-11")){
                        count [5] += 1;
                    }else if(line.contains("2022-12")){
                        count [6] +=1;
                    }
                }
                
                System.out.printf("+ %-20s + %17s +\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17s     |\n","       Month","     Number Of Job Killed ");
                System.out.printf("+ %-20s | %17s +\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","       June",       count[0]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","       July",       count[1]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      August",       count[2]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","     September",            count[3]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      October",            count[4]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      November",            count[5]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      December",            count[6]);
                System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
                System.out.println();
                
            }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
            }catch(IOException e){
            System.out.println("Problem with file input.");
        }
       }
            
        public static void TotalReturnedJob(){
            
        try{
            BufferedReader reader = new BufferedReader(new FileReader(D1));
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
           
           // System.out.println("Total no of jobs returned: " + count);
            System.out.println();
            
            reader.close();
            writer.close();
            
        }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
        }catch(IOException e){
            System.out.println("Problem with file input.");
        }
    }
        
     /*   public static void MonthReturnedJob(){
        
        try{
           int count = 0;
           Scanner sc = new Scanner(System.in);
           Scanner cs = new Scanner(new FileInputStream("return.txt"));
           PrintWriter writer = new PrintWriter(new FileWriter("return2.txt"));
           
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
    }*/
     
        public static void MonthReturnedJob(){
          Integer [] count = new Integer [7];
            for (int i = 0; i < 7; i++) {
                count[i]=0;
            }
            System.out.println("Total number of job being returned per month: ");
            System.out.println();
            
            try{
                
                Scanner abc = new Scanner(new FileInputStream("return.txt"));
                //PrintWriter writer = new PrintWriter(new FileWriter("totalkilljobmonth.txt"));
                
                while(abc.hasNextLine()){
                    String line = abc.nextLine();
                    
                    if(line.contains("2022-06")){
                        count [0] += 1;
                    }else if(line.contains("2022-07")){
                        count [1] += 1;
                    }else if(line.contains("2022-08")){
                        count [2] += 1;
                    }else if(line.contains("2022-09")){
                        count [3] += 1;
                    }else if(line.contains("2022-10")){
                        count [4] += 1;
                    }else if(line.contains("2022-11")){
                        count [5] += 1;
                    }else if(line.contains("2022-12")){
                        count [6] +=1;
                    }
                }
                
                System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
                System.out.printf("| %-20s | %17s   |\n","       Month","     Number Of Job Returned ");
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","       June",       count[0]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","       July",       count[1]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      August",       count[2]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","     September",            count[3]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      October",            count[4]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      November",            count[5]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                System.out.printf("| %-20s | %17d              |\n","      December",            count[6]);
                System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
                System.out.println();
                
            }catch(FileNotFoundException e){
            System.out.println("File Not Found.");
            }catch(IOException e){
            System.out.println("Problem with file input.");
        }
}
        
        public static void displaytReturnedMonthInTable() {

        try{
            
           // System.out.println("+" + "-".repeat(150) + "+");
            System.out.println();
            System.out.println("Details for job being returned per month: ");
            
            BufferedReader reader = new BufferedReader(new FileReader("return.txt"));
            
            int count = 0;
            String line;
           
            System.out.println(" ");
            System.out.printf("+ %-27s%-30s%-30s%-33s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(36));
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s    |\n","           JobID","            Time","            Status","               Reason");
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30),"-".repeat(36));

                
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] .* (uid=)([0-9]*) (JobId=)([0-9]*) (sig=9 returned:) (.*)");
                    Matcher matcher = pattern.matcher(line);
                    
                        if(matcher.matches()){
                            System.out.printf("|            %-16s |     %-26s |           %-20s |        %10s       | \n",matcher.group(5), matcher.group(1),"returned",matcher.group(7));
                            count++;
                        }
                }
            
            System.out.printf("| %-27s%-30s%-16s%-30s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(36));
            System.out.printf("|"+ " ".repeat(12) + "Total number of jobs being returned in all months is: " + count + " ".repeat(67) + "|\n");
            System.out.printf("+ %-27s%-30s%-16s%-30s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(36));
           
            reader.close();
            
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }
  }

    

