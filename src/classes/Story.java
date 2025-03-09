package classes;

import java.util.ArrayList;
import java.util.List;

public class Story extends Content {
    

    private int storyId;
    private String storyTitle;
    private String content;
    private boolean approved;
    private int magazineIssueId;
    private final List<Contributors> contributors;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Story(String content, int magazineIssueId, int storyId, String storyTitle, int contentId, String title) {
        super(contentId, title);
        setContent(content);       // Validates content length
        setStoryTitle(storyTitle); // Validates story title length
        this.storyId = storyId;
        this.contributors = new ArrayList<>();
        
    }
    
    public List<Contributors> getContributors() {
        return contributors;
    }

    public void addContributor(Contributors contributor) {
        this.contributors.add(contributor);
    }


    public int getMagazineIssueId() {
        return magazineIssueId;
    }

    public void setMagazineIssueId(int magazineIssueId) {
        this.magazineIssueId = magazineIssueId;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String title) {
        if (title.length() >= 10 && title.length() <= 20) {
            this.storyTitle = title;
        } else {
            throw new IllegalArgumentException("Story title must be between 10 and 20 characters.");
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content != null && content.length() > 0 && content.length() <= 400) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content must be greater than 0 and less than or equal to 400 characters.");
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
