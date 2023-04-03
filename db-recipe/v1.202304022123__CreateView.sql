create or replace view recipe_vw as
select *
from recipe;
grant select, insert, update, delete on web.recipe_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.recipe_vw to 'web_appl'@'%';

create or replace view ingredient_vw as
select *
from ingredient;
grant select, insert, update, delete on web.ingredient_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.ingredient_vw to 'web_appl'@'%';

create or replace view direction_vw as
select *
from direction;
grant select, insert, update, delete on web.direction_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.direction_vw to 'web_appl'@'%';

create or replace view tag_vw as
select *
from tag;
grant select, insert, update, delete on web.tag_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.tag_vw to 'web_appl'@'%';
