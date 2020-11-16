package github.workflow.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A gaming grid, with specified dimensions.
 */
public class Grid {
    private final Map<GridKey, Boolean> map = new HashMap<>();
    private int rowCount;
    private int colCount;

    /**
     * Creates a new grid with specified grid definition.
     *
     * @param gridDef The definition of a grid. May contain whitespace around the grid. Example looks like:
     * <pre>
     * ......
     * ....*.
     * ....*.
     * ......
     * </pre>
     */
    public Grid(String gridDef) {
        // parse the grid definition and populate a grid object.
        // Split into separate rows...
        String[] rows = gridDef.trim().split("\n");
        rowCount = rows.length;
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            String trimmedRow = rows[rowIdx].trim();
            colCount = trimmedRow.length();
            // Now split into separate cols...
            for (int colIdx = 0; colIdx < trimmedRow.length(); colIdx++) {
                char ch = trimmedRow.charAt(colIdx);
                // We now simply support . as dead cells, all other are live cells.
                map.put(new GridKey(rowIdx, colIdx), '.' != ch);

            }
        }
    }

    /**
     * Moves ahead one generation, all cells are checked to determine what the next state should be, finally the old
     * cell states are replaced with the new cell state.
     */
    public void incrementGeneration() {
        Map<GridKey, Boolean> nextGen = new HashMap<>();
        // go through each cell by looping through row and cols.
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                // check the rules for each cell and store the state in a new state
                // First rule: As a live cell I will die if I have fewer than two live neighbours.
                boolean live = map.get(new GridKey(row, col));
                if (live) {
                    boolean shouldDie = getLiveNeighbourCount(row, col) < 2;
                    nextGen.put(new GridKey(row, col), !shouldDie);
                } else {
                    // Was not live, should still be dead.
                    nextGen.put(new GridKey(row, col), false);
                }
            }
        }
        // finally replace the old states with the new states.
        map.clear();
        map.putAll(nextGen);
    }

    /**
     * Get the neighbours for a cell at a specific position. Check a grid of 9x9 cells around this cell.
     */
    private int getLiveNeighbourCount(int row, int col) {
        int returnVal = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                // we do not want to check ourselves.
                if (i == row && j == col) {
                    continue;
                }
                GridKey key = new GridKey(i, j);
                if (!map.containsKey(key)) {
                    continue;
                }
                boolean live = map.get(key);
                if (live) {
                    returnVal++;
                }
            }
        }
        return returnVal;
    }

    // two grids of the same size, and the same cell liveness are considered equal.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grid grid = (Grid) o;
        return rowCount == grid.rowCount &&
                colCount == grid.colCount &&
                map.equals(grid.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, rowCount, colCount);
    }

    /**
     * Returns a string representing this grid as . for dead cells, * as live cells. Makes it easy to debug.
     */
    public String toString() {
        StringBuilder rv = new StringBuilder(getClass().getSimpleName() + " {\n");
        for (int row = 0; row < rowCount; row++) {
            rv.append("\t");
            for (int col = 0; col < colCount; col++) {
                rv.append(map.get(new GridKey(row, col)) ? "*" : ".");
            }
            rv.append("\n");
        }
        rv.append("}");
        return rv.toString();
    }
}
