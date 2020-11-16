package github.workflow.kata;

import java.util.Objects;

/**
 * Defines a key for the grid, when doing lookups.
 */
class GridKey {
    private final int col;
    private final int row;

    /**
     * Creates a new GridKey.
     */
    public GridKey(int row, int col) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GridKey gridKey = (GridKey) o;
        return col == gridKey.col &&
                row == gridKey.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }
}
