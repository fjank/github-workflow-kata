package features;

import github.workflow.kata.Grid;
import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains the Java implementation of the BDD steps for the two neighbour rule feature.
 */
public class TwoNeighbourRuleSteps implements En {
    private Grid grid;

    /**
     * Creates a new StepDefinition.
     */
    public TwoNeighbourRuleSteps() {
        Given("the grid:", (String gridDef) -> {
            grid = new Grid(gridDef);
        });

        Then("the next generation should look like:", (String gridDef) -> {
            Grid expected = new Grid(gridDef);
            grid.nextGeneration();
            assertEquals(expected, grid);
        });
    }
}
