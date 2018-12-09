import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D3_NoMatterHowYouSliceIt {
    private List<String> lines;
    private int[][] fabric = new int[1000][1000];
    private Pattern pattern;

    public static void main(String[] args) {
        new D3_NoMatterHowYouSliceIt("assets", "Day3_Input.txt");
    }

    /**
     * Constructor
     * @param location File location (Example: 'C\\')
     * @param file Filename (Example: 'text.txt')
     */
    private D3_NoMatterHowYouSliceIt(String location, String file) {
        pattern = Pattern.compile("\\d+");

        lines = new LineReader(location, file).getLines();

        System.out.println(getOverlappingSum());
        getNoCollideClaim();
    }

    /**
     * @return The sum of the overlapping surface of the claims.
     */
    private int getOverlappingSum() {
        int sum = 0;

        for (String line : lines) {
            List<Integer> claim;

            claim = fillClaim(line);

            //Adds the amount of claims on the fabric.
            for (int i = claim.get(1); i < claim.get(1) + claim.get(3); i++) {
                for (int x = claim.get(2); x < claim.get(2) + claim.get(4); x++) {
                    fabric[i][x] += 1;
                }
            }
        }

        //calculates the sum.
        for (int i = 0; i < 1000; i++) {
            for (int x = 0; x < 1000; x++) {
                if (fabric[i][x] > 1)
                    sum++;
            }
        }

        return sum;
    }

    /**
     * Prints out the ID of the claim which does not collide with any other.
     */
    private void getNoCollideClaim(){
        for (String line : lines) {
            List<Integer> claim;
            boolean noOverlap = true;

            claim = fillClaim(line);

            for (int i = claim.get(1); i < claim.get(1) + claim.get(3); i++) {
                for (int x = claim.get(2); x < claim.get(2) + claim.get(4); x++) {
                    if(fabric[i][x] > 1){
                        noOverlap = false;
                    }
                }
            }

            // Part 2: Prints the claim which doesnt overlap.
            if (noOverlap){
                System.out.println("Answer to part 2 is: " + claim.get(0));
            }
        }
    }

    /**
     * Fills up a claim with Integers.
     * @param line The String of the claim.
     * @return A filled claim with Integers.
     */
    private List<Integer> fillClaim(String line){
        List<Integer> claim = new ArrayList<>();

        //Fill up claim
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            claim.add(Integer.parseInt(matcher.group()));
        }

        return claim;
    }
}
