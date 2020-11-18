Feature:
  Should be able to read a world from file or standard input on the format:
Generation 1:
3 3
...
***
...
and create it's own internal representation of the world
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