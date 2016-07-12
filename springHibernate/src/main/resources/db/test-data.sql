INSERT INTO CONTACT (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES
  ('Chris', 'Schaefer', '1981-05-03'),
  ('Scott', 'Tiger', '1990-11-02'),
  ('John', 'Smith', '1964-02-28');
INSERT INTO CONTACT_TEL_DETAIL (CONTACT_ID, TEL_TYPE, TEL_NUMBER) VALUES
  (1, 'Mobile', '1234567890'),
  (1, 'Home', '1234567890'),
  (2, 'Home', '1234567890');
INSERT INTO HOBBY (HOBBY_ID) VALUES
  ('Swimming'),
  ('Jogging'),
  ('Programming'),
  ('Movies'),
  ('Reading');
INSERT INTO CONTACT_HOBBY_DETAIL (CONTACT_ID, HOBBY_ID) VALUES
  (1, 'Swimming'),
  (1, 'Movies'),
  (2, 'Swimming');
