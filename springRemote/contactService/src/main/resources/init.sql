CREATE TABLE contact (
  id   INT         NOT NULL AUTO_INCREMENT,
  version INT NOT NULL DEFAULT 0,
  name VARCHAR(30) NOT NULL,
  birthDate DATETIME NOT NULL,
  UNIQUE (name),
  PRIMARY KEY (id)
);

INSERT INTO contact (name, birthDate) VALUES
  ('Sasha', '1985-11-27'),
  ('Masha', '1986-10-26'),
  ('Pasha', '1987-9-25');

