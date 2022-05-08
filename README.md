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

*Further details will be added on how to configure the database and frontend*

## Why Spring Boot?

Spring is a very popular Java-based framework for building web and enterprise applications. Unlike many other frameworks, which focus on only one area, 
Spring framework provides a wide verity of features addressing the modern business needs via its portfolio project. The main goal of the Spring Boot 
framework is to reduce overall development time and increase efficiency by having a default setup for unit and integration tests.

In relation to Spring, Spring Boot aims to make it easy to create Spring-powered, production-grade applications and services with minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.

## Why ReactJS?


* **Simplicity**

ReactJS is just simpler to grasp right away. The component-based approach, well-defined lifecycle, and use of just plain JavaScript make React very simple to learn, build a professional web (and mobile applications), and support it. 

* **Easy to learn**

Anyone with a basic previous knowledge in programming can easily understand React while Angular and Ember are referred to as ‘Domain-specific Language’, implying that it is difficult to learn them. To react, you just need basic knowledge of CSS and HTML.

* **Native Approach**

React can be used to create mobile applications (React Native). And React is a diehard fan of reusability, meaning extensive code reusability is supported.

* **Data Binding**

React uses one-way data binding and an application architecture called Flux controls the flow of data to components through one control point – the dispatcher. It's easier to debug self-contained components of large ReactJS apps.

* **Performance**

React does not offer any concept of a built-in container for dependency. You can use Browserify, Require JS, EcmaScript 6 modules which we can use via Babel, ReactJS-di to inject dependencies automatically.

* **Testability**

ReactJS applications are super easy to test. React views can be treated as functions of the state, so we can manipulate with the state we pass to the ReactJS view and take a look at the output and triggered actions, events, functions, etc.

## Why PostgreSQL?

PostgreSQL is a powerful, open source object-relational database system that uses and extends the SQL language combined with many features that safely store and scale the most complicated data workloads.

While being **free and open source**, PostgreSQL is highly extensible. For example, you can define your own data types, build out custom functions, even write code from different programming languages without recompiling your database!

PostgreSQL tries to conform with the SQL standard where such conformance does not contradict traditional features or could lead to poor architectural decisions. Many of the features required by the SQL standard are supported, though sometimes with slightly differing syntax or function. Further moves towards conformance can be expected over time. As of the version 14 release in September 2021, PostgreSQL conforms to at least 170 of the 179 mandatory features for SQL:2016 Core conformance. As of this writing, no relational database meets full conformance with this standard.
![Akdeniz University Logo](http://cse.akdeniz.edu.tr/wp-content/themes/fakulte/images/logo.png)
