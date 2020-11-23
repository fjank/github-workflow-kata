Feature: Main application should have a -help option
  As a user i should be able to increment the world to the next generation.
  This will iterate the game world to the next generation
  Scenario: Start GOL with -help option
    When application is started with -help option
    Then the output should be
    """
You can either load a world from a file with -file option,
or manually entering the world definition after starting GOL with no arguments.
The definition should be as the following example:
Generation 1
3 3
...
***
...

You finish the grid definition with an empty line.
"""
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