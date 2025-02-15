import java.io.Serializable;

public class QuestionEntry implements Serializable {
    private static final String[] NUMBER_WORDS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
    private static int nextId = 1;  // Incrementing ID
    private final String id;  // Text-based ID
    private String content;

    public QuestionEntry(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Question cannot be empty.");
        }
        this.id = getNumberWord(nextId++);
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Updated question cannot be empty.");
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
        return "num" + number;  // For numbers beyond the predefined list
    }

    @Override
    public String toString() {
        return "Question ID: " + id + "\nContent: " + content;
    }
}
