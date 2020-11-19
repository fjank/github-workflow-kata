Feature: Main application should have a -help option
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