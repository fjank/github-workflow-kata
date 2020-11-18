Feature: Cells live or die depending on a few rules:
  1. As a live cell I will die if I have fewer than two live neighbours
  2. As a live cell I will live on if i have exactly two or three neighbours
  4. As a dead cell I will regain life if i have exactly three neighbours
  Each scenario defines an initial grid with some initial live cells. The tests then check each cell to see what the
  result actually should be on the next round.

  Scenario: A grid where cells has only one neighbour, covering rule 1 with rule 4 additions.
  All cells in this grid has one or zero neighbours, so all should die, a few will respawn.
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
      .**..
      .....
      .....
    """

  Scenario: A grid where cells has either 2 or 3 neighbours, covering rule 2, with rule 4 additions
  All cells has 2 or 3 neighbours, all should live on, a few will be added.
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
      *****
      *****
      .....
      *****
      *****
    """

  Scenario: A grid where cells have 0, 1, 2 and 3 neighbours, covering rule 1 and 2, with rule 4 additions
  Cells with 0 and 1 neighbour should die, 2 and 3 should live, a few will respawn
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
      **...
      *****
      ..***
    """

  Scenario: A grid where dead cells have 3 neighbours, covering rule 4
  Dead cells with exactly 3 neighbours should become alive.
    Given the grid:
    """
      .*...
      **...
      .....
      .***.
      .....
    """
    Then the next generation should look like:
    """
      **...
      **...
      *....
      ..*..
      ..*..
    """
    