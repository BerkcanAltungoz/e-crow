# E-Crow - An Escrow Service for Everyone

This project is made for Akdeniz University Class CSE 492 - Senior Design Project

Made by: Berkcan Altungöz

Student Number: 20170808014

Advisor: Asst.Prof.Dr. Murat Ak :man_teacher:
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

| Recommended                | Reference                                                      |
|----------------------------|----------------------------------------------------------------|
| Oracle Java 11 JDK or higher | [Download](https://www.oracle.com/java/technologies/downloads/)|
| PostgreSQL 14.2 or higher  | [Download](https://www.postgresql.org/download/)               |
| Maven 3.8 or higher        | [Download](https://maven.apache.org/download.cgi)              |
| Git 2.36 or higher         | [Download](https://git-scm.com/downloads)                      |
| Spring Boot 2.4.5 or higher | [Website](https://spring.io/projects/spring-boot#learn)        |
| React 17 or higher         | [Website](https://reactjs.org/docs/getting-started.html)       |
| Your Preferred IDE         | [IntelliJ Idea](https://www.jetbrains.com/idea/download/)      |

## Running E-Crow Locally

* Clone the repository

* Create a PostgreSQL database and run the scripts in the [postgre_script.txt](./backend/postgre_configs/postgre_script.txt)

* Configure your [application.properties](./backend/src/main/resources/application.properties) file with your DB options.

* Move into the [backend](./backend) directory and install maven dependencies.

* Run [BackendApplication](./backend/src/main/java/ecrow/backend/BackendApplication.java) to start backend server.

* Move to the [frontend](./frontend) directory

* Run ```npm install``` to install frontend dependencies

* Run ```npm start``` to start the frontend server.

You can then access the website here: http://localhost:3000/

You can access the backend API endpoints by using swagger on this address: http://localhost:8080/swagger-ui.html#/

## Main Features
* Sign-up as an Employee
* Sign-up as a Customer
* Persisting Log-in
* Easy Sign-out on Navigation Bar
* Balance Information on Navigation Bar
* Settings for Employee/Customer
* Setting Categories
* Update Main Account Details as Customer/Employee
* View Address Information as Customer
* Update Address Information as Customer
* Add Payment Method as Customer 
* Deposit Balance as Customer
* Add Bank Account Customer/Employee
* Withdraw Balance as Customer/Employee
* Configure Employee Details for Listing
* Listing Available Employees
* Filter Available Employees by City or Town
* View Detailed Information About Employees
* Create New Transaction for an Item
* View Buying and Selling Transactions for Customer
* View Escrowing Transactions as Employee

## Screenshots
### Entity Relationship Diagram
![ERD](./backend/postgre_configs/Entity_Relationship_Diagram.png)
### API Endpoints
![API](./backend/postgre_configs/Endpoints.png)
### Main Page
![MainPage](./frontend/screenshots/MainPage.png)
### Login
![Login](./frontend/screenshots/CustomerLogin.png)
### Customer Signup
![Customer Signup](./frontend/screenshots/CustomerSignup.png)
### Employee Signup
![Employee Signup](./frontend/screenshots/EmployeeSignup.png)
### Customer Transactions
![Customer Transactions](./frontend/screenshots/CustomerTransactions.png)
### Employee Transactions
![Employee Transactions](./frontend/screenshots/EmployeeTransactions.png)
### Create New Transaction
![Create New Transaction](./frontend/screenshots/NewTransaction.png)
### Account Settings
![Account Settings](./frontend/screenshots/CustomerSettingsAccount.png)
### Bank Settings
![Bank Settings](./frontend/screenshots/CustomerSettingsBank.png)
### Customer Address Settings
![Customer Address Settings](./frontend/screenshots/CustomerSettingsAddress.png)
### Customer Payment Settings
![Customer Payment Settings](./frontend/screenshots/CustomerSettingsPayment.png)
### Employee Account Details Settings
![Employee Account Details Settings](./frontend/screenshots/EmployeeSettingsDetails.png)

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

---

![Akdeniz University Logo](http://cse.akdeniz.edu.tr/wp-content/themes/fakulte/images/logo.png)
