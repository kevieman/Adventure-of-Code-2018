import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class LineReader {
    private List<String> lines;

    LineReader(String location, String file) {
        Path inputPath = Paths.get(location, file);
        // Fill up lines
        try {
            lines = Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        assert lines != null;

    }

    List<String> getLines() {
        return lines;
    }

    List<String> getLinesSorted() {
        List<String> sorted = lines;
        Collections.sort(sorted);
        return sorted;
    }
}
