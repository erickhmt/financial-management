-- password 1234 w/ bcrypt
INSERT INTO users (name, username, email, password) VALUES
('John Doe', 'john', 'john.doe@example.com', '$2a$12$ftb1t7YkUX9.mZK.83MaZe9X721SnSH6N94CXls..4Anu4n2d9O4C');

INSERT INTO categories (name, user_id, type) VALUES
('Groceries', 1, 'expense'),
('Salary', 1, 'income');

INSERT INTO accounts (name, balance, user_id) VALUES
('Checking Account', 1000.00, 1),
('Savings Account', 5000.00, 1);

-- INSERT INTO transactions (amount, description, date, user_id, category_id, account_id, type) VALUES
-- (50.00, 'Grocery shopping', '2024-07-01 10:00:00', 1, 1, 1, 'expense'),
-- (2000.00, 'Monthly salary', '2024-07-01 09:00:00', 1, 1, 1, 'income');

INSERT INTO transactions (amount, description, date, user_id, type) VALUES
(50.00, 'Grocery shopping', '2024-07-01', 1, 'EXPENSE'),
(2000.00, 'Monthly salary', '2024-07-01', 1, 'INCOME');