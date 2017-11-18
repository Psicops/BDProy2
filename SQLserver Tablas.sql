create database Proy2

use Proy2

GO

create table Confederaciones(
	nombre varchar(55), codigo varchar(10) primary key)

create table Equipos(
	Pais varchar(30), codigo varchar(3) primary key)

create table Grupos(
	Grupo varchar(1) not null,
	Confederacion varchar(10) foreign key references Confederaciones,
	Equipo varchar(3) foreign key references Equipos not null)

alter table Grupos add primary key (Grupo, Equipo)

insert into Confederaciones values
	('Confederaci�n Centroamericana y del Caribe de F�tbol', 'Concacaf'),
	('Uni�n Europea de F�tbol Asociado', 'UEFA'),
	('Confederaci�n Sudamericana de F�tbol', 'Conmebol'),
	('Confederaci�n de f�tbol de Africa', 'CAF'),
	('Confederaci�n de f�tbol de Asia','AFC'),
	('Confederaci�n de f�tbol de Ocean�a','OFC')

insert into Equipos values
	('BRUNEI DARUSSALAM','BN'),('BRITISH INDIAN OCEAN TERRITORY','IO'),('BRAZIL','BR'),('BOUVET ISLAND','BV'),
	('BOTSWANA','BW'),('BOSNIA AND HERZEGOVINA','BA'),('BOLIVIA','BO'),('BHUTAN','BT'),
	('BERMUDA','BM'),('BENIN','BJ'),('BELIZE','BZ'),('BELGIUM','BE'),
	('BELARUS','BY'),('BARBADOS','BB'),('BANGLADESH','BD'),('BAHRAIN','BH'),
	('BAHAMAS','BS'),('AZERBAIJAN','AZ'),('AUSTRIA','AT'),('AUSTRALIA','AU'),
	('ARUBA','AW'),('ARMENIA','AM'),('ARGENTINA','AR'),('ANTIGUA AND BARBUDA','AG'),
	('ANTARCTICA','AQ'),('ANGUILLA','AI'),('ANGOLA','AO'),('ANDORRA','AD'),
	('AMERICAN SAMOA','AS'),('ALGERIA','DZ'),('ALBANIA','AL'),('AFGHANISTAN','AF')

insert into Grupos values
	('A', 'Concacaf','BN'),('A', 'UEFA','BB'),('A', 'Conmebol','BJ'),('A', 'Concacaf','BE'),
	('B', 'AFC','BY'),('B', 'Conmebol','AW'),('B', 'Concacaf','BD'),('B', 'CAF','BV'),
	('C', 'UEFA','AQ'),('C', 'OFC','BO'),('C', 'CAF','AM'),('C', 'UEFA','DZ'),
	('D', 'AFC','BM'),('D', 'CAF','AI'),('D', 'Concacaf','BH'),('D', 'Conmebol','AT'),
	('E', 'Conmebol','BR'),('E', 'UEFA','BZ'),('E', 'UEFA','AZ'),('E', 'OFC','BT'),
	('F', 'AFC','AD'),('F', 'CAF','AF'),('F', 'AFC','IO'),('F', 'OFC','AL'),
	('G', 'UEFA','AU'),('G', 'OFC','AS'),('G', 'AFC','AR'),('G', 'Concacaf','BW'),
	('H', 'OFC','BS'),('H', 'Concacaf','BA'),('H', 'Conmebol','AO'),('H', 'CAF','AG')