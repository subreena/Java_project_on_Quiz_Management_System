import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
public class quizManagementSystem {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("System:> Enter your username");
        System.out.print("User:> ");
        String username = scanner.nextLine();

        System.out.println("System:> Enter password");
        System.out.print("User:> ");
        String password = scanner.nextLine();

        JSONObject user = authenticateUser(username, password);
        if (user != null) {
            String role = (String) user.get("role");
            if (role.equals("admin")) {
                adminFlow(username);
            } else if (role.equals("student")) {
                studentFlow(username);
            }
        } else {
            System.out.println("System:> Invalid credentials!");
        }
    }

    //login
    private static JSONObject authenticateUser(String username, String password) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray users = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/users.json"));

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                if (user.get("username").equals(username) && user.get("password").equals(password)) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //create quiz

    private static void adminFlow(String username) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("System:> Welcome " + username + "! \nPlease create new questions in the question bank.");

        while (true) {
            System.out.println("\nSystem:> Input your question");
            System.out.print("Admin:> ");
            String question = scanner.nextLine();

            System.out.println("System:> Input option 1:");
            System.out.print("Admin:> ");
            String option1 = scanner.nextLine();

            System.out.println("System:> Input option 2:");
            System.out.print("Admin:> ");
            String option2 = scanner.nextLine();

            System.out.println("System:> Input option 3:");
            System.out.print("Admin:> ");
            String option3 = scanner.nextLine();

            System.out.println("System:> Input option 4:");
            System.out.print("Admin:> ");
            String option4 = scanner.nextLine();

            System.out.println("System:> What is the answer key?");
            System.out.print("Admin:> ");
            int answerKey = Integer.parseInt(scanner.nextLine());

            saveQuestion(question, option1, option2, option3, option4, answerKey);
            System.out.println("System:> Saved successfully!");

            System.out.println("System:> Do you want to add more questions? (press 's' to start, 'q' to quit)");
            System.out.print("Admin:> ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }
    }

    private static void saveQuestion(String question, String opt1, String opt2, String opt3, String opt4, int answerKey) {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray questions;

            try {
                questions = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/quiz.json"));
            } catch (Exception e) {
                questions = new JSONArray();
            }

            JSONObject questionObj = new JSONObject();
            questionObj.put("question", question);
            questionObj.put("option 1", opt1);
            questionObj.put("option 2", opt2);
            questionObj.put("option 3", opt3);
            questionObj.put("option 4", opt4);
            questionObj.put("answerkey", answerKey);

            questions.add(questionObj);

            FileWriter writer = new FileWriter("./src/main/resources/quiz.json");
            writer.write(questions.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void studentFlow(String username) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("System:> Welcome " + username + " to the quiz! \nWe will throw you 10 questions. Each MCQ mark is 1 and no negative marking. Are you ready? Press 's' to start.");
            System.out.print("Student:> ");
            String start = scanner.nextLine();

            if (start.equalsIgnoreCase("s")) {
                int marks = conductQuiz();
                displayMarks(marks);

                System.out.println("\nSystem:> Would you like to start again? Press 's' for start or 'q' for quit");
                System.out.print("Student:> ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("q")) {
                    break;
                }
            } else {
                break;
            }
        }
    }

    private static int conductQuiz() {
        Scanner scanner = new Scanner(System.in);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray questions = (JSONArray) jsonParser.parse(new FileReader("./src/main/resources/quiz.json"));

            if (questions.isEmpty()) {
                System.out.println("System:> No questions available in the quiz bank!");
                return 0;
            }

            Random random = new Random();
            int marks = 0;

            for (int i = 1; i <= 10; i++) {
                int randomIndex = random.nextInt(questions.size());
                JSONObject question = (JSONObject) questions.get(randomIndex);

                System.out.println("\nSystem:> [Question " + i + "] " + question.get("question"));
                System.out.println("1. " + question.get("option 1"));
                System.out.println("2. " + question.get("option 2"));
                System.out.println("3. " + question.get("option 3"));
                System.out.println("4. " + question.get("option 4"));
                System.out.print("Student:> ");

                try {
                    int answer = Integer.parseInt(scanner.nextLine());
                    long correctAnswer = (long) question.get("answerkey");

                    if (answer == correctAnswer) {
                        marks++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("System:> Invalid input! Skipping question.");
                }
            }

            return marks;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void displayMarks(int marks) {
        System.out.println("\n===============================================================");
        if (marks >= 8 && marks <= 10) {
            System.out.println("Excellent! You have got " + marks + " out of 10");
        } else if (marks >= 5 && marks <= 7) {
            System.out.println("Good. You have got " + marks + " out of 10");
        } else if (marks >= 3 && marks <= 4) {
            System.out.println("Very poor! You have got " + marks + " out of 10");
        } else {
            System.out.println("Very sorry you are failed. You have got " + marks + " out of 10");
        }
        System.out.println("===============================================================");
    }
}