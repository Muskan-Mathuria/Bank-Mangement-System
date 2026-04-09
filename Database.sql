drop database banksystem;
CREATE DATABASE  banksystem;
USE banksystem;


CREATE TABLE signup (
  formno VARCHAR(20) PRIMARY KEY,
  name VARCHAR(50),
  father_name VARCHAR(50),
  dob DATE,
  gender VARCHAR(20),
  email VARCHAR(50),
  marital VARCHAR(20),
  address VARCHAR(100),
  city VARCHAR(50),
  pincode VARCHAR(10),
  state VARCHAR(50)
);


DROP TABLE signuptwo;

CREATE TABLE signuptwo (
  formno VARCHAR(20),
  religion VARCHAR(20),
  category VARCHAR(20),
  income VARCHAR(30),   
  education VARCHAR(50),
  occupation VARCHAR(50),
  pan VARCHAR(20),
  aadhar VARCHAR(20),
  seniorcitizen VARCHAR(10),
  existingaccount VARCHAR(10)
);

CREATE TABLE signupthree (
  formno VARCHAR(20),
  accountType VARCHAR(40),
  cardnumber VARCHAR(25),
  pin VARCHAR(10),
  facility VARCHAR(100)
);


CREATE TABLE login (
  formno VARCHAR(20),
  cardnumber VARCHAR(25),
  pin VARCHAR(10)
);


CREATE TABLE bank (
  pin VARCHAR(10),
  date datetime,
  type VARCHAR(20),
  amount DECIMAL(10,2)
);


SHOW TABLES;
