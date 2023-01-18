import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;


public class Counter {

    protected String DEFAULT;
    protected String NEWFILE;
    protected Pattern PATTERN;

    public Counter(String pattern, String readedFile, String newFile) {
        this.DEFAULT = readedFile;
        this.NEWFILE = newFile;
        this.PATTERN = Pattern.compile(pattern);
    }

    public void countAllLines() {
        this.countIfContain("[");
    }

    public void countIfContain(String... keywords) {
        int count = 0;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(NEWFILE));

            String line;                     
            Outer:
            while((line = reader.readLine()) != null) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.matches()) {
                    for(int i = 0; i < keywords.length; i++){
                        if (!line.contains(keywords[i]))
                            continue Outer;
                    }
                    count++;
                }
                
            }

            System.out.println(count);             
            writer.close();
            reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
    }
}
