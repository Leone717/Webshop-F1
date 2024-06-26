CREATE TABLE APP.ACCOUNT
(  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(100),
  EMAIL VARCHAR(100),
  PASSWORD VARCHAR(10));

CREATE TABLE APP.PRODUCT
(  ID INTEGER PRIMARY KEY,
  NAME VARCHAR(100),
  IMAGE VARCHAR(100),
  PRICE INTEGER,
  DESCRIPTION VARCHAR(1000));
  
CREATE TABLE APP.ORDERS
(  ID INTEGER,
  ACCOUNT_ID INTEGER,
  PRODUCT_ID INTEGER,
  AMOUNT INTEGER);    
  
CREATE SEQUENCE APP.ACCOUNT_ID START WITH 1000;  

CREATE SEQUENCE APP.PRODUCT_ID START WITH 2000;

CREATE SEQUENCE APP.ORDERS_ID START WITH 3000;

------------------------------------------------- 
DROP SEQUENCE ACCOUNT_ID RESTRICT;

DROP SEQUENCE PRODUCT_ID RESTRICT;

DROP SEQUENCE ORDERS_ID RESTRICT;

DROP TABLE APP.ORDERS

DELETE FROM APP.ORDERS

DELETE FROM APP.ACCOUNT

DELETE FROM APP.PRODUCT

SELECT NEXT VALUE FOR ACCOUNT_ID FROM ACCOUNT;

SELECT O.ID, O.AMOUNT, A.NAME, A.EMAIL, P.NAME, P.PRICE FROM APP.ACCOUNT A, APP.ORDERS O, APP.PRODUCT P WHERE
O.ACCOUNT_ID = A.ID
AND O.PRODUCT_ID = P.ID

INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'MichaelSchumacher Ferrari 2004','Schumacher.png',95000,'Michael Schumacher Ferrari 2004 F2004 1:18 Scale Model F1 Car – Canadian GP');
INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'Lewis Hamilton Mercedes F1','Hamilton.png',84000,'Lewis Hamilton 2020 Mercedes-AMG Petronas F1 Team W11 EQ Performance 1:18 Scale Model - Turkish GP');
INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'Lando Norris 2021 McLaren','Norris.png',35000,'Lando Norris 2021 McLaren MCL35M 1:18 Scale Model – Monaco GP');
INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'Max Verstappen 2021 RB16B','Verstappen.png',72000,'Max Verstappen 2021 RB16B 1:18 Scale Model – Abu Dhabi GP');
INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'Ayrton Senna McLaren F1 1988','Senna.png',89000,'Ayrton Senna McLaren F1 1988 MP4/4 1:18 Scale Model');
INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES (NEXT VALUE FOR APP.PRODUCT_ID, 'Charles Leclerc Ferrari 2022','Leclerc.png',55000,'Charles Leclerc Ferrari 2022 F1-75 1:18 Scale Model – Bahrain GP')