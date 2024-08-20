CREATE TABLE product(
    id UUID NOT NULL,
    name TEXT NOT NULL,

    CONSTRAINT product_pk PRIMARY KEY (id),
    CONSTRAINT product_unique UNIQUE (name)
);

CREATE TABLE sale(
    id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL DEFAULT 0,
    total_sum REAL NOT NULL DEFAULT 0,
    sale_date TIMESTAMP NOT NULL,

    CONSTRAINT sale_pk PRIMARY KEY (id),
    CONSTRAINT sale_product_fk FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT sale_product_id_sale_date_unique UNIQUE (product_id, sale_date)
);

CREATE TABLE IF NOT EXISTS product_groups (
    id UUID NOT NULL,
    creation_date DATE NOT NULL,
    name VARCHAR NOT NULL,

    CONSTRAINT product_groups_pk PRIMARY KEY (id),
    CONSTRAINT product_groups_unique UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS product_and_groups (
    product_id UUID NOT NULL,
    group_id UUID NOT NULL,
    custom_value INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT product_and_groups_product_id_fk FOREIGN KEY (product_id) REFERENCES product (id),
    CONSTRAINT product_and_groups_group_id_fk FOREIGN KEY (group_id) REFERENCES product_groups (id),
    CONSTRAINT product_and_groups_pk PRIMARY KEY (product_id, group_id)
);