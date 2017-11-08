CREATE TABLE pessoa(
  id  serial,
  nome character varying(80) NOT NULL,
  cpf character varying(14) NOT NULL,
  PRIMARY KEY (id)
);