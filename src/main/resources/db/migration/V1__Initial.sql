CREATE TABLE DISEASE (
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(256),
  system      VARCHAR(256),
  description VARCHAR(256)

);


CREATE TABLE BREED (
  id      INTEGER PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(256),
  species VARCHAR(256)
);


CREATE TABLE DISEASES_AFFECTING_BREEDS (
  disease_id INTEGER,
  breed_id   INTEGER,
  FOREIGN KEY (disease_id) REFERENCES DISEASE (id),
  FOREIGN KEY (breed_id) REFERENCES BREED (id)
);



