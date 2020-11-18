Feature: As a live cell I will die if I have fewer than two live neighbours
  3. As a live cell I will die if i have more than three neighbours
  Each scenario defines an initial grid with some initial live cells. The tests then check each cell to see what the
  result actually should be on the next round.
  The input grid has support for numbers instead of stars,
  to make it easy te see what cells will live and what will die.

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
  Scenario: A grid where some cells have 4 or more neighbours, covering rule 3
    Cell with 4 or more neighbours should die
    Given the grid:
    """
      .....
      .353.
      .463.
      .2...
      .....
    """
    Then the next generation should look like:
    """
      .....
      .*.*.
      ...*.
      .*...
      .....
    """