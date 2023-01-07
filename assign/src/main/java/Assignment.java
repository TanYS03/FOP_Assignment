import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String find = "";
        do{
            System.out.printf("%-45s %-10s\n","Description","Command");
            System.out.printf("%-45s %-10s\n","number of job created in particular month","created");
            System.out.printf("%-45s %-10s\n","number of job ended in particular month","ended");
            System.out.printf("%-45s %-10s\n","number of job ended ","allEnded");
            System.out.printf("%-45s %-10s\n","number of job created","allCreated");
            System.out.print("What do you want to find?: ");
            find = input.nextLine();

            switch(find){
                case "ended","created":
                    checkMonth(find);
                    break;
                case "allEnded","allCreated":
                    allJobs(find);
                    break;
                case "-1":
                    break;
                default:
                    System.out.println("Please rewrite again.");
            }
//            if(find.equalsIgnoreCase("ended") || find.equalsIgnoreCase("created")) checkMonth(find);
        }while(!find.equals("-1"));
    }

    public static void checkMonth(String find){// ask for the month to check
        Scanner input = new Scanner(System.in);

            System.out.print("Enter the number for the month you want to find(0-Jun,1-July,2-August,3-September,4-October,5-November,6-December or press all to look every month): ");
            int  month = input.nextInt();

            boolean whichJob = false;
            int[] arrayMonth = {6,7,8,9,0,1,2};
            if(find.equalsIgnoreCase("created")) whichJob=true;
            if(find.equalsIgnoreCase("ended")) whichJob = false;
            job(month,arrayMonth,whichJob);




    }
    public static void job (int month, int[] arrayMonth,boolean whichJob){  //check the number of jobs created and ended in the month
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            String chooseJob = whichJob? "Allocate":"_job_complete:";
            int readNum = whichJob?2:1;
            int number = arrayMonth[month];
            int num=0;
            while(inputstream.hasNextLine()) {
                String[] read = inputstream.nextLine().split(" "); //read the text word by word

                if (read.length >2) {
                    if (read[0].charAt(7) - '0' == number) {
                        if (read[readNum].equals(chooseJob)) num++;
                    }
                    if (read[0].charAt(7) - '0' > number + 1 && read[0].charAt(6)-'0'>0) break;  //to break if the program look the line which already pass the needed month
                }
            }
            System.out.println(num);

            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }
    }


    public static void allJobs( String find){
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            int month=0, readNum=0;
            String chooseJob="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            if(find.equalsIgnoreCase("allCreated")){
                chooseJob = "Allocate";
                readNum = 2;
            }
            else if(find.equalsIgnoreCase("allEnded")){
                chooseJob="_job_complete:";
                readNum=1;
            }
            int num=0;
            System.out.printf("%-10s%-10s\n","Month","Number of jobs");
            while(month<=6){

                String number = arrayMonth[month];
                while(inputstream.hasNextLine()) {
                    String[] read = inputstream.nextLine().split(" "); //read the text word by word

                    String date = ""+(read[0].charAt(6)-'0')+(read[0].charAt(7) - '0');
                    if (read.length >2) {
                        if (date.compareTo(number)==0 ) {
                            if (read[readNum].equals(chooseJob)) num++;
                        }
                    }
                    if (date.compareTo(number)>0) {
                        System.out.printf("%-15s%-10d\n",Month[month],num);
                        month++;
                        num = read[readNum].equals(chooseJob)?1:0;
                        break;  //to break if the program look the line which already pass the needed month
                    }
                }
                if(!inputstream.hasNextLine()){
                    System.out.printf("%-15s%-10d\n",Month[month],num);
                    break;

                }
            }
            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }

    }
}
