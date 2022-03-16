create table usuario
(
    id               int(11) not null auto_increment,
    nombre           varchar(100) not null,
    clave            varchar(45)  not null,
    fecha_creacion   datetime null,
    fecha_nacimiento datetime null,
    primary key (id)
);

create table reserva
(
    id            int(11) not null auto_increment,
    nombre        varchar(100) not null,
    categoria     varchar(100) not null,
    fecha_reserva datetime null,
    id_usuario int ,
    precio float (11) not null ,
    obsequio bit not null ,
    primary key (id),
    foreign key (id_usuario) REFERENCES usuario (id)
);