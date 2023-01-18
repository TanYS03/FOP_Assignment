
public class TestCounter {
    
    public static void main(String[] args) {
        String fromFile = "C:\\Users\\User\\Downloads\\extracted_log";
        String toFile = "C:\\Users\\User\\Desktop\\FOP_Assignment\\Test\\testExtractErrors.txt";
        String regex = "\\[.*\\].*(error: This association).*" ;

        InheritCounter errors = new InheritCounter(regex, fromFile, toFile);
        errors.countAllLines();
        errors.countUserError("han");
    }
    
}
