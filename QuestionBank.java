import java.io.*;
import java.util.HashMap;

public class QuestionBank {
    private final HashMap<String, String> questions;
    private static final String FILE_NAME = "questions.txt";

    public QuestionBank() {
        this.questions = loadQuestions();
    }

    public String addQuestion(String content) {
        String questionId = "Q" + (questions.size() + 1);
        questions.put(questionId, content);
        saveQuestions();
        return questionId;
    }

    public boolean updateQuestion(String id, String newContent) {
        if (questions.containsKey(id)) {
            questions.put(id, newContent);
            saveQuestions();
            return true;
        }
        return false;
    }

    public boolean removeQuestion(String id) {
        if (questions.containsKey(id)) {
            questions.remove(id);
            saveQuestions();
            return true;
        }
        return false;
    }

    public String findQuestionById(String id) {
        return questions.get(id);
    }

    public HashMap<String, String> getAllQuestions() {
        return new HashMap<>(questions);
    }

    private void saveQuestions() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (var entry : questions.entrySet()) {
                out.println(entry.getKey() + "::" + entry.getValue());
            }
            System.out.println("Questions saved!");
        } catch (IOException e) {
            System.err.println("Error saving questions: " + e.getMessage());
        }
    }

    private HashMap<String, String> loadQuestions() {
        HashMap<String, String> loadedQuestions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::", 2);
                if (parts.length == 2) {
                    loadedQuestions.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous questions found.");
        }
        return loadedQuestions;
    }
}
