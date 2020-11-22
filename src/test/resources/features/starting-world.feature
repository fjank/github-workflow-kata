Feature: As a user I should be able see a textual output of the game of life world
  and see which cells are dead and alive.
  As a user i should be able to increment the world to the next generation.
  This will iterate the game world to the next generation
  Scenario: Simple world that is initialized.
    Given a world initialized from the input
    """
Generation 1:
4 8
........
...**...
...**...
........
    """
    Then the output should be
    """
Generation 1:
4 8
........
...**...
...**...
........
    """
  Scenario: Simple world that is moved one generation ahead.
    Given a world initialized from the input
    """
Generation 1:
4 8
........
...**...
...**...
........
    """
    When the return key is pressed
    Then the output should be
    """
Generation 2:
4 8
........
...**...
...**...
........
    """