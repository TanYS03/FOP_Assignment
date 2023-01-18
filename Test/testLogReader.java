import java.util.*;
public class testLogReader {
    
    public static void main(String[] args) {
        String fromFile = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt";
        String toFile = "C:\\Users\\User\\Desktop\\FOP_Assignment\\Test\\usernamelist.txt";
        String regex =  "\\[.*\\].*(error: This association).*(user=)'(.*)'(.*)(,).*";
        int group = 3;
        logReader lrd = new logReader(fromFile, toFile, regex, group);
        HashSet<String> usernames = new HashSet<>();
        System.out.println(lrd.getText(usernames));

        String fromfile =  "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt";
        String newFile = "C:\\Users\\User\\Desktop\\FOP_Assignment\\Test\\month.txt";
        String regex2 = "\\[.*-(.*)-[0-9]{2}.*\\].*(error: This association).*";
        int group2 = 1;
        logReader lrd2 = new logReader(fromfile, newFile, regex2, group2);
        HashSet<String> months = new HashSet<>();
        System.out.println(lrd2.getText(months));
    }
}
