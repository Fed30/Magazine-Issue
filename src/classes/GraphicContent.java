package classes;

import java.util.regex.Pattern;

public class GraphicContent {

    private int graphicId;
    private String graphicPath;
    private String author;
    private final Advertisement advertisement;      // The advertisement is composed of Graphic Content

    // Regular expression to validate a URL (basic check for a link)
    private static final String URL_REGEX = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public GraphicContent(int graphicId, String graphicPath, String author, Advertisement advertisement) {
        this.graphicId = graphicId;
        setGraphicPath(graphicPath); // Validates the graphicPath
        setAuthor(author); // Validates the author
        this.advertisement = advertisement;
    }
    
    public int getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(int graphicId) {
        this.graphicId = graphicId;
    }

    public String getGraphicPath() {
        return graphicPath;
    }

    public void setGraphicPath(String graphicPath) {
        if (graphicPath != null && URL_PATTERN.matcher(graphicPath).matches()) {
            this.graphicPath = graphicPath;
        } else {
            throw new IllegalArgumentException("Graphic path must be a valid URL.");
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
