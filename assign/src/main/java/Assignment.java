import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String find = "";
        do{
            System.out.printf("%-50s %-10s\n","Description","Command");
            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-50s %-10s\n","number of job created in particular month","created");
            System.out.printf("%-50s %-10s\n","number of job ended in particular month","ended");
            System.out.printf("%-50s %-10s\n","number of job created/ended in particular month","jobRange");
            System.out.printf("%-50s %-10s\n","number of job created/ended in each month","allJobs");
            System.out.printf("%-50s %-10s\n","number of partitions","partition");
            System.out.print("What do you want to find?: ");
            find = input.nextLine();

            switch(find){
                case "ended","created":
                    checkMonth(find);
                    break;
                case "allJobs":
                    allJobs(find);
                    break;
                case "jobRange":
                    jobRange();
                    break;
                case "partition":
                    partition();
                    break;
                case "-1":
                    System.out.println("Thank you for using our program （^^)");
                    break;
                default:
                    System.out.println("Please rewrite again.");
            }
            System.out.println("*----------------------------------------------------------*");
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
        Scanner input = new Scanner(System.in);
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            int month=0, readNum=0;
            String chooseJob="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            System.out.print("Find job created or ended (C/E): ");
            char choice = input.next().charAt(0);


//            if(find.equalsIgnoreCase("allCreated")){
            if(choice=='C' || choice =='c'){
                chooseJob = "Allocate";
                readNum = 2;
            }
            else if(choice=='E' || choice =='e'){
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

    public static void jobRange(){
        Scanner input = new Scanner(System.in);
        String imonth="", fmonth = null;

        System.out.print("Find job created or ended (C/E): ");
        char choice = input.next().charAt(0);

        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            int readNum=0;
            String chooseJob="";
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};

            if(choice=='C' || choice =='c'){
                chooseJob = "Allocate";
                readNum = 2;
            }
            else if(choice=='E' || choice =='e'){
                chooseJob="_job_complete:";
                readNum=1;
            }

            System.out.print("Range of month from (june...december.) : ");//input initial month
            String month1 = input.next();
            System.out.print("To : ");  //input until month
            String month2 = input.next();

            for (int i = 0; i < arrayMonth.length; i++) {
                if(month1.equalsIgnoreCase(Month[i]))
                    imonth = arrayMonth[i];
                if(month2.equalsIgnoreCase(Month[i]))
                    fmonth = arrayMonth[i];

            }
            int num=0;

                while(inputstream.hasNextLine()) {
                    String[] read = inputstream.nextLine().split(" "); //read the text word by word

                    String date = ""+(read[0].charAt(6)-'0')+(read[0].charAt(7) - '0'); //to form a String month with two digits
                    if (read.length >2) {
                        if (date.compareTo(imonth)>=0 && date.compareTo(fmonth)<=0 ) {   //to check the month
                            if (read[readNum].equals(chooseJob)) num++; //to compare the word allocate for job created and job_complete for job ended
                        }
                    }
                    if (date.compareTo(fmonth)>0)
                        break;  //to break if the program look the line which already pass the needed month

                }
            System.out.printf("%s to %s : %d\n",month1,month2,num);
            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }

    }

    public static void partition(){
        Scanner input = new Scanner(System.in);
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            String imonth="", fmonth = null;
            String[] arrayMonth = {"06","07","08","09","10","11","12"};
            String[] Month ={"June","July","August","September","October","November","December"};
            String[] arrayPartition = {"gpu-k10","gpu-k40c","gpu-v100s","gpu-titan","cpu-opteron","cpu-epyc"};
            int[] numPartition =new int[6];

            System.out.println("Which month do you want to see?(june...december or all)"); //To ask the range of month or all month
            String ask = input.next();
            if(!ask.equalsIgnoreCase("all")) {
                System.out.println("To :");
                String month2 = input.next();
                for (int i = 0; i < arrayMonth.length; i++) {
                    if (ask.equalsIgnoreCase(Month[i]))
                        imonth = arrayMonth[i];
                    if (month2.equalsIgnoreCase(Month[i]))
                        fmonth = arrayMonth[i];
                }
            }

                System.out.printf("%-20s%-10s\n","Type of partition","Amount");

                if(!ask.equalsIgnoreCase("all")) {
                    while (inputstream.hasNextLine()) {
                       String[] read = inputstream.nextLine().split(" "); //read the text word by word
                       String[] typePartition = read[read.length - 1].split("="); //split the partition and the type of partition

                       String date = "" + (read[0].charAt(6) - '0') + (read[0].charAt(7) - '0'); //to form a String month with two digits
                        if (date.compareTo(imonth)>=0 && date.compareTo(fmonth)<=0 ) {   //to check the month
                            for (int i = 0; i < arrayPartition.length; i++) {
                                if (typePartition[typePartition.length - 1].equals(arrayPartition[i])) {
                                    numPartition[i]++;
                                    break;
                                }
                            }
                        }
                        if (date.compareTo(fmonth) > 0)
                            break;  //to break if the program look the line which already pass the needed month
                    }
                }

            if(ask.equalsIgnoreCase("all")){
                while(inputstream.hasNextLine()) {
                    String[] read = inputstream.nextLine().split(" "); //read the text word by word
                    String[] typePartition = read[read.length-1].split("="); //split the partition and the type of partition

                    for (int i = 0; i < arrayPartition.length; i++) {
                        if (typePartition[typePartition.length-1].equals(arrayPartition[i]) ) {
                            numPartition[i]++;
                            break;
                        }
                    }
                }

            }
            for (int i = 0; i < numPartition.length; i++) {
                System.out.printf("%-20s%-10d\n",arrayPartition[i],numPartition[i]);

            }

            inputstream.close();
        }catch (IOException e){
                System.out.println("Input file problem");
            }
    }
}
