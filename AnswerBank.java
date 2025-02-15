import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnswerBank {
    private final HashMap<String, List<String>> answers;
    private static final String FILE_NAME = "answers.txt";

    public AnswerBank() {
        this.answers = loadAnswers();
    }

    public void addAnswer(String questionId, String content) {
        answers.computeIfAbsent(questionId, k -> new ArrayList<>()).add(content);
        saveAnswers();
    }

    public boolean removeAnswer(String questionId, String answerContent) {
        if (answers.containsKey(questionId)) {
            boolean removed = answers.get(questionId).remove(answerContent);
            if (answers.get(questionId).isEmpty()) {
                answers.remove(questionId);
            }
            saveAnswers();
            return removed;
        }
        return false;
    }

    public List<String> getAnswersForQuestion(String questionId) {
        return answers.getOrDefault(questionId, new ArrayList<>());
    }

    private void saveAnswers() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (var entry : answers.entrySet()) {
                for (String ans : entry.getValue()) {
                    out.println(entry.getKey() + "::" + ans);
                }
            }
            System.out.println("Answers saved!");
        } catch (IOException e) {
            System.err.println("Error saving answers: " + e.getMessage());
        }
    }

    private HashMap<String, List<String>> loadAnswers() {
        HashMap<String, List<String>> loadedAnswers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::", 2);
                if (parts.length == 2) {
                    loadedAnswers.computeIfAbsent(parts[0], k -> new ArrayList<>()).add(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous answers found.");
        }
        return loadedAnswers;
    }
}
