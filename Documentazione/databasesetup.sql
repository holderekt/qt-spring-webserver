DROP DATABASE IF EXISTS MapDB;
CREATE DATABASE  MapDB;
CREATE USER IF NOT EXISTS 'MapUser'@'localhost' IDENTIFIED BY 'map';
GRANT CREATE, SELECT, INSERT, DELETE ON MapDB.* TO MapUser@localhost IDENTIFIED BY 'map';
CREATE TABLE MapDB.playtennis(
    outlook varchar(10),
    temperature float(5,2),
    umidity varchar(10),
    wind varchar(10),
    play varchar(10)
);

insert into MapDB.playtennis values('sunny', 30.3, 'high', 'weak', 'no' );
insert into MapDB.playtennis values('sunny', 30.3,'high', 'strong', 'no' );
insert into MapDB.playtennis values('overcast', 30.0,'high', 'weak', 'yes' );
insert into MapDB.playtennis values('rain', 13.0,'high', 'weak', 'yes' );
insert into MapDB.playtennis values('rain', 0.0,'normal', 'weak', 'yes' );
insert into MapDB.playtennis values('rain', 0.0,'normal', 'strong', 'no' );
insert into MapDB.playtennis values('overcast', 0.1,'normal', 'strong', 'yes' );
insert into MapDB.playtennis values('sunny', 13.0,'high', 'weak', 'no' );
insert into MapDB.playtennis values('sunny', 0.1,'normal', 'weak', 'yes' );
insert into MapDB.playtennis values('rain', 12.0,'normal', 'weak', 'yes' );
insert into MapDB.playtennis values('sunny', 12.5,'normal', 'strong', 'yes' );
insert into MapDB.playtennis values('overcast',12.5 ,'high', 'strong', 'yes' );
insert into MapDB.playtennis values('overcast', 29.21,'normal', 'weak', 'yes' );
insert into MapDB.playtennis values('rain', 12.5,'high', 'strong', 'no' );


CREATE TABLE MapDB.testtable(
    testval1 varchar(10),
    testval2 float(5,2),
    testval3 float(5,2),
    testval4 varchar(10)
);

insert into MapDB.testtable values ('valore1', 10.5, 16.7, 'test1');
insert into MapDB.testtable values ('valore2', 89.5, 55.1, 'test2');
insert into MapDB.testtable values ('valore3', 16.4, 25.7, 'test2');
insert into MapDB.testtable values ('valore1', 89.4, 18.7, 'test2');
insert into MapDB.testtable values ('valore2', 80.7, 16.8, 'test1');