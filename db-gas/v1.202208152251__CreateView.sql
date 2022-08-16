create or replace view gas_vw as
select *
from gas;
grant select, insert, update, delete on web.gas_vw to 'web_appl'@'localhost';
grant select, insert, update, delete on web.gas_vw to 'web_appl'@'%';
