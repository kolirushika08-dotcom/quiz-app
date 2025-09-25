-- MySQL schema for Quiz Application
CREATE DATABASE IF NOT EXISTS quizdb;
USE quizdb;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS questions (
  id INT AUTO_INCREMENT PRIMARY KEY,
  text TEXT NOT NULL,
  score INT DEFAULT 1
);

CREATE TABLE IF NOT EXISTS options (
  id INT AUTO_INCREMENT PRIMARY KEY,
  question_id INT NOT NULL,
  text VARCHAR(500) NOT NULL,
  is_correct BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS results (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  total_score INT,
  taken_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Sample data
INSERT INTO questions (text, score) VALUES 
('What is the capital of France?', 1),
('Which language is used for backend development in this project?', 1);

INSERT INTO options (question_id, text, is_correct) VALUES
(1, 'Paris', TRUE),
(1, 'London', FALSE),
(1, 'Berlin', FALSE),
(1, 'Madrid', FALSE),
(2, 'Java', TRUE),
(2, 'Python', FALSE),
(2, 'JavaScript', FALSE),
(2, 'C#', FALSE);

INSERT INTO users (username, password, role) VALUES
('admin', 'adminpassword', 'ADMIN'),
('user1', 'userpassword', 'USER');
