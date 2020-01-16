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
    (8, 'Lotus', 'http://www.lotuscars.com', 'web@lotuscars.com', 'Lotus.gif'),
    (9, 'McLaren', 'https://cars.mclaren.com/en', 'web@mclaren.com', 'McLaren.png');

INSERT INTO CARS(NAME, MODEL, DESCRIPTION, MANUFACTURER_ID, COLOUR, YEAR, PRICE, SUMMARY, PHOTO) VALUES
    ("GTB", "488", "Low mileage with high spec!", 2, "Red", 2018, 130000, "This is a excellent example offered at a price for a quick sale", "IMG_1.jpeg"),
    ("35i", "X1", "Leather interior, navigation", 4, "Blue", 2013, 13000, "Offered for a quick sale, reliable small SUV", "IMG_2.jpeg"),
    ("GT3", "911", "Great track day car", 1, "Silver", 2018, 135000, "Track day car that can be used daily", "IMG_3.jpeg"),
    ("Base", "DB11", "Great looking car", 3, "Black", 2019, 168000, "Great", "IMG_4.jpeg"),
    ("Raptor", "F150", "Performance truck", 5, "Black", 2018, 76000, "Roush", "IMG_5.jpeg"),
    ("V8", "F-Type", "Classic British GT", 6, "Green", 2017, 45000, "Great car!", "IMG_6.jpeg"),
    ("LP580-2", "Huracan", "2 wheel drive", 7, "Orange", 2016, 180000, "Great!", "IMG_7.jpeg"),
    ("V6", "Exige", "British track car", 8, "Black", 2017, 55000, "Wonderful", "IMG_8.jpeg"),
    ("Spider", "720S", "Fantastic", 9, "Blue", 2019, 345000, "Great", "IMG_9.jpeg");
