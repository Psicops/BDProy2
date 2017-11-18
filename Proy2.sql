create table CONFEDERACION(
    nombre varchar2(55) not null,
    codigo varchar2(10) check (codigo in ('Concacaf', 'UEFA', 'Conmebol', 'CAF', 'AFC','OFC'))primary key not null);
    
create table EQUIPO(
    codigoConfederacion varchar2(10) references CONFEDERACION(codigo) not null,
    nombrePais varchar2(40) not null,
    grupo varchar2(1) not null,
    codigoPais varchar2(3) primary key not null);

create table PERSONAS(
    numeroPasaporte varchar2(20) primary key not null,
    fechaNacimiento date not null,
    nombre varchar2(15) not null,
    apellido1 varchar2(15) not null,
    apellido2 varchar2(15) not null);
    
create table JUGADORES(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    numeroCamisa integer check(numeroCamisa between 1 and 99) not null,
    posicion varchar2(15) check (posicion in ('Portero', 'Defensa', 'Mediocampista', 'Delantero')) not null,
    equipo varchar2(3) references EQUIPO(codigoPais));
    
create table ASISTENTE(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    inicioPuesto date not null,
    nacionalidad varchar2(15) not null,
    tipoAsistente varchar2(25) check ( tipoAsistente in ('Entrenador', 'Técnico', 'Preparador Físico',
        'Médico', 'Psicólogo', 'Nutricionista', 'Administrativo', 'Delegado de Equipo')) not null,
    equipo varchar2(3) references EQUIPO(codigoPais));
    
create table FEDERATIVO(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    inicioPuesto date not null,
    nacionalidad varchar2(15) not null,
    puesto varchar2(15) check (puesto in ('Director', 'Vicepresidente', 'Secretario', 'Tesorero', 'Fiscal', 'Vocal')) not null,
    equipo varchar2(3) references EQUIPO(codigoPais));
    
create table ARBITRO(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    inicioPuesto date not null,
    nacionalidad varchar2(15) not null);

create table SEDE(
    nombreSede varchar2(40) not null,
    nombreEstadio varchar2(40) primary key not null,
    capacidad integer check (capacidad between 25000 and 150000) not null);

create table PARTIDOS(
    numeroPartido integer check(numeroPartido between 1 and 64) primary key not null,
    etapa varchar2(15) check(etapa in ('Fase de Grupos', 'Octavos de Final', 'Cuartos de Final',
        'Semifinales', 'Tercer Lugar', 'Final')) not null,
    fechaHora date not null,
    cantidadAficionados integer not null,
    reposicion1T varchar2(10) not null,
    reposicion2T varchar2(10) not null,
    equipo1 varchar2(3) references EQUIPO(codigoPais) not null,
    equipo2 varchar2(3) references EQUIPO(codigoPais) not null,
    nombreEstadio varchar2(10) references SEDE(nombreEstadio) not null,
    extras varchar2(2) check (extras in('SI','NO')) not null);

create table TITULARES(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    capitan varchar(2)check (capitan in ('SI', 'NO')) not null,
    equipo varchar2(3) references EQUIPO(codigoPais) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null);    
    
create table SUPLENTES(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    equipo varchar2(3) references EQUIPO(codigoPais) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null);

create table EQUIPOTECNICO(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    equipo varchar2(3) references EQUIPO(codigoPais) not null,
    puesto varchar2(20) check (puesto in ('Entrenador', 'Asistente Técnico', 'Médico', 'Delegado')) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null);

create table ACCION(
    numeroPasaporte varchar2(20) references PERSONAS(numeroPasaporte) not null,
    tipoAccion varchar2(20) check (tipoAccion in ('Gol', 'Tarjeta Amarilla', 'Tarjeta Roja')) not null,
    tiempo varchar2(5) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null,
    equipo varchar2(3) references EQUIPO(codigoPais) not null)
    
alter table ACCION add primary key (numeroPasaporte, tiempo, numeroPartido);

create table CUERPOARBITRAL(
    numeroPasaporte varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    puesto varchar2(15) check(puesto in ('Principal', 'Guardalíneas', 'Cuarto Árbitro', 'Quinto Árbitro')) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null);

create table CAMBIOS(
    entra varchar2(20) references PERSONAS(numeroPasaporte) not null,
    sale varchar2(20) primary key references PERSONAS(numeroPasaporte) not null,
    tiempo varchar2(5) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null);

create table PENALES(
    numeroPenal varchar2(2) not null,
    anotado varchar2(2) check (anotado in ('SI', 'NO')) not null,
    jugador varchar2(20) references PERSONAS(numeroPasaporte) not null,
    numeroPartido integer references PARTIDOS(numeroPartido) not null)
    
alter table PENALES add primary key (jugador, numeroPartido);

--insert into CONFEDERACION select * from proy2.conf;

--insert into EQUIPO select confederacion, pais, grupo, equipo from proy2.group_ join proy2.team on proy2.group_.equipo = proy2.team.codigo;

 
insert into personas values
    ('1300', '02-01-1978', 'Henry', 'Bejarano', 'Matarrita');
    ('1301', '22-07-1982', 'Hugo', 'Cruz', 'Alvarado'),
    ('1302', '17-04-1964', 'William', 'Mattus', 'Vega'),
    ('1303', '06-03-1986', 'Ricardo', 'Montego', 'Garita'),
    ('1304', '09-05-1970', 'Walter', 'Quesada', 'Cordero');
    
insert into arbitro values
    ('1300', '01-01-2011', 'Costarricense')
    ('1301', '01-01-2001', 'Costarricense')
    ('1302', '06-08-2008', 'Costarricense')
    ('1303', '01-10-2011', 'Costarricense')
    ('1304', '01-01-2001', 'Costarricense')
    














