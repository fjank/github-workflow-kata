package features;

import github.workflow.kata.GOL;
import io.cucumber.java8.En;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingWorldSteps implements En {
    private final ByteArrayOutputStream appResultOutputStream = new ByteArrayOutputStream();

    /**
     * Creates a new StartingWorldSteps, with the code to make the starting world feature work.
     */
    public StartingWorldSteps() {
        /*
        Starts the application flushing the definition into standard input.
         */
        Given("a world initialized from the input", (String definition) -> {
            String input = definition + "\n\n";
            InputStream customin = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
            InputStream oldIn = System.in;
            System.setIn(customin);
            System.setOut(new PrintStream(appResultOutputStream));
            GOL.main(new String[]{});
            System.setIn(oldIn);
        });
        Then("the output should be", (String expected) -> {
            String actual = new String(this.appResultOutputStream.toByteArray(), StandardCharsets.UTF_8).trim();
            assertEquals(expected.trim(), actual);
        });
    }
}
