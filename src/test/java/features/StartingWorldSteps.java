package features;

import github.workflow.kata.GOL;
import github.workflow.kata.IOUtils;
import io.cucumber.java8.En;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingWorldSteps implements En {
    private final List<Throwable> throwables = new ArrayList<>();

    /**
     * Creates a new StartingWorldSteps, with the code to make the starting world feature work.
     */
    public StartingWorldSteps() {
        /*
         Runs the application with a -file parameter
         Application is usually started with java -jar gol.jar -file "filename",
         we simulate this by invoking main directly.
         We load our file from the classpath, store it in a temp file, and sends that location to main.
         We capture any exceptions, which are an indication that the world creation failed.
         */
        Given("a world initialized from the file {string}", (String fileLoc) -> {
            try {
                String definition = IOUtils.readFully(getClass().getResourceAsStream(fileLoc));
                Path tempFile = Files.createTempFile("gol", "world");
                Files.write(tempFile, definition.getBytes(StandardCharsets.UTF_8));
                tempFile.toFile().deleteOnExit();
                GOL.main(new String[]{"-file", tempFile.toString()});
            } catch (Throwable e) {
                throwables.add(e);
            }
        });
        /*
        Starts the application flushing the definition into standard input.
         */
        Given("a world initialized from the input", (String definition) -> {
            String input = definition + "\n\n";
            InputStream customin = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
            InputStream oldIn = System.in;
            System.setIn(customin);
            try {
                GOL.main(new String[]{});
            } catch (Throwable e) {
                throwables.add(e);
            }
            System.setIn(oldIn);
        });
        Then("the world should be created", () -> {
            throwables.forEach(Throwable::printStackTrace);
            assertEquals(0, throwables.size());
        });
    }
}
