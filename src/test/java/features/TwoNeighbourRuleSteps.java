package features;

import github.workflow.kata.Cell;
import github.workflow.kata.Grid;
import io.cucumber.java8.En;

import static org.junit.Assert.*;

/**
 * Contains the Java implementation of the BDD steps for the two neighbour rule feature.
 */
public class TwoNeighbourRuleSteps implements En {
    private Grid grid;
    private Cell cell;

    /**
     * Creates a new StepDefinition.
     */
    public TwoNeighbourRuleSteps() {
        Given("a grid of size {int} x {int}", (Integer width, Integer height) -> {
            grid = new Grid(width, height);
        });

        Given("the cell at pos {int}, {int} is {string}", (Integer col, Integer row, String status) -> {
            if ("checked".equals(status)) {
                // Get the cell, as if the world has moved ahead.
                cell = grid.getCell(col, row);
            } else {
                grid.setCell(col, row, new Cell("live".equals(status)));
            }
        });

        Then("the checked cell should die", () -> {
            assertTrue("Expected cell to die, but it stayed alive", cell.shouldDie());
        });
        Then("the checked cell should still be alive", () -> {
            assertFalse("Expected cell to stay alive, but it died", cell.shouldDie());
        });
    }
}
