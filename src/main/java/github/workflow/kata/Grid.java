package github.workflow.kata;

import java.util.HashMap;
import java.util.Map;

/**
 * A gaming grid, with specified dimensions.
 */
public class Grid {
    private final Map<GridKey, Cell> map = new HashMap<>();
    /**
     * Creates a new grid with the specified width and height.
     *
     * @param width  the width of the grid to create.
     * @param height the height of the grid to create.
     */
    public Grid(int width, int height) {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                setCell(col, row, new Cell(false));
            }
        }
    }

    /**
     * Sets a cell at a specific grid position.
     *
     * @param cell the cell to set
     * @param col  the column where the cell should be put
     * @param row  the row where the cell should be put
     */
    public void setCell(int col, int row, Cell cell) {
        GridKey key = new GridKey(col, row);
        cell.setParent(this, key);
        map.put(key, cell);
    }

    /**
     * Gets a cell at a specified position.
     *
     * @param col the column to get the cell for
     * @param row the row to get the cell for
     * @return the found cell
     */
    public Cell getCell(int col, int row) {
        return map.get(new GridKey(col, row));
    }
}
