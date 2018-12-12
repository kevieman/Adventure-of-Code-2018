import java.util.ArrayList;
import java.util.List;

public class D6_ChronalCoordinates {
    private ArrayList<int[]> coordinates;
    private int[] maxCoordinates;

    public static void main(String[] args) {

    }

    private D6_ChronalCoordinates(String location, String file) {
        List<String> lines = new LineReader(location, file).getLines();
        coordinates = new ArrayList<>();
        maxCoordinates = new int[2];

        for (String line : lines) {
            String[] splitted = line.split(", ");
            int x = Integer.parseInt(splitted[0]);
            int y = Integer.parseInt(splitted[1]);
            coordinates.add(new int[]{x, y});
        }
        setMaxCoordinates();

    }

    private void setMaxCoordinates() {
        int maxX = 0;
        int maxY = 0;

        for (int[] coordinate : coordinates) {
            if (coordinate[0] > maxX)
                maxX = coordinate[0];
            if (coordinate[1] > maxY)
                maxY = coordinate[1];
        }

        maxCoordinates[0] = maxX;
        maxCoordinates[1] = maxY;
    }
}
