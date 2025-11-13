# Testing Exercise – Selenium & Cucumber (Java)

This repository contains a small testing exercise built with **Java 17**, **Selenium WebDriver** and **Cucumber (BDD)**.  
The goal of the project is to practice UI test automation, step definitions and Gherkin feature files using the Maven + JUnit stack.

## Tech stack

- **Language:** Java 17
- **Build tool:** Maven
- **Test framework:** JUnit 4
- **BDD framework:** Cucumber (cucumber-java, cucumber-junit)
- **Browser automation:** Selenium WebDriver
- **Other libs:** Spring Context, Lombok

All dependencies are defined in [`pom.xml`](pom.xml).

## Project structure

A typical layout for this project is:

```text
Testing_exercise/
├── pom.xml
├── .gitignore
├── src
│   ├── main
│   │   └── java
│   │       └── ... (support / utility classes, if any)
│   └── test
│       ├── java
│       │   └── stepdefs/          # Cucumber step definitions (e.g. EBAStepDefs)
│       └── resources
│           └── features/          # .feature files written in Gherkin
