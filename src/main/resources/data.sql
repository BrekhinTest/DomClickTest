DROP TABLE IF EXISTS account;

CREATE TABLE account (
  id BIGINT primary key,
  balance BIGINT
);

INSERT INTO account (id, balance) VALUES
  ('1', '10000'),
  ('2', '50000'),
  ('3', '20000');