![Java CI with Gradle](https://github.com/fjank/github-workflow-kata/workflows/Java%20CI%20with%20Gradle/badge.svg)
# A Game of Life application
Based on an initial fork for working with git/pulls/issues/projects.
## Getting started
This project has been tested on JDK 8, so you need to download and install a JDK.
clone the project, then for the junit tests, just run:
```bash
$ gradlew test
```

## Testing the logic with BDD
The logic is tested with BDD and Junit tests.
As for now, there are no CI/CD integrations, so you need to clone the project to run the tests.
The BDD tests can be executed several ways (pick your poison):
### From gradle:
```bash
$ gradlew cucumber
```

### As a Junit test
Execute `src/test/java/features/RunCucumberTests`
### With a IDE plugin (e.g. Cucumber for Intellij)
Open the feature file you want the execute `src/test/resources/features` and run it.

