Feature: As a live cell I will die if I have fewer than two live neighbours
  Each scenario defines an initial grid with some initial live cells. The tests then check each cell to see what the
  result actually should be on the next round.

  Scenario: 3x3 grid with three neighbours
  All cells has 2 or more neighbours, all will live on.
    Given the grid:
    """
      ***
      .*.
      ...
    """
    Then the next generation should look like:
    """
      ***
      .*.
      ...
    """
  Scenario: 3x3 grid with one neighbour
  Top left and right has only one neighbour so they will die, center has two and will live.
    Given the grid:
    """
      *.*
      .*.
      ...
    """
    Then the next generation should look like:
    """
      ...
      .*.
      ...
    """
