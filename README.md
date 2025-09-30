#  Quiz Management System using Java

A Java-based role-based quiz management system that allows administrators to create quiz questions and students to take quizzes with automatic scoring.

## Video Demo
[Demo Link](https://drive.google.com/file/d/1a2uEbl-twBGuGfLTncksbUkF1_KcQpLQ/view?usp=sharing) . The demo shows: 
– Admin login and adding questions 
– Student login and completing a quiz

##  Features
### Admin Features
-  Add multiple-choice questions (MCQs) to question bank
-  Store questions with 4 options and answer key
-  Continuous question addition capability
-  Persistent storage in JSON format

### Student Features

- Take quiz with 10 randomly selected questions 
- Immediate feedback on performance 
- Multiple quiz attempts allowed

## File Descriptions
### `users.json`
Stores user credentials and roles:
```json
[
  { "username": "admin", "password": "1234", "role": "admin" },
  { "username": "salman", "password": "1234", "role": "student" },
  { "username": "subreena", "password": "1234", "role": "student" }
]
```

### `quiz.json`
Stores all quiz questions with options and answer keys:
```json
[
  {
    "question": "Which is not part of system testing?",
    "option 1": "Regression Testing",
    "option 2": "Sanity Testing",
    "option 3": "Load Testing",
    "option 4": "Unit Testing",
    "answerkey": 4
  }
]
```

## Getting Started

### Prerequisites
- Java 11 or higher
- Gradle (or use Gradle wrapper)


### Admin Login
1. Enter username: `admin`
2. Enter password: `1234`
3. Add questions by following the prompts
4. Press `s` to add more questions or `q` to quit

**Example Admin Flow:**
```
System:> Enter your username
User:> admin
System:> Enter password
User:> 1234
System:> Welcome admin! Please create new questions in the question bank.

System:> Input your question
Admin:> Which is not part of system testing?
System:> Input option 1:
Admin:> Regression Testing
System:> Input option 2:
Admin:> Sanity Testing
System:> Input option 3:
Admin:> Load Testing
System:> Input option 4:
Admin:> Unit Testing
System:> What is the answer key?
Admin:> 4
System:> Saved successfully!
System:> Do you want to add more questions? (press 's' to start, 'q' to quit)
```

### Student Login
1. Enter username: `subreena`
2. Enter password: `1234`
3. Press `s` to start the quiz
4. Answer all 10 questions
5. View your score and performance feedback

**Example Quiz Flow:**
```
System:> Enter your username
User:> subreena
System:> Enter password
User:> 1234
System:> Welcome subreena to the quiz! We will throw you 10 questions. 
Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.
Student:> s

System:> [Question 1] Which is not part of system testing?
1. Regression Testing
2. Sanity Testing
3. Load Testing
4. Unit Testing
Student:> 4
...
```

## Scoring System

| Score Range | Performance Level | Message |
|------------|-------------------|---------|
| 8-10 | Excellent | "Excellent! You have got [marks] out of 10" |
| 5-7 | Good | "Good. You have got [marks] out of 10" |
| 3-4 | Very Poor | "Very poor! You have got [marks] out of 10" |
| 0-2 | Failed | "Very sorry you are failed. You have got [marks] out of 10" |


### Installation

1. Clone this repository:

2. Ensure `users.json` and `quiz.json` files exist in the correct directory

3. Build the project

4. Run the application in Intellij Idea

## Quiz Flow

The system contains **20 SQA (Software Quality Assurance)** related questions covering topics such as:
- Testing types (Unit, Integration, System, Acceptance)
- Testing techniques (Black box, White box)
- Testing principles
- Test documentation
- Defect management
- Performance testing
- Security testing
