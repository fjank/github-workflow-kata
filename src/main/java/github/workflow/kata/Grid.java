package github.workflow.kata;

import java.util.Objects;

/**
 * A gaming grid.
 */
public class Grid {
    private String gridDef;

    public Grid(String gridDef) {
        // make sure the gridDef has no spaces, as equals does a string comparison, while the input can have spaces
        // around the grid definition.
        String niceDef = "";
        String[] rows = gridDef.trim().split("\n");
        for (String s : rows) {
            char[] cols = s.trim().toCharArray();
            for (char c : cols) {
                niceDef += c;
            }
            // add a new-line
            niceDef += '\n';
        }
        this.gridDef = niceDef;
    }

    public void nextGeneration() {
        // so, lets try to implement the actual code.
        // not so happy with the implementation, it's "a bit" dirty, but it works. Refactor this later.
        String newDef = "";
        String[] rows = gridDef.split("\n");
        for (int row = 0; row < rows.length; row++) {
            char[] cols = rows[row].toCharArray();
            for (int col = 0; col < cols.length; col++) {
                char ch = cols[col];
                // Rule: As a live cell I will die if I have fewer than two live neighbours
                if (ch == '*') {
                    // check the surrounding 9 cells.
                    int liveCount = 0;
                    int startRow = Math.max(0, row - 1);
                    int endRow = Math.min(rows.length-1, row + 1);
                    int startCol = Math.max(0, col - 1);
                    int endCol = Math.min(cols.length-1, col + 1);
                    for (int i = startRow; i <= endRow; i++) {
                        for (int j = startCol; j <= endCol; j++) {
                            // skip ourselves.
                            if (i == row && j == col) {
                                continue;
                            }
                            if (rows[i].toCharArray()[j] == '*') {
                                liveCount++;
                            }
                        }
                    }
                    if (liveCount < 2) {
                        // cell will die.
                        newDef += '.';
                    } else {
                        // cell will still be alive.
                        newDef += '*';
                    }
                } else {
                    // cell is dead, it will also be dead next round.
                    newDef += '.';
                }
            }
            // add a new-line
            newDef += '\n';
        }
        // now replace old definition with new definition.
        gridDef = newDef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return gridDef.equals(grid.gridDef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gridDef);
    }
}
