# E-Crow - An Escrow Service for Everyone

This project is made for Akdeniz University Class CSE 492 Senior Design Project

Made by:Berkcan Altungöz

Student Number: 20170808014

Advisor: Murat Ak :man_teacher:
## Description

**E-Crow** is a **web-based escrow service** that is made to solve a big problem that hasn’t been solved in buying an item from the second-hand market. It aims to solve trust,
time availability, security, and problems with the transfer of funds when buying a second-hand property.

Everyone has experienced wanting to buy a second-hand item has experienced that situation when you
have a better-looking deal present on the website, but it is too far away, and you have to be content
with what is present near your location and if you have none at all you have to give up on buying that
item second hand. E-Crow will solve this problem by allowing you to hire a person(s) to look at the item
you want to buy, confirm it suits your needs/specifications, hold the money until you receive the
product and delivers it to the seller.

## Project Dependencies for Development (Recommended Versions)

E-Crow is built using the tools and libraries below. If you wish to tweak and look at the internal structure the recommended versions are listed.

| Recommended                  | Reference                                                      |
|------------------------------|----------------------------------------------------------------|
| Oracle Java 11 JDK or higher | [Download](https://www.oracle.com/java/technologies/downloads/)|
| PostgreSQL 14.2 or higher    | [Download](https://www.postgresql.org/download/)               |
| Maven 3.8 or higher          | [Download](https://maven.apache.org/download.cgi)              |
| Git 2.36 or higher           | [Download](https://git-scm.com/downloads)                      |
| Spring Boot 2.6 or higher    | [Website](https://spring.io/projects/spring-boot#learn)        |
| React 18.1 or higher         | [Website](https://reactjs.org/docs/getting-started.html)       |
| Your Preferred IDE           | [IntelliJ Idea](https://www.jetbrains.com/idea/download/)      |

## Running E-Crow Locally

You should build a jar file using [Maven](https://spring.io/guides/gs/maven/) after cloning from Github and run it from the command line.  

```
git clone https://github.com/BerkcanAltungoz/e-crow
cd e-crow
./mvnw package
java -jar target/*.jar
```
You can then access the website here: http://localhost:8080/

*Further details will be added on how to configure the database*

![Akdeniz University Logo](https://cdn.freelogovectors.net/wp-content/uploads/2017/04/akdeniz-university-logo.png)
