package features;

import github.workflow.kata.GOL;
import io.cucumber.java8.En;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StartingWorldSteps implements En {
    private final ByteArrayOutputStream appResultOutputStream = new ByteArrayOutputStream();

    /**
     * Creates a new StartingWorldSteps, with the code to make the starting world feature work.
     */
    public StartingWorldSteps() {
        When("application is started with -help option", () -> {
            System.setOut(new PrintStream(appResultOutputStream));
            GOL.main(new String[]{"-help"});
        });
        Then("the output should be", (String expected) -> {
            String actual = new String(this.appResultOutputStream.toByteArray(), StandardCharsets.UTF_8).trim();
            assertEquals(expected.trim(), actual);
        });
    }
}
