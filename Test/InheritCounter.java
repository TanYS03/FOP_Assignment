import java.util.ArrayList;
public class InheritCounter extends Counter {

    public InheritCounter (String pattern, String readedFile, String newFile) {
        super(pattern, readedFile, newFile);
    }

    
    public void countUserError(String user) {
        super.countIfContain(user);
    }
}
