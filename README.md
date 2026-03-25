# Selenium Template
This is a selenium template project.

## Built With
* [Java](https://www.oracle.com/java/technologies/downloads/#java21) - Programming Language (Oracle JDK 21)
* [Maven](https://maven.apache.org/download.cgi) - Build Management
* [Selenium](https://selenium.dev/) - Framework for automated control of a web browser
* [TestNG](https://testng.org/) - Unit Test Framework
* [ExtentReports](http://extentreports.com/) - Reporting Framework

The current versions of the individual components can be found in [pom.xml](pom.xml).

## Getting Started

### Requirements
Java and Maven must be set up on the respective execution environment.

To run the test locally, the browsers Mozilla Firefox and Google Chrome must be installed.

### Installation
To download all necessary dependencies, please run the following Maven command:

```
mvn clean test-compile
```

### Test Execution from command line

#### Without additional params
```
mvn clean test -DsuiteXmlFile="testsuites/ExampleTestSuite.xml"
```

#### With additional params
```
mvn clean test -DsuiteXmlFile="testsuites/ExampleTestSuite.xml" -DdriverType="FIREFOX" -DtestEnvironment="http://localhost:3000/"
```

#### Parameter explanation
* driverType - specifies which WebDriver should be used (and therefore which browser)
* testEnvironment - URL to the Application Under Test
* suiteXmlFile - specifies which TestSuite should be executed

The **driverType** parameter can have the following values:

* CHROME - local WebDriver for Google Chrome
* FIREFOX - local WebDriver for Mozilla Firefox
* CHROME-REMOTE - RemoteWebDriver for remote execution via Selenium Hub -> Chrome Node
* FIREFOX-REMOTE - RemoteWebDriver for remote execution via Selenium Hub -> Firefox Node

If the parameter has no value, a local Chrome driver is used by default.

Note: The WebDriver instances are created with help of
[WebDriverManager](https://github.com/bonigarcia/webdrivermanager)

-----

### Test Execution in IDE (Bsp. IntelliJ)

#### 1. Prepare property file

#### 2. Define TestNG configuration

#### 3. Run test suite

#### 4. Show report

-----

## Documentation
Link tbd

-----

## Contact
accompio PrimeTec Gmbh - [info@pro.accompio.com]()