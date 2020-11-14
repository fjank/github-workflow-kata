package github.workflow.kata;

import github.workflow.kata.Cell;
import github.workflow.kata.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for grid functionality.
 */
public class GridTest {
    /**
     * Makes sure the grid is initialized with cells. It also checks that the cells are actually connected to the grid.
     * This is really not a junit tests, as it tests both cell and grid.
     */
    @Test
    void initializeGridHasCells() {
        int width = 3;
        int height = 3;
        Grid grid = new Grid(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = grid.getCell(i, j);
                assertNotNull(cell, "Cell at pos (" + i + ", " + j + ") was null");
                assertNotNull(cell.getParent(), "Cell at pos (" + i + ", " + j + ") has no parent");

            }
        }
    }
}
