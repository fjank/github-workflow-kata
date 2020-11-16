package github.workflow.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A gaming grid, with specified dimensions.
 */
public class Grid {
    public Grid() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // all grids considered equal
        return true;
    }

    @Override
    public int hashCode() {
        return 42;
    }
}
