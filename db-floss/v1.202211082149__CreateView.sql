create or replace view pattern_vw as
select *
from pattern;
grant select, insert, update, delete on web.pattern_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.pattern_vw to 'web_appl'@'%';

create or replace view pattern_thread_vw as
select *
from pattern_thread;
grant select, insert, update, delete on web.pattern_thread_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.pattern_thread_vw to 'web_appl'@'%';

create or replace view pattern_inventory_vw as
select *
from pattern_inventory;
grant select, insert, update, delete on web.pattern_inventory_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.pattern_inventory_vw to 'web_appl'@'%';
