import java.util.List;
import java.util.Scanner;

public class QuestionAnswerSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionBank questionBank = new QuestionBank();
        AnswerBank answerBank = new AnswerBank();

        System.out.println("Loading previous data...");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a new question");
            System.out.println("2. View all questions");
            System.out.println("3. Update a question");
            System.out.println("4. Delete a question");
            System.out.println("5. Add a response to a question");
            System.out.println("6. View responses for a question");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter question content: ");
                    String questionContent = scanner.nextLine().trim();
                    if (!questionContent.isEmpty()) {
                        String questionId = questionBank.addQuestion(questionContent);
                        System.out.println("Question added! ID: " + questionId);
                    } else {
                        System.out.println("Error: Question cannot be empty.");
                    }
                    break;

                case 2:
                    questionBank.getAllQuestions().forEach((id, content) ->
                        System.out.println("Question ID: " + id + "\nContent: " + content)
                    );
                    break;

                case 3:
                    System.out.print("Enter Question ID to update: ");
                    String updateId = scanner.nextLine().trim();
                    if (questionBank.findQuestionById(updateId) != null) {
                        System.out.print("Enter new content: ");
                        String updatedContent = scanner.nextLine().trim();
                        questionBank.updateQuestion(updateId, updatedContent);
                        System.out.println("Question updated successfully!");
                    } else {
                        System.out.println("Error: Question ID not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Question ID to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    if (questionBank.removeQuestion(deleteId)) {
                        System.out.println("Question deleted successfully!");
                    } else {
                        System.out.println("Error: Question ID not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Question ID to add an answer: ");
                    String questionId = scanner.nextLine().trim();

                    if (!questionBank.getAllQuestions().containsKey(questionId)) {
                        System.out.println("Error: Question ID not found.");
                        break;
                    }

                    System.out.print("Enter answer content: ");
                    String answerContent = scanner.nextLine().trim();

                    if (answerContent.isEmpty()) {
                        System.out.println("Error: Answer cannot be empty.");
                    } else {
                        answerBank.addAnswer(questionId, answerContent);
                        System.out.println("Answer added!");
                    }
                    break;

                case 6:
                    System.out.print("Enter Question ID to view responses: ");
                    String searchId = scanner.nextLine().trim();

                    if (!questionBank.getAllQuestions().containsKey(searchId)) {
                        System.out.println("Error: Question ID not found.");
                        break;
                    }

                    List<String> answers = answerBank.getAnswersForQuestion(searchId);
                    if (answers.isEmpty()) {
                        System.out.println("No responses found for this question.");
                    } else {
                        answers.forEach(answer -> System.out.println("Answer: " + answer));
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
