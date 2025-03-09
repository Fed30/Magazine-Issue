package classes;

import java.util.List;

public class Magazine {
    private String MagazineName;
    private List<MagazineIssue> magazineIssues; // Association with Magazine Issue

    public List<MagazineIssue> getMagazineIssues() {
        return magazineIssues;
    }

    public void setMagazineIssues(List<MagazineIssue> magazineIssues) {
        this.magazineIssues = magazineIssues;
    }

    public String getMagazineName() {
        return MagazineName;
    }

    public void setMagazineName(String magazineName) {
        this.MagazineName = magazineName;
    }
}
