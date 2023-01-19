import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Table{
    public static void main(String[] args) {
        try{
            PrintWriter outputstream = new PrintWriter(new FileOutputStream("D:/UM/WIX1002 Fundamentals of Programming/try.xlsx"));
            Scanner inputstream = new Scanner(new FileInputStream("D:/UM/WIX1002 Fundamentals of Programming/tableData.txt"));

            String x = inputstream.next();
            outputstream.print(x);

            outputstream.close();
            inputstream.close();
        }catch (IOException e){
            System.out.println("file not found");
        }

    }
}