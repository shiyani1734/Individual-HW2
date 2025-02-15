import java.io.Serializable;

public class AnswerEntry implements Serializable {
    private static final String[] NUMBER_WORDS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    private static int nextId = 1;
    private final String id;
    private final String questionId;
    private String content;

    public AnswerEntry(String questionId, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Answer cannot be empty.");
        }
        this.id = getNumberWord(nextId++);
        this.questionId = questionId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Updated answer cannot be empty.");
        }
        this.content = newContent;
    }

    public static void setNextId(int id) {
        nextId = id;
    }

    private String getNumberWord(int number) {
        if (number >= 0 && number < NUMBER_WORDS.length) {
            return NUMBER_WORDS[number];
        }
        return "num" + number;  // Fallback for larger numbers
    }

    @Override
    public String toString() {
        return "Answer ID: " + id + "\nQuestion ID: " + questionId + "\nContent: " + content;
    }
}
