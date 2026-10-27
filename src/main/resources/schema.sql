CREATE TABLE users (
    id IDENTITY,
    userName VARCHAR NOT NULL /*nameは使えない*/
);
CREATE TABLE matches (
    id IDENTITY,
    user1 INT NOT NULL,
    user2 INT NOT NULL,
    user1hand VARCHAR NOT NULL,
    user2hand VARCHAR NOT NULL
);
