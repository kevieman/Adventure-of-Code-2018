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

        int answerA = getMostSleeper() * getMostMinAsleep(getMostSleeper())[0];
        System.out.println("Answer to part 1: " + answerA);
        System.out.println("Answer to part 2: " + getFrequentSleeper());
    }

    private Integer getMostSleeper() {
        Integer mostSleeper = null;
        int mostMinutes = 0;

        for (Integer key : guardAsleep.keySet()) {
            int totalMinutes = 0;
            int[] minutes = guardAsleep.get(key);

            for (int minute : minutes){
                totalMinutes += minute;
            }

            if (mostSleeper == null || totalMinutes > mostMinutes) {
                mostSleeper = key;
                mostMinutes = totalMinutes;
            }
        }
        return mostSleeper;
    }

    private int getFrequentSleeper() {
        int frequentSleeper = 0;
        int whichMin = 0;
        int min = 0;

        for (Integer key : guardAsleep.keySet()) {
            if (getMostMinAsleep(key)[1] > min) {
                min = getMostMinAsleep(key)[1];
                whichMin = getMostMinAsleep(key)[0];
                frequentSleeper = key;
            }
        }

        return frequentSleeper * whichMin;
    }

    private int[] getMostMinAsleep(Integer key) {
        int mostMin = 0;
        int min = 0;
        int[] minutes = guardAsleep.get(key);

        for (int i = 0; i < minutes.length; i++) {
            if (minutes[i] > mostMin) {
                min = i;
                mostMin = minutes[i];
            }
        }

        return new int[] {min, mostMin};
    }

    private void fillGuardAsleep(){
        Integer start = null;
        Integer guardID = null;
        int stop;

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

                if (start != null)
                    for (int i = start ; i < stop; i++)
                        a[i] += 1;
                guardAsleep.put(guardID, a);
            }
        }
    }
}
