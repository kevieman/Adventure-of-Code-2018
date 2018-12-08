import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: Comments
public class D3_NoMatterHowYouSliceIt {
    private List<String> lines;
    private int[][] fabric = new int[1000][1000];

    public static void main(String[] args) {
        D3_NoMatterHowYouSliceIt d3 = new D3_NoMatterHowYouSliceIt("assets", "Day3_Input.txt");
        System.out.println(d3.getOverlappingSum());
    }

    private D3_NoMatterHowYouSliceIt(String location, String file) {
        Path inputPath = Paths.get(location, file);

        // Fill up lines
        try {
            lines = Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getOverlappingSum() {
        int sum = 0;
        Pattern pattern = Pattern.compile("\\d+");

        for (String line : lines) {
            List<Integer> claim = new ArrayList<>();

            //Fill up numbers
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                claim.add(Integer.parseInt(matcher.group()));
            }

            //Adds the amount of claims on the fabric
            for (int i = claim.get(1); i < claim.get(1) + claim.get(3); i++) {
                for (int j = claim.get(2); j < claim.get(2) + claim.get(4); j++) {
                    fabric[i][j] += 1;
                }
            }
        }

        // Add to sum if claims are overlapping
        for (int i = 0; i < 1000; i++)
            for (int x = 0; x < 1000; x++)
                if (fabric[i][x] > 1)
                    sum++;
        return sum;
    }
}
