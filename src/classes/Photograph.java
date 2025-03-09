package classes;

import java.util.ArrayList;
import java.util.List;

public class Photograph extends Content {
    

    private int photographId;
    private String photographPath;
    private boolean approved;
    private int magazineIssueId;                    // Aggregation with Magazine Issue
    private final List<Contributors> contributors;



    public int getMagazineIssueId() {
        return magazineIssueId;
    }

    public void setMagazineIssueId(int magazineIssueId) {
        this.magazineIssueId = magazineIssueId;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Photograph(int contentId, String title, int photographId, String photographPath,int magazineIssueId) {
        super(contentId, title);
        this.photographId = photographId;
        this.photographPath = photographPath;
        this.contributors = new ArrayList<>();
        this.magazineIssueId = magazineIssueId;
    }

    public List<Contributors> getContributors() {
        return contributors;
    }

    public void addContributor(Contributors contributor) {
        contributors.add(contributor);
    }

    public int getPhotographId() {
        return photographId;
    }

    public void setPhotographId(int photographId) {
        this.photographId = photographId;
    }

    public String getPhotographPath() {
        return photographPath;
    }

    public void setPhotographPath(String photographPath) {
        this.photographPath = photographPath;
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
