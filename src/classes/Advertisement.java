package classes;

import java.util.ArrayList;
import java.util.List;

public class Advertisement extends Content {
    
    private int advertId;
    private String format;
    private String author;
    private boolean approved;
    private int magazineIssueId;                    // Aggregation with Magazine Issue
    private final List<Contributors> contributors;

    
    // One to many- demo for composition-advertisement is composed of TextContent
    private List<TextContent> text;
    private List<GraphicContent> image;

    public Advertisement(int contentId, String title, int magazineIssueId, List<TextContent> text,
            List<GraphicContent> image) {
        super(contentId, title);
        this.text = text;
        this.image = image;
        this.magazineIssueId = magazineIssueId;
        this.contributors = new ArrayList<>();

    }
    public void addTextContent(int textId, String content, String author, Advertisement advertisement) {
        TextContent t = new TextContent(textId, content, author, advertisement);
        text.add(t);
    }
    
    public void addGraphicContent(int graphicId, String graphicPath, String author, Advertisement advertisement){
        GraphicContent g = new GraphicContent(graphicId, graphicPath, author, advertisement);
        image.add(g);
    }


    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Advertisement(int contentId, String title, int advertId, String format, String author, int magazineIssueId) {
        super(contentId, title);
        this.advertId = advertId;
        setFormat(format); // Calls the setter with validation
        setAuthor(author); // Calls the setter with validation
        this.contributors = new ArrayList<>();
        this.magazineIssueId = magazineIssueId;
    }
    // Getter and Setter for Magazine Issue Id as per Aggregation principle
    public int getMagazineIssueId() {
        return magazineIssueId;
    }

    public void setMagazineIssueId(int magazineIssueId) {
        this.magazineIssueId = magazineIssueId;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<Contributors> getContributors() {
        return contributors;
    }

    public void addContributor(Contributors contributor) {
        this.contributors.add(contributor);
    }

    public int getAdvertId() {
        return advertId;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        if ("jpg".equalsIgnoreCase(format)) {
            this.format = format;
        } else {
            throw new IllegalArgumentException("Format must be 'jpg'.");
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

    @Override
    public boolean isApproved() {
        if (this.approved) {
            return approved;
        } else {
            return false;
        }

    }
    
}
