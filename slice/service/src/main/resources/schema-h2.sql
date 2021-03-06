CREATE TABLE customer (
  username   VARCHAR(255) NOT NULL,
  email      VARCHAR(255),
  first_name VARCHAR(255),
  last_name  VARCHAR(255),
  PRIMARY KEY (username)
);

CREATE TABLE customer_address (
  customer_id VARCHAR(255) NOT NULL,
  city        VARCHAR(255),
  country     INTEGER,
  number      VARCHAR(255),
  street      VARCHAR(255),
  zip_code    VARCHAR(255),
  hash        INTEGER      NOT NULL,
  value       VARCHAR(255) NOT NULL,
  PRIMARY KEY (customer_id, hash, value)
);

CREATE TABLE customer_allergen (
  customer_id VARCHAR(255) NOT NULL,
  allergen    VARCHAR(255)
);

CREATE TABLE customer_phone (
  customer_id VARCHAR(255) NOT NULL,
  area_code   VARCHAR(255),
  number      VARCHAR(255)
);

CREATE TABLE product (
  type     VARCHAR(31)  NOT NULL,
  name     VARCHAR(255) NOT NULL,
  category VARCHAR(255),
  PRIMARY KEY (name)
);

CREATE TABLE consumable_product (
  field1 VARCHAR(255),
  name   VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
);

CREATE TABLE non_consumable_product (
  field2 VARCHAR(255),
  name   VARCHAR(255) NOT NULL,
  PRIMARY KEY (name)
);

CREATE TABLE orders (
  id          BIGINT GENERATED BY DEFAULT AS IDENTITY,
  status      VARCHAR(255),
  customer_id VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE order_quantity (
  product_name VARCHAR(255) NOT NULL,
  amount       INTEGER      NOT NULL,
  order_id     BIGINT,
  PRIMARY KEY (product_name)
);

ALTER TABLE customer_address
  ADD CONSTRAINT fk_customer_address FOREIGN KEY (customer_id) REFERENCES customer;

ALTER TABLE customer_allergen
  ADD CONSTRAINT fk_customer_allergen FOREIGN KEY (customer_id) REFERENCES customer;

ALTER TABLE customer_phone
  ADD CONSTRAINT fk_customer_phone FOREIGN KEY (customer_id) REFERENCES customer;

ALTER TABLE consumable_product
  ADD CONSTRAINT fk_consumable_product FOREIGN KEY (name) REFERENCES product;

ALTER TABLE non_consumable_product
  ADD CONSTRAINT fk_non_consumable_product FOREIGN KEY (name) REFERENCES product;

ALTER TABLE order_quantity
  ADD CONSTRAINT fk_order_quantity_product FOREIGN KEY (product_name) REFERENCES product;

ALTER TABLE order_quantity
  ADD CONSTRAINT fk_product_order FOREIGN KEY (order_id) REFERENCES orders;

ALTER TABLE orders
  ADD CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customer;