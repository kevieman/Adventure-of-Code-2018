import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class D1_ChronalCalibration {
    private int frequency;
    private Path filePath;
    private List<String> lines;
    private Charset charset;

    public static void main(String[] args) {
        D1_ChronalCalibration puzzle = new D1_ChronalCalibration();
        puzzle.calculate();
    }

    private D1_ChronalCalibration(){
        frequency = 0;
        filePath = Paths.get("assets", "Day1-1Input.txt");
        charset = Charset.forName("ISO-8859-1");
    }

    private void calculate(){
        try {
            lines = Files.readAllLines(filePath, charset);
            for (String line : lines){
                if (line.contains("+")){
                    line = line.replace("+", "");
                    int nr = Integer.parseInt(line);
                    frequency += nr;
                } else if (line.contains("-")) {
                    line = line.replace("-", "");
                    int nr = Integer.parseInt(line);
                    frequency -= nr;
                }
            }
            System.out.println("The frequency is: " + frequency);
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
