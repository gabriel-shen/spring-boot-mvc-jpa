CREATE TABLE IF NOT EXISTS airline (
      id INT NOT NULL AUTO_INCREMENT,
      name VARCHAR(50) NOT NULL,
      short_name VARCHAR(10) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE INDEX name_UNIQUE (name)
    );