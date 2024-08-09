CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    amount DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    user_id INT NOT NULL,
--     category_id INT NOT NULL,
--     account_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type VARCHAR(10) NOT NULL CHECK (type IN ('EXPENSE', 'INCOME')),
    FOREIGN KEY (user_id) REFERENCES users(id)
--     FOREIGN KEY (category_id) REFERENCES categories(id),
--     FOREIGN KEY (account_id) REFERENCES accounts(id)
);