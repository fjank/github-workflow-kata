package github.workflow.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    /**
     * Test for issue 4: As a user I should be able see a textual output of the game of life world and see which cells
     * are dead and alive. when a grid is initialized, the internal representation is serialized with printOutput()
     */
    @Test
    void textualOutput() {
        // Setup
        String input = "Generation 1:\n" +
                "4 8\n" +
                "........\n" +
                "...**...\n" +
                "...**...\n" +
                "........";
        String expected = "Generation 1:\n" +
                "4 8\n" +
                "........\n" +
                "...**...\n" +
                "...**...\n" +
                "........";
        // Execute
        Grid grid = new Grid(input);
        String result = grid.printOutput();

        // Assert
        Assertions.assertEquals(expected, result);
    }
}
