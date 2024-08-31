ALTER TABLE sale DROP CONSTRAINT sale_product_id_sale_date_unique;

ALTER TABLE sale RENAME COLUMN total_sum TO cost;

ALTER TABLE sale ADD CONSTRAINT sale_product_id_sale_date_cost_unique UNIQUE (product_id, sale_date, cost);