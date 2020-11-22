package features;

import github.workflow.kata.GOL;
import io.cucumber.java8.En;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingWorldSteps implements En {
    private final ByteArrayOutputStream appResultOutputStream = new ByteArrayOutputStream();
    private final MyInputStream appInputStream = new MyInputStream();

    /**
     * Creates a new StartingWorldSteps, with the code to make the starting world feature work.
     */
    public StartingWorldSteps() {
        When("application is started with -help option", () -> {
            System.setOut(new PrintStream(appResultOutputStream));
            GOL.main(new String[]{"-help"});
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