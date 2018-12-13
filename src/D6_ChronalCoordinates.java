import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class D6_ChronalCoordinates {
    private List<String> lines;
    private ArrayList<Point> coordinates;
    private HashMap<Integer, Integer> locations;
    private int[][] field;
    private int maxX;
    private int maxY;

    public static void main(String[] args) {
        new D6_ChronalCoordinates("assets", "Day6_Input.txt");
    }

    private D6_ChronalCoordinates(String location, String file) {
        lines = new LineReader(location, file).getLines();
        locations = new HashMap<>();
        coordinates = new ArrayList<>();
        setCoordinates();
        fillLocations();
        System.out.println("Answer 2: " + getRegionSize());
        removeInfinite();
        System.out.println("Answer 1: " + getBigNonInfiniteSize());
    }

    private int getBigNonInfiniteSize() {
        int size = 0;
        for (int lSize : locations.values()) {
            if (lSize > size){
                size = lSize;
            }
        }

        return size;
    }

    private int getRegionSize() {
        int size = 0;

        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++){
                int cSize = 0;
                for (int i = 0; i < coordinates.size(); i++) {
                    Point coordinate = coordinates.get(i);
                    int distance = Math.abs(x - coordinate.x) + Math.abs(y - coordinate.y);
                    cSize += distance;
                }

                if (cSize < 10000) {
                    size++;
                }
            }
        }

        return size;
    }

    private void removeInfinite(){
        int infinite = 0;
        for (int i = 0; i <= maxX; i++) {
            infinite  = field[i][0];
            locations.remove(infinite);
            infinite = field[i][maxY];
            locations.remove(infinite);
        }
        for (int i = 0; i <= maxY; i++) {
            infinite  = field[0][i];
            locations.remove(infinite);
            infinite = field[maxX][i];
            locations.remove(infinite);
        }
    }

    private void fillLocations() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                int sum = maxX + maxY;
                int marker = -1;

                for (int i = 0; i < coordinates.size(); i ++) {
                    Point coordinate = coordinates.get(i);
                    int distance = Math.abs(x - coordinate.x) + Math.abs(y - coordinate.y);
                    if (distance < sum) {
                        sum = distance;
                        marker = i;
                    } else if(distance == sum) {
                        marker = -1;
                    }
                }
                field[x][y] = marker;
                Integer counter = locations.get(marker);
                if (counter == null) {
                    counter = 1;
                } else {
                    counter++;
                }
                locations.put(marker, counter);
            }
        }
    }

    private void setCoordinates() {
        maxX = 0;
        maxY = 0;

        for (String line : lines) {
            String[] coor = line.trim().split(", ");
            int x = Integer.parseInt(coor[0]);
            int y = Integer.parseInt(coor[1]);

            coordinates.add(new Point(x, y));

            if (x > maxX)
                maxX = x;
            if (y > maxY)
                maxY = y;
        }

        field = new int[maxX+1][maxY+1];
    }
}
