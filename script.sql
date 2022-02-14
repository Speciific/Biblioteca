show databases;
use mysql;
show tables;
select * from component;
CREATE TABLE Cart1 (
    NrCarte int(10),
    Nume VARCHAR(255),
    Autor VARCHAR(255),
    Editura VARCHAR(255),
    Anul year,
    Disponibil BOOLEAN
);
create table Users5
(user1 varchar(255),
telefon varchar(255),
password1 varchar(255));
Insert Into Cart1
VALUES(1, 'Ion', 'Liviu Rebreanu', 'Flacara',1920 , true);
Insert Into Users5
VALUES('a','0723562384','a');
select * from Users5;
select * from Cart1;