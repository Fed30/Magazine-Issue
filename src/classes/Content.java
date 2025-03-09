package classes;

public abstract class Content {
    
    protected int contentId;
    protected String contentTitle;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Content(int contentId, String contentTitle) {
        this.contentId = contentId;
        setContentTitle(contentTitle);
    }

    // Getter for content title
    public String getContentTitle() {
        return contentTitle;
    }

    // Setter for content title (if you want to modify the title later)
    public void setContentTitle(String contentTitle) {
        if (contentTitle.length() >= 10 && contentTitle.length() <= 20) {
            this.contentTitle = contentTitle;
        } else {
            throw new IllegalArgumentException("Content title must be between 10 and 20 characters.");
        }
    }

    // Getter for contentId
    public int getContentId() {
        return contentId;
    }

    // Setter for contentId
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    // Abstract method for approved, to be implemented by subclasses
    public abstract boolean isApproved();
}
