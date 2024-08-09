CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);