CREATE TABLE IF NOT EXISTS user
(
    user_id        INT NOT NULL,
    username       VARCHAR,
    address        VARCHAR,
    favorite_order INT,

    PRIMARY KEY (user_id)
);



MERGE INTO user KEY (user_id)
    VALUES (1, 'Dima', 'Atrium', 1);
