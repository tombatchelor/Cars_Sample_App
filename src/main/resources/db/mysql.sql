CREATE DATABASE supercars;

connect supercars;

CREATE TABLE MANUFACTURER (
    MANUFACTURER_ID MEDIUMINT NOT NULL,
    NAME VARCHAR(30),
    WEB VARCHAR(50),
    EMAIL VARCHAR(50),
    LOGO VARCHAR(30),
    PRIMARY KEY (MANUFACTURER_ID)
);

CREATE TABLE CARS (
     CAR_ID MEDIUMINT NOT NULL AUTO_INCREMENT,
     NAME VARCHAR(30),
     MODEL VARCHAR(30),
     DESCRIPTION VARCHAR(200),
     MANUFACTURER_ID MEDIUMINT NOT NULL,
     COLOUR VARCHAR(20),
     YEAR MEDIUMINT,
     PRICE FLOAT,
     SUMMARY VARCHAR(200),
     PHOTO VARCHAR(30),
     PRIMARY KEY (CAR_ID)
);

CREATE TABLE ENQUIRIES (
    ENQUIRY_ID MEDIUMINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50),
    EMAIL VARCHAR(50),
    COMMENT VARCHAR(200),
    CAR_ID MEDIUMINT,
    DUMMY MEDIUMINT,
    PRIMARY KEY (ENQUIRY_ID)
);

INSERT INTO MANUFACTURER (MANUFACTURER_ID, NAME, WEB, EMAIL, LOGO) VALUES
    (1, 'Porsche', 'http://www.porsche.com', 'web@porsche.com', 'Porsche.gif'),
    (2, 'Ferrari', 'http://www.ferrari.com/en_us/', 'web@ferrari.com','Ferrari.gif'),
    (3, 'Aston Martin','http://www.astonmartin.com','web@astonmartin.com','AstonMartin.gif'),
    (4, 'BMW', 'http://www.bmw.com/com/en/', 'web@bmw.com', 'Bmw.gif'),
    (5, 'Ford', 'http://www.ford.com', 'web@ford.com', 'Ford.gif'),
    (6, 'Jaguar', 'http://www.jaguarusa.com/index.html', 'web@jaguarusa.com', 'Jaguar.gif'),
    (7, 'Lamborghini', 'http://www.lamborghini.com/en/home/', 'web@lamborghini.com', 'Lamborghini.gif'),
    (8, 'Lotus', 'http://www.lotuscars.com', 'web@lotuscars.com', 'Lotus.gif');
