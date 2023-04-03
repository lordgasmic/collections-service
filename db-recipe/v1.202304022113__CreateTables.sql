create table recipe
(
    recipe_id   int unsigned not null auto_increment,
    name        nvarchar(255),
    description nvarchar(255),
    constraint recipe_pk primary key (recipe_id)
);

create table ingredient
(
    ingredient_id int unsigned  not null auto_increment,
    recipe_id     int unsigned  not null,
    ordinal       int unsigned  not null,
    quantity      nvarchar(255) not null,
    uom           nvarchar(255),
    ingredient    nvarchar(255),
    preparation   nvarchar(255),
    constraint ingredient_pk primary key (ingredient_id)
);

create table direction
(
    direction_id int unsigned  not null auto_increment,
    recipe_id    int unsigned  not null,
    ordinal      int unsigned  not null,
    direction    nvarchar(255) not null,
    constraint direction_pk primary key (direction_id)
);

create table tag
(
    tag_id    int unsigned not null auto_increment,
    recipe_id int unsigned not null,
    ordinal   int unsigned not null,
    tag       nvarchar(255),
    constraint tag_pk primary key (tag_id)
);
