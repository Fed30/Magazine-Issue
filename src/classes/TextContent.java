package classes;

public class TextContent {
    
    private int textId;
    private String content;
    private String author;


    private final Advertisement advertisement;


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TextContent(int textId, String content, String author, Advertisement advertisement) {
        this.textId = textId;
        setContent(content);  // Calls the setter with validation
        setAuthor(author);    // Calls the setter with validation
        this.advertisement = advertisement;
    }
    

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null && content.length() > 0 && content.length() < 400) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content must be greater than 0 and less than 400 characters.");
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null && author.length() >= 10 && author.length() <= 20) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author name must be between 10 and 20 characters.");
        }
    }
}
