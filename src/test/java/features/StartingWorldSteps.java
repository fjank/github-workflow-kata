package features;

import github.workflow.kata.GOL;
import github.workflow.kata.IOUtils;
import io.cucumber.java8.En;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingWorldSteps implements En {
    private final List<Throwable> throwables = new ArrayList<>();
    private final ByteArrayOutputStream appResultOutputStream = new ByteArrayOutputStream();
    private final MyInputStream appInputStream = new MyInputStream();

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
            System.setIn(appInputStream);
            System.setOut(new PrintStream(appResultOutputStream));
            appInputStream.appendData(definition + "\n\n");
            GOL.main(new String[]{});
        });
        Then("the output should be", (String expected) -> {
            String actual = new String(this.appResultOutputStream.toByteArray(), StandardCharsets.UTF_8).trim();
            assertEquals(expected.trim(), actual);
        });
        Then("the world should be created", () -> {
            throwables.forEach(Throwable::printStackTrace);
            assertEquals(0, throwables.size());
        });
        When("the return key is pressed", () -> {
            appInputStream.appendData("\n");
        });
    }

    /**
     * Nice little inputstream that allows us to let have a dynamic inputstream, very usable in BDD tests.
     */
    private static class MyInputStream extends InputStream {
        private int index = 0;
        private final StringBuilder buffer = new StringBuilder();

        @Override
        public int read() throws IOException {
            if (index >= buffer.length()) {
                return -1;
            }
            return buffer.charAt(index++);
        }

        private void appendData(String data) {
            buffer.append(data);
        }
    }
}