# KRY Poller Service

As a part of scaling the number of services running within a modern health tech company we need a way to make sure our systems are running smoothly. None of the monitoring tools that we have looked at satisfy our requirements so we have decided that we will build one ourselves. What we want you to do is to build a simple service poller that keeps a list of services (defined by a URL), and periodically performs a HTTP GET request to each and stores a record of the response ("OK" or "FAIL"). Apart from the polling logic we want to have all the services visualised and easily managed in a basic UI presenting all the services together with their status.

## Requirements
- MySQL DB (8.0.27 used in this project)
- Java 11 (Might run on other versions)
- IDE with Support for Maven

## How to run

- Clone or download the project
- Run MySQL DB server locally
- Change to your dbName, userName, and userPassword in DBConnection.java
- Build it from IDE.


## How to use

- As a Poller Service Desktop Application, the user can add, delete, update, refresh services. After modified, click 'Save' bottom to save your records to Database.
- The Poller Service periodically performs a HTTP GET request to each, and automatically update the database and shown to the user.
- URL validation to detect if the input URL is invalid. Save is not allowed when any URL is invalid.
- The user can set refresh interval time to control the rate of sending HTTP GET request to services.
- The service status on the UI won't be updated if user makes changes on service records and not save to Database.


## Frontend

- Java Swing

## Backend

- Java
- SQL
- MySQL DB

## Libraries

- MySQL Connector/J 8.0.27
- Apache HttpClient 4.5.13
- junit 4.13.2
- Mockito Core 4.0
