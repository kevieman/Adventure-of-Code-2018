import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Day 1 Advent of Code
 * Author: Kevin Tabak
 */
public class D1_ChronalCalibration {
    private int frequency;
    private Path filePath;
    private List<String> lines;
    private ArrayList<Integer> seenFreq;

    /**
     * Runs the program
     * @param args
     */
    public static void main(String[] args) {
        D1_ChronalCalibration puzzle = new D1_ChronalCalibration("assets", "Day1-1Input.txt");
        puzzle.calculate();
        puzzle.calibrate();
    }

    /**
     * Constructor
     * @param location File location (Example: 'C\\')
     * @param file Filename (Example: 'text.txt')
     */
    private D1_ChronalCalibration(String location, String file){
        frequency = 0;
        filePath = Paths.get(location, file);
        seenFreq = new ArrayList<>();
    }

    /**
     * Part 1 of day 1
     * Calculates the end frequency.
     */
    private void calculate(){
        seenFreq.add(frequency);
        try {
            lines = Files.readAllLines(filePath);
            for (String line : lines){
                setFrequency(line);
            }
            System.out.println("The frequency is: " + frequency);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Part 2 of day 1
     * Prints the frequency that we see twice for the first time.
     */
    private void calibrate(){
        seenFreq.clear();
        frequency = 0;
        seenFreq.add(frequency);
        Integer seenBefore = null;
        while (seenBefore == null){
            seenBefore = calibrateLoop();
        }
        System.out.println("Frequency: "+seenBefore+" is the first to be seen twice.");
    }

    /**
     * Checks if a frequency has been seen before.
     * @return Null when frequency has not been seen before. Otherwise the frequnecy that has been seen before.
     */
    private Integer calibrateLoop(){
        try {
            lines = Files.readAllLines(filePath);
            for (String line : lines){
                setFrequency(line);
                if(seenFreq.contains(frequency)){
                    return frequency;
                }
                seenFreq.add(frequency);
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * Changes the freqentie according to the line. + will add, - will remove.
     * @param line The line with +[number] or -[number]
     */
    private void setFrequency(String line){
        int nr;
        if (line.contains("+")){
            nr = Integer.parseInt(line.replace("+", ""));
            frequency += nr;
        } else if (line.contains("-")) {
            nr = Integer.parseInt(line.replace("-", ""));
            frequency -= nr;
        }
    }
}
