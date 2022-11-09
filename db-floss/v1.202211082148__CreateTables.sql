create table pattern
(
    id                 int UNSIGNED NOT NULL AUTO_INCREMENT,
    name               nvarchar(255),
    stitch_count       int,
    cloth_type         nvarchar(255),
    size               int,
    finished_size      nvarchar(255),
    threads_per_stitch int,
    total_colors       int,
    CONSTRAINT pattern_pk PRIMARY KEY (id)
);
create table pattern_thread
(
    id                int UNSIGNED NOT NULL AUTO_INCREMENT,
    name              nvarchar(255),
    brand             nvarchar(255),
    color_code        nvarchar(255),
    cm_required       nvarchar(255),
    stitches_per_inch int,
    aida_count        int,
    CONSTRAINT thread_pk PRIMARY KEY (id)
);
create table pattern_inventory
(
    id     int UNSIGNED NOT NULL AUTO_INCREMENT,
    number nvarchar(255),
    name   nvarchar(255),
    color  nvarchar(255),
    owned  int,
    CONSTRAINT inventory_pk PRIMARY KEY (id)
);
