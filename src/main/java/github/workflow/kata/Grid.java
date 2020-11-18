package github.workflow.kata;

import java.util.Arrays;

/**
 * A gaming grid.
 */
public final class Grid {
    private char[][] grid;

    /**
     * Extract the grid and store it as a char[][]
     * Makes sure the gridDef has no spaces, as equals does a string comparison, while the input can have spaces
     * around the grid definition.
     *
     * @param definition The string containing the grid definition, may have spaces around the grid.
     */
    public Grid(String definition) {
        String[] rows = definition.trim().split("\n");
        grid = new char[rows.length][];
        for (int row = 0; row < rows.length; row++) {
            grid[row] = rows[row].trim().toCharArray();
        }
    }

    /**
     * Invokes the rules for the next generation to the cells for this grid.
     */
    public void nextGeneration() {
        int rowCount = grid.length;
        int colCount = grid[0].length;
        char[][] newGrid = new char[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                char ch = grid[row][col];
                // Rule: As a live cell I will die if I have fewer than two live neighbours
                int liveCount = getLiveCountAround(row, col);
                if (ch == '*') {
                    newGrid[row][col] = liveCount < 2 ? '.' : '*';
                } else {
                    // cell is dead, check rule 4: As a dead cell I will regain life if i have exactly three neighbours
                    if (liveCount == 3) {
                        newGrid[row][col] = '*';
                    } else {
                        newGrid[row][col] = '.';
                    }
                }
            }
        }
        // now replace old definition with new definition.
        grid = newGrid;
    }

    /**
     * Count the live cells using the surrounding 9 cells.
     */
    private int getLiveCountAround(int rowIdx, int colIdx) {
        int liveCount = 0;
        int startRow = Math.max(0, rowIdx - 1);
        int endRow = Math.min(grid.length - 1, rowIdx + 1);
        int startCol = Math.max(0, colIdx - 1);
        int endCol = Math.min(grid[0].length - 1, colIdx + 1);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                // skip ourselves.
                if (row == rowIdx && col == colIdx) {
                    continue;
                }
                if (grid[row][col] == '*') {
                    liveCount++;
                }
            }
        }
        return liveCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grid other = (Grid) o;
        return Arrays.deepEquals(this.grid, other.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }
}
