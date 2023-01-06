import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String find;
        do{
            Scanner inputstream = new Scanner(new FileInputStream("assignmentData"));
            System.out.println("What do you find?: ");
            find =input.nextLine();

        }while(find.equals("-1"));
    }
}
