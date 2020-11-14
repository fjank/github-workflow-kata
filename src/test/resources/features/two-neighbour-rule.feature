Feature: As a live cell I will die if I have fewer than two live neighbours
  Each scenario defines an initial grid with some initial live cells, then one cell is checked, to see if it would
  survive or die.
  Dead cells are marked as "."
  Live cells are marked as "*"
  Cell the check is marked as "x"

  Scenario: Positive test: 3x3 grid with three neighbours
    ***
    .x.
    ...
  The cell has three neighbours, so it should live
    Given a grid of size 3 x 3
    And the cell at pos 0, 0 is "live"
    And the cell at pos 0, 1 is "live"
    And the cell at pos 0, 2 is "live"
    And the cell at pos 1, 1 is "live"
    When the cell at pos 1, 1 is "checked"
    Then the checked cell should still be alive

  Scenario: Positive test: 3x3 grid with two neighbours
    **.
    .x.
    ...
  The cell has two neighbours, so it should live
    Given a grid of size 3 x 3
    And the cell at pos 0, 0 is "live"
    And the cell at pos 0, 1 is "live"
    And the cell at pos 1, 1 is "live"
    When the cell at pos 1, 1 is "checked"
    Then the checked cell should still be alive

  Scenario: Negative test: 3x3 grid with one neighbour
    *..
    .x.
    ...
  The cell has one neighbour, so it should die
    Given a grid of size 3 x 3
    And the cell at pos 0, 0 is "live"
    And the cell at pos 1, 1 is "live"
    When the cell at pos 1, 1 is "checked"
    Then the checked cell should die

  Scenario: Negative test: 3x3 grid with zero neighbours
    ...
    .x.
    ...
  The cell has zero neighbours, so it should die
    Given a grid of size 3 x 3
    And the cell at pos 1, 1 is "dead"
    When the cell at pos 1, 1 is "checked"
    Then the checked cell should die

