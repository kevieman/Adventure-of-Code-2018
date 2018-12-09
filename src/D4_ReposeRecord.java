import java.util.HashMap;
import java.util.List;

public class D4_ReposeRecord {
    private List<String> lines;
    private HashMap<Integer, int[]> guardAsleep;

    public static void main(String[] args) {
        new D4_ReposeRecord("assets", "Day4_Input.txt");
    }

    private D4_ReposeRecord(String location, String file) {
        guardAsleep = new HashMap<>();

        lines = new LineReader(location, file).getLinesSorted();

        fillGuardAsleep();
    }

    private void fillGuardAsleep(){
        Integer start = null;
        int stop;
        Integer guardID = null;

        for (String line : lines) {
            String[] splitted = line.split(" ");

            if (line.contains("#")){
                guardID = Integer.parseInt(splitted[3].replace("#", ""));
                start = null;
            }
            if (line.contains("falls asleep")) {
                start = Integer.parseInt(splitted[1].split(":")[1].replace("]", ""));
            }
            if (line.contains("wakes up")) {
                stop = Integer.parseInt(splitted[1].split(":")[1].replace("]", ""));
                int[] a = new int[59];

                if(guardAsleep.containsKey(guardID))
                    a = guardAsleep.get(guardID);

                if (start != null) {
                    for (int i = start - 1; i <= stop; i++) {
                        a[i] += 1;
                    }
                }

                guardAsleep.put(guardID, a);

            }
        }
    }
}
