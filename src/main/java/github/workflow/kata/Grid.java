package github.workflow.kata;

import java.util.Objects;

/**
 * A gaming grid.
 */
public class Grid {
    private final String gridDef;

    public Grid(String gridDef) {
        this.gridDef = gridDef;
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
