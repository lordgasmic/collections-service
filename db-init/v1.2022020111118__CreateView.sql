create or replace view id_generator_vw as select * from id_generator;
grant select, insert, update, delete on web.id_generator_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.id_generator_vw to 'web_appl'@'%';
