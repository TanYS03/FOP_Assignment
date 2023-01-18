import java.util.regex.*;
import java.nio.file.*;
import java.util.stream.*;

public class RegexReader {
    private final Pattern PATTERN;
    private final String FILE;
    private StringBuilder content;

    public RegexReader(String pattern, String file) {
        this.PATTERN = Pattern.compile(pattern);
        this.FILE = file;
        this.content = new StringBuilder();
    }

    public void readContent() throws Exception {
        Files.lines(Paths.get(this.FILE))
             .filter(line -> line.matches(PATTERN.pattern()))
             .forEach(line -> this.content.append(line).append("\n"));
    }

    public void printTable() {
         Matcher matcher;
         for(String line : this.content.toString().split("\n")) {
            matcher = PATTERN.matcher(line); 
            matcher.matches();
            for(int i = 1; i <= matcher.groupCount(); i++){
                System.out.printf("|%30s", matcher.group(i));
            }
            System.out.println("|");
         }
    }
}
