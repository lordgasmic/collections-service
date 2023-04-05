create or replace view recipe_vw as
select *
from recipe;
grant select, insert, update, delete on web.recipe_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.recipe_vw to 'web_appl'@'%';
