Feature:
  Should be able to read a world from file or standard input on the format:
Generation 1:
3 3
...
***
...
and create it's own internal representation of the world
  Also, As a user i should be able to increment the world to the next generation.
  This will iterate the game world to the next generation.
  Scenario: internal representation from file
    Given a world initialized from the file "/worlds/3x3-basic.txt"
    Then the world should be created

  Scenario: internal representation from standard input
    Given a world initialized from the input
    """
    Generation 1:
    3 3
    ...
    ***
    ...
    """
    Then the world should be created
  Scenario: Simple world that is moved one generation ahead with the return keu
    Given a world initialized from the input
    """
Generation 1:
4 8
........
....*...
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