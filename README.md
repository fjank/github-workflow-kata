![Java CI with Gradle](https://github.com/fjank/github-workflow-kata/workflows/Java%20CI%20with%20Gradle/badge.svg)
# A Game of Life application
Based on an initial fork for working with git/pulls/issues/projects.
## Running gol
There are no releases yet, so to run this, you should clone this repo, then run:
```bash
./gradlew clean installDist
./build/install/gol/bin/gol
```
This application support loading a world from startdard input, or from file with a `-file` parameter.
The format should be:
Generation 1:
3 3
...
***
...
First line should be the Generation to start with, only 1 is supported.
Second line should be two numbers space separated representing the grid size.
Next lines are the actual grid of the size mentioned above.
## Getting started - development
This project has been tested on JDK 8, so you need to download and install a JDK.
clone the project, then for the junit tests, just run:
```bash
$ gradlew test
```

## Testing the logic with BDD
The logic is tested with BDD and Junit tests.
Each time you push to a branch a github actioon is invoked that will build the application.
If you want to run them manually, the BDD tests can be executed several ways (pick your poison):
### From gradle:
```bash
$ gradlew cucumber
```

### As a Junit test
Execute `src/test/java/features/RunCucumberTests`

### With a IDE plugin (e.g. Cucumber for Intellij)
Open the feature file you want the execute `src/test/resources/features` and run it.

