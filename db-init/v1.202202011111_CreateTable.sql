create table id_generator(
    item_descriptor nvarchar(255) not null primary key,
    current_id nvarchar(255) not null,
    prefix nvarchar(255),
    suffix nvarchar(255)
);
