# Cars Sample App Readme

This is a simple Struts 1 app which provides for a Super car store which has a couple of performance/code issues. The app can be build with Maven.

## Database

The app uses a MySQL DB in the backend, this be default is expected to be the "supercars" schema, and MySQL running locally to the app. The schema build is in src/main/resources/db/mysql.sql. Execute this script against the schema you create in MySQL.

The datasouce is defined in context.xml in src/webapp/META-INF