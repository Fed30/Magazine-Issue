package classes;

public class Contributors extends User {

    private int contributorId;
    private boolean isNotified;
    private MagazineIssue magazineIssue; // Association with MagazineIssue



    public Contributors(int userId,int contributorId, int mobile, String name, String address) {
        this.userId = userId;
        this.contributorId = contributorId;
        this.mobile = mobile;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean login(int contributorId) {
        // Convert userId and editorId to strings
        String userIdStr = String.valueOf(this.userId);
        String contributorIdStr = String.valueOf(this.contributorId);

        // Check if the first three digits of editorId match the userId
        if (contributorIdStr.length() >= 3 && userIdStr.equals(contributorIdStr.substring(0, 3))) {
            System.out.println("Login successful for Contributor User " + this.name);
            return true;
        } else {
            System.out.println("Login failed. Invalid User ID");
            return false;
        }
    }
    
    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean isNotified) {
        this.isNotified = isNotified;
    }

    public int getContributorId() {
        return contributorId;
    }

    public void setContributorId(int contributorId) {
        this.contributorId = contributorId;
    }

    public MagazineIssue getMagazineIssue() {
        return magazineIssue;
    }

    public void setMagazineIssue(MagazineIssue magazineIssue) {
        this.magazineIssue = magazineIssue;
    }
    
}
