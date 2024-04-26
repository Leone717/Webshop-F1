Create Derby Database Eclipse 

Eclipse -> Data Source Explorer -> Database Connections -> Right click -> New ->
Derby -> Derby Client JDBC Driver (System Version: 10.2)

JAR List/derbyclient.jar (Unable to locate) -> Add JAR/Zip... ->
C:\Oracle\Middleware\Oracle_Home\wlserver\common\derby\lib -> derbyclient.jar 
remove the original derbyclient.jar

Specify a Driver and Connection Details

Drivers: Derby Client JDBC Driver
General
Database: 	webshop(your database name)
Host: 		localhost
Port number: 	1527
User name:	user
Password	123

X Create database (if required)
X Save password
URL: jdbc:derby://localhost:1527/webshop;create=true
X Connect when the wizard completes	---> Test Connection -> Ping succeeded!(Server runs while)
Finish -> Data Spource Explorer/Database Connections folder/ New Derby(Apache Derby v.10.14.2.0)/webshop database/schemas folder

DERBY DATABASE READY. 

New Derby(Apache Derby v.10.14.2.0) -> Right click -> Open Sql Scrapbook -> select your databse 

used_SQLs.sql

Create Derby Database Weblogic

JDBC Data Source Properties 
Home >Summary of Services: JMS >Summary of Servers >AdminServer >Summary of Services >Summary of JDBC Data Sources >Summary of Services >Summary of Services: JMS >Summary of Servers >AdminServer >Summary of JDBC Data Sources

*Name: DerbyDS
JNDI Name: DerbyDS
Database Type: Derby
Next >Next >Next

Database Name:		webshop
Host Name:		localhost
Port:			1527
Database User Name:	user
Password:		123
Confirm Password:	123

Driver Class Name:	org.apache.derby.jdbc.ClientXADataSource
URL:			jdbc:derby://localhost:1527/webshop;ServerName=localhost;databaseName=webshop;create=true
Database User Name:	user

X AdminServer

Eclipse -> Data Source Explorer -> New Derby -> Right click -> Open SQL Scrapbook -> SQLExecute the insert commands

Webshop project is ready with products

