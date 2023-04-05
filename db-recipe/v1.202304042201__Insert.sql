insert into recipe(name)
values ('Dummy Recipe');

insert into ingredient(recipe_id, ordinal, quantity, uom, ingredient)
values (1, 1, "1", "cup", "chicken breast");
insert into ingredient(recipe_id, ordinal, quantity, uom, ingredient)
values (1, 2, "1", "tbsp", "salt");

insert into tag(recipe_id, ordinal, tag)
values (1, 1, "chicken");
insert into tag(recipe_id, ordinal, tag)
values (1, 2, "dinner");
insert into tag(recipe_id, ordinal, tag)
values (1, 3, "derp");

insert into direction(recipe_id, ordinal, direction)
values (1, 1, 'cook chicken');
insert into direction(recipe_id, ordinal, direction)
values (1, 2, 'season chicken with salt');
