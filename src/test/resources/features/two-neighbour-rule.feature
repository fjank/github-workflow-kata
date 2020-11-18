Feature: Cells live or die depending on a few rules:
  1. As a live cell I will die if I have fewer than two live neighbours
  2. As a live cell I will live on if i have exactly two or three neighbours
  3. As a live cell I will die if i have more than three neighbours
  Each scenario defines an initial grid with some initial live cells. The tests then check each cell to see what the
  result actually should be on the next round.
  The input grid has support for numbers instead of stars,
  to make it easy te see what cells will live and what will die.

  Scenario: A grid where cells has only one neighbour, covering rule 1
  All cells in this grid has one or zero neighbours, so all should die.
    Given the grid:
    """
      *....
      ..**.
      .....
      **...
      ....*
    """
    Then the next generation should look like:
    """
      .....
      .....
      .....
      .....
      .....
    """

  Scenario: A grid where cells has either 2 or 3 neighbours, covering rule 2
  All cells has 2 or 3 neighbours, all should live on.
    Given the grid:
    """
      **.**
      **..*
      .....
      **..*
      **.**
    """
    Then the next generation should look like:
    """
      **.**
      **..*
      .....
      **..*
      **.**
    """

  Scenario: A grid where cells have 0, 1, 2 and 3 neighbours, covering rule 1 and 2
  Cells with 0 and 1 neighbour should die, 2 and 3 should live.
    Given the grid:
    """
      *..**
      .....
      *....
      **.**
      ...**
    """
    Then the next generation should look like:
    """
      .....
      .....
      *....
      **.**
      ...**
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