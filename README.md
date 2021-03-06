# Mabaya Automation Project


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Please verify that you got Chrome and Firefox (if you want to use firefox browser) installed on your local machine
* Please verify you got Java 8 and above installed on your local machine.
* You can run the program using your favorite IDE or using maven (will be explained later)

### Before Running
* Before running please set all the requirement used under this file:
```
src/main/resources/env/env.properties
```
You can set both running the program with live browser or headless (later on if you want to run it from your CI tool for regression tests)
```
selenium.headless=true
```
You can set your browser (this program supports chrome & firefox browsers)
```
ui.browser.type=CHROME
```
### Running Tests
* running using maven: (verify you are located in the source path, e.g: '\MabayaAutomationProjectAssignment')

  please run this commands:

 ```
  1. mvn clean
  2. mvn compile
  3. mvn test
  ```
The above commands will run the program

## Authors

**Erez Naim** - *Senior Automation Engineer*

Enjoy! :)
