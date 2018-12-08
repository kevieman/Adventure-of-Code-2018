import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class D3_NoMatterHowYouSliceIt {
    private ArrayList<D3_Claim> claims;

    public static void main(String[] args) {
        D3_NoMatterHowYouSliceIt d3 = new D3_NoMatterHowYouSliceIt("assets", "Day3_Input.txt");
    }

    private D3_NoMatterHowYouSliceIt(String location, String file){
        Path inputPath = Paths.get(location, file);
        List<String> lines;

        try {
            lines = Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private int getOverlappingSum(String claim){
        int sum = 0;

        for (D3_Claim toCompare : claims) {
            System.out.println("Start to compare");
            int overlapWith = 0;
            int overlapHeight = 0;

            //TODO: Compare claim coordinates here.

            sum += overlapWith * overlapHeight;
        }

        return sum;
    }
}
