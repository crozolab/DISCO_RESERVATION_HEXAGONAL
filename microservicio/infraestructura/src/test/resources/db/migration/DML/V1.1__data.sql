insert into usuario(id, nombre,clave,fecha_creacion, fecha_nacimiento) values(1,'test','1234',now(), '1992-11-06');
insert into usuario(id, nombre,clave,fecha_creacion, fecha_nacimiento) values(2,'camilo','1234',now(), '1992-11-06');
insert into reserva(id, nombre,categoria,fecha_reserva, id_usuario, precio) values(1,'test','vip','2022-11-14', 1, 200000);
insert into reserva(id, nombre,categoria,fecha_reserva, id_usuario, precio) values(2,'camilo','vip','2022-11-14', 2, 300000);
insert into reserva(id, nombre,categoria,fecha_reserva, id_usuario, precio) values(3,'camilo','general','2022-11-14', 2, 100000);