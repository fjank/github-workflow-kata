package github.workflow.kata;

/**
 * Definition of a single cell in GOL. Before this cell is "usable", it should be added to a grid.
 */
public class Cell {
    private Grid parent;
    private GridKey position;
    private final boolean live;

    public Cell(boolean live) {
        this.live = live;
    }

    /**
     * Sets the grid and position for this cell, so it can be usable.
     */
    void setParent(Grid parent, GridKey position) {
        this.parent = parent;
        this.position = position;
    }

    /**
     * Returns whether the cell should die or not on the next round.
     *
     * @return {@code true} if the cell should die, {@code false} otherwise.
     */
    public boolean shouldDie() {
        // First rule: As a live cell I will die if I have fewer than two live neighbours.
        if (getLiveNeighbourCount() < 2) {
            return true;
        }
        // Looks like we are to live another day
        return false;
    }

    /**
     * Get the neighbours for this cell. Check a grid of 9x9 cells around this cell.
     */
    private int getLiveNeighbourCount() {
        int returnVal = 0;
        for (int row = position.getRow() - 1; row < position.getRow() + 1; row++) {
            for (int col = position.getCol() - 1; col < position.getCol() + 1; col++) {
                // we do not want to check ourselves.
                if (row == position.getRow() && col == position.getCol()) {
                    continue;
                }

                if (parent.getCell(col, row).isLive()) {
                    returnVal++;
                }
            }
        }
        return returnVal;
    }

    private boolean isLive() {
        return live;
    }


    public Grid getParent() {
        return parent;
    }
}
