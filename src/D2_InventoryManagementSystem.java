import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class D2_InventoryManagementSystem {
    private List<String> lines;

    public static void main(String[] args) {
        D2_InventoryManagementSystem d2 = new D2_InventoryManagementSystem("assets", "Day2-Input.txt");
        System.out.println("Answer to part 1 is: "+d2.getChecksum());
    }

    /**
     * Constructor
     * @param location File location (Example: 'C\\')
     * @param file Filename (Example: 'text.txt')
     */
    private D2_InventoryManagementSystem(String location, String file){
        Path inputPath = Paths.get(location, file);
        try {
            lines = Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private Integer getChecksum() {
        int checksum;
        int sumThree = 0;
        int sumTwo = 0;

        if (lines == null) {
            System.out.println("File is empty or not found.");
            return null;
        }

        for (String line : lines){
            if(hasAmount(line, 2))
                sumTwo++;
            if (hasAmount(line, 3))
                sumThree++;
        }

        checksum = sumThree * sumTwo;

        return checksum;
    }

    private boolean hasAmount(String line, int amount){
        String[] letters = line.split("");
        boolean hasAmount = false;
        List<String> asList = Arrays.asList(letters);
        HashSet<String> lettersSet = new HashSet<>(asList);
        for(String s: lettersSet){
            if(Collections.frequency(asList,s) == amount){
                hasAmount = true;
            }
        }
        return hasAmount;
    }

}
