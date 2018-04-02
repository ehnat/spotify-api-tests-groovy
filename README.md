### Project description
This project contains few tests for spotify

### Technical description
- **Used technologies:** Groovy, Gradle
- **Used frameworks/libraries:** Spock

### Necessary to download/install to run the project
1. install Java 8
2. install Groovy
3. install Gradle

### How to run test
1. clone this repo
2. put config.properties to the folder **src/test/resources** (useful variables: baseUrl, basePath,
token, refresh_token, client_id, client_secret)
3. go to folder **spotify-api-tests-groovy** (where file 'build.gradle' is located)
4. run command from command line:

  ```sh
    gradle test
  ```
5. test results are located in **build/reports** (file 'index.html')
