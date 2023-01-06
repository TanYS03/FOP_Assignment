import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String find = "";
        do{
            try {
                Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
                System.out.println("What do you find?: ");
                find = input.nextLine();


                jobCreated();
                inputstream.close();
            }catch(IOException e ){
                System.out.println("File input problem");
            }
        }while(!find.equals("-1"));
    }

    public static void jobCreated(){
        Scanner input = new Scanner(System.in);
            System.out.println("Enter the number for the month you want to find(0-Jun,1-July,2-August,3-September,4-October,5-November,6-December: ");
            int  month = input.nextInt();

            int[] arrayMonth = {6,7,8,9,0,1,2};
            find(month, arrayMonth);



    }
    public static void find (int month, int[] arrayMonth){
        try{
            Scanner inputstream = new Scanner (new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/Assignment/extracted_log.txt"));
            int number = arrayMonth[month];
            int num=0;
            while(inputstream.hasNextLine()) {
                String[] read = inputstream.nextLine().split(" "); //read the text word by word

                if (read.length > 2) {
                    if (read[0].charAt(7) - '0' == number) {
                        if (read[2].equals("Allocate")) num++;
                    }
//                    if (read[0].charAt(7) - '0' > number + 1) break;
                }
            }
            System.out.println(num);

            inputstream.close();
        }catch (IOException e){
            System.out.println("Input file problem");
        }


    }


    // count number of jobs per month
}
