-- Step 1: Create database
CREATE DATABASE IF NOT EXISTS quiz_app;
USE quiz_app;

-- Step 2: Create users table
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  role ENUM('USER','ADMIN') DEFAULT 'USER'
);

-- Step 3: Create questions table
CREATE TABLE IF NOT EXISTS questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question_text VARCHAR(255) NOT NULL,
  option_a VARCHAR(100),
  option_b VARCHAR(100),
  option_c VARCHAR(100),
  option_d VARCHAR(100),
  correct_option CHAR(1)
);

-- Step 4: Create results table
CREATE TABLE IF NOT EXISTS results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  score INT,
  taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Step 5: Insert sample users
INSERT INTO users (username, password, role) VALUES
('alice', 'password123', 'USER'),
('bob', 'password123', 'USER'),
('admin', 'adminpass', 'ADMIN');

-- Step 6: Insert sample questions
INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
('What is the capital of India?', 'Delhi', 'Mumbai', 'Kolkata', 'Chennai', 'A'),
('Which language is primarily used for Android development?', 'Python', 'Java', 'C++', 'JavaScript', 'B'),
('Who wrote "Hamlet"?', 'Shakespeare', 'Dickens', 'Hemingway', 'Tolstoy', 'A');

-- Step 7: Insert sample results
INSERT INTO results (user_id, score) VALUES
(1, 3),
(2, 2);