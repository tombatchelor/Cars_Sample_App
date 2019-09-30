# DB Server Readme

The schema for the Cars DB is defined in `mysql.sql`. There is also a Docker container defined in `Dockerfile`. NOTE, at this time there is no persistant volume backing the DB, so each time the container starts it just has the schema and no data.