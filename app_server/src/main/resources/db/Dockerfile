FROM mysql:5.7.15

MAINTAINER me

ENV MYSQL_DATABASE=supercars \
    MYSQL_ROOT_PASSWORD=AppDynamics

ADD mysql.sql /docker-entrypoint-initdb.d

EXPOSE 3306
