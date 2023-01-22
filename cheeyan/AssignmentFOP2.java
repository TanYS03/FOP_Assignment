package assignmentfop2;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.*;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class AssignmentFOP2 {

    public static final String D1 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP2\\extracted_log";
    public static final String D2 = "C:\\Users\\tiewc\\OneDrive\\Documents\\NetBeansProjects\\AssignmentFOP2\\totalkilljob.txt";
    
    public static void main(String[] args) {
      
    totalkilljobmonth();
    totalkilljob();
    TotalReturnedJob();
    MonthReturnedJob();
    displaytReturnedMonthInTable();
    
    }
    
        public static void totalkilljobmonth(){
            
            int [] successcount = new int [7];
            int [] requestcount = new int [7];
            int [] failurecount = new int [7];
            String [] Month = {"June", "July", "August", "September", "October", "November", "December"};
            
            System.out.println("Total number of job being killed successfully per month: ");
            System.out.println();
            
            try{
                
                Scanner scanner = new Scanner(new FileInputStream (D1));
               
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    if(line.contains("_slurm_rpc_kill_job")){
                        if(line.contains("REQUEST_KILL_JOB")) checkMonth(line,requestcount);
                        if(line.contains("job_str_signal()")) checkMonth(line,failurecount);
                    }
                }
                
                scanner.close();
                 
            }catch (FileNotFoundException e){
                System.out.println("File Not Found.");
            }catch (IOException e){
                System.out.println("Problem with file input.");
            }
            
            for(int i = 0; i < successcount.length; i++){
                successcount[i] = requestcount[i] - failurecount[i];
            }
            
            System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
            System.out.printf("| %-20s | %17s     |\n","Month","     Number Of Job Killed ");
            System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
            for(int i = 0; i < successcount.length-1; i++){
                System.out.printf("| %-20s | %17d              |\n",   Month[i],  successcount[i]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
            }
            System.out.printf("| %-20s | %17d              |\n","December",   successcount[6]);
            System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
            System.out.println();
             
            PieChartforsuccessjobkill(successcount);
        }
        
        public static void checkMonth(String line, int [] monthcount){
                Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.+)](.+)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                switch(matcher.group(1)){
                    case "06" : monthcount[0]++;
                    break;
                    case "07" : monthcount[1]++;
                    break;
                    case "08" : monthcount[2]++;
                    break;
                    case "09" : monthcount[3]++;
                    break;
                    case "10" : monthcount[4]++;
                    break;
                    case "11" : monthcount[5]++;
                    break;
                    case "12" : monthcount[6]++;
                    break;
                }
            }
        
         public static void totalkilljob(){
            
            String[] successDate = new String[2336];
            String[] successRequest = new String[2336];
            String[] successId = new String[2336];
            String[] successUid = new String[2336];
            String[] failDate = new String[44];
            String[] failRequest = new String[44];
            String[] failId = new String[44];
            String[] failUid = new String[44];
            String[] failReason = new String[44];
            int successCount =0;
            int failCount =0;
            String id = null;
             
            System.out.println("Details for job successfully being killed per month: \n");
            
            try{
                
                Scanner scanner = new Scanner(new FileInputStream(D1));
                
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    Pattern pattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (REQUEST_KILL_JOB) JobId=(\\d+) uid (\\d+)");
                    Matcher matcher = pattern.matcher(line);
                    Pattern failedpattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (job_str_signal(.+)) uid=(\\d+) JobId=(\\d+) sig=9 returned: (.+)");
                    Matcher failedmatcher = failedpattern.matcher(line);
                    matcher.find();
                    failedmatcher.find();
                    
                    if(line.contains("job_str_signal()")){
                        if(failedmatcher.group(5).compareToIgnoreCase(id)==0){
                        successDate[successCount-1]=null;
                        successRequest[successCount-1]=null;
                        successId[successCount-1]=null;
                        successUid[successCount-1]=null;
                        successCount--;
                    }
                        
                    failDate[failCount]= failedmatcher.group(1);
                    failRequest[failCount]=failedmatcher.group(2);
                    failId[failCount]=failedmatcher.group(5);
                    failUid[failCount]=failedmatcher.group(4);
                    failReason[failCount] = failedmatcher.group(6);
                    failCount++;
                }
                    
                if(matcher.matches()){
                    successDate[successCount]= matcher.group(1);
                    successRequest[successCount]=matcher.group(2);
                    successId[successCount]=matcher.group(3);
                    successUid[successCount]=matcher.group(4);
                    id = matcher.group(3);
                    successCount++;
                }
              }
    
                scanner.close();
                
                int counter = 0;
                
                System.out.printf("+ %-27s%-30s%-30s%-24s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(27));
                System.out.printf("| %-27s | %-30s | %-35s |  %-23s|\n","           JobID","            Date","              Request","         uid");
                System.out.printf("| %-27s%-30s%-30s%-24s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(27));
                
                for (int i = 0; i < successDate.length; i++) {
                System.out.printf("|            %-16s |     %-26s |           %-25s |        %10s       | \n", successId[i] , successDate[i], successRequest[i], successUid[i]);
                counter++;
                }
                
                System.out.printf("| %-27s%-30s%-30s%-24s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(27));
                System.out.printf("|"+ " ".repeat(11) + " Total number of jobs being killed in all months is: "+ counter + " ".repeat(57) + "|\n");
                System.out.printf("+ %-27s%-30s%-30s%-24s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(27));
                System.out.println();
                
            }catch (FileNotFoundException e){
                System.out.println("File Not Found.");
            }catch (IOException e){
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
        
        public int [] monthCount(String line, int[] count){
                
                 if (line.contains("2022-06")){
                     count[0]+=1;    
                 }
                 else if (line.contains("2022-07")){
                     count[1]+=1;
                 }
                 else if (line.contains("2022-08")){
                     count[2]+=1;
                 }
                 else if (line.contains("2022-09")){
                     count[3]+=1;
                 }
                 else if (line.contains("2022-10")){
                     count[4]+=1;
                 }
                 else if (line.contains("2022-11")){
                     count[5]+=1;
                 }
                 else if (line.contains("2022-12")){
                     count[6]+=1;
                 }
                 return count;
            }
        
        public static void MonthReturnedJob(){
            
            String [] Month = {"June", "July", "August", "September", "October", "November", "December"};
            int [] count = new int [7];
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
                System.out.printf("| %-20s | %17s   |\n","Month","     Number Of Job Returned ");
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                for(int i = 0 ; i < count.length-1 ; i++){
                System.out.printf("| %-20s | %17d              |\n",  Month[i],       count[i]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
                }
                System.out.printf("| %-20s | %17d              |\n","December",            count[6]);
                System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
                System.out.println();
                
                PieChartforreturnedjob(count);
                
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
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s    |\n","           JobID","            Date","           Status","               Reason");
            System.out.printf("| %-27s%-30s%-30s%-33s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30),"-".repeat(36));

                
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
        
    public static void PieChartforsuccessjobkill(int [] count){
        
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 25% ", count[0]);
        result.setValue("July\n" + " 12% ", count[1]);
        result.setValue("August\n" + " 18% ", count[2]);
        result.setValue("September\n" + " 10% ", count[3]);
        result.setValue("October\n" + " 13% ", count[4]);
        result.setValue("November\n" + " 13% ", count[5]);
        result.setValue("December\n" + " 9% ", count[6]);
        
        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total number of job successfully being killed ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);
        
        
        PiePlot3D plot=(PiePlot3D)chart.getPlot();
        plot.setSectionPaint(0, Color.GREEN);
        plot.setSectionPaint(1, Color.RED);
        plot.setSectionPaint(2, Color.BLUE);
        plot.setSectionPaint(3, Color.magenta);
        plot.setSectionPaint(4, Color.darkGray);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.YELLOW);
        plot.setLabelFont(new Font("Arial",Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        
        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
      
    public static void PieChartforreturnedjob(int [] count){
        
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 14% ", count[0]);
        result.setValue("July\n" + " 57% ", count[1]);
        result.setValue("August\n" + " 7% ", count[2]);
        result.setValue("September\n" + " 5% ", count[3]);
        result.setValue("October\n" + " 5% ", count[4]);
        result.setValue("November\n" + " 14% ", count[5]);
        result.setValue("December\n" + " 0% ", count[6]);
        
        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total number of job being returned ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 30));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);
        
        
        PiePlot3D plot=(PiePlot3D)chart.getPlot();
        plot.setSectionPaint(0, Color.RED);
        plot.setSectionPaint(1, Color.ORANGE);
        plot.setSectionPaint(2, Color.YELLOW);
        plot.setSectionPaint(3, Color.GREEN);
        plot.setSectionPaint(4, Color.BLUE);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial",Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        
        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
  }

    



    

