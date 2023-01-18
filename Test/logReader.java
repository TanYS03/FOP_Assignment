
import java.io.*;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class logReader {
    
  
    protected String DEFAULT;
    protected String NEWFILE;
    protected HashSet<String> set; 
    protected int MatcherGroup;
    private final Pattern PATTERN;

    public logReader(String file, String newFile, String pattern, int Group) {
        this.DEFAULT = file;   
        this.NEWFILE = newFile;
        this.MatcherGroup = Group;
        this.PATTERN = Pattern.compile(pattern);
    }

    public HashSet<String> getText(HashSet<String> sets) {
        this.set = sets;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT)) ;
            PrintWriter writer = new PrintWriter(new FileWriter(NEWFILE)) ;            
                while((line = reader.readLine()) != null) {            
                    Matcher matcher = PATTERN.matcher(line);
                        if(matcher.matches()) {
                            writer.println(matcher.group(this.MatcherGroup));
                            sets.add(matcher.group(this.MatcherGroup));
                        }                
            }
            reader.close();
            writer.close();
        }
        catch (FileNotFoundException e) {return set;}       
        catch (IOException ex) {return set;}
        return set;
    }
}
