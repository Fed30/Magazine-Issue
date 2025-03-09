package classes;

import java.util.List;

public class ProcessingCentreStaff extends User {

    private int staffId;
    private ContentSystemImp contentSystem;
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }

    public ProcessingCentreStaff(int userId,int staffId, int mobile, String name, String address, ContentSystemImp contentSystem) {
        this.userId = userId;
        this.staffId = staffId; 
        this.mobile = mobile;
        this.name = name;
        this.address = address;
        this.contentSystem = contentSystem; // Associate ContentSystemImp
    }

    @Override
    public boolean login(int staffId) {
         // Convert userId and editorId to strings
        String userIdStr = String.valueOf(this.userId);
        String staffIdStr = String.valueOf(this.staffId);

        // Check if the first three digits of editorId match the userId
        if (staffIdStr.length() >= 3 && userIdStr.equals(staffIdStr.substring(0, 3))) {
            System.out.println("Login successful for Processing Centre User " + this.name);
            return true;
        } else {
            System.out.println("Login failed. User ID invalid.");
            return false;
        }
    }

    public ContentSystemImp getContentSystem() {
        return contentSystem;
    }

    public void setContentSystem(ContentSystemImp contentSystem) {
        this.contentSystem = contentSystem;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void prepareIssue() {
        System.out.println("Issue prepared by staff with ID: " + this.staffId);
    }

    public void loadContent(int magazineIssueId) {
        System.out.println("Loading content for magazine issue ID: " + magazineIssueId);
    }


    public void retrieveContent(int magazineIssueId) {
        // Retrieve the magazine issue from the content system
        MagazineIssue issue = contentSystem.getMagazineIssueById(magazineIssueId);
        if (issue == null) {
            System.out.println("No magazine issue found with ID: " + magazineIssueId);
            return;
        }
    
        // Retrieve and display approved stories for this magazine issue
        List<Story> stories = contentSystem.retrieveApprovedStories();
        System.out.println("Approved Stories:");
        for (Story story : stories) {
            if (story.getMagazineIssueId() == magazineIssueId) {
                System.out.println("- " + story.getStoryId() + ": " + story.getStoryTitle() + " - " + story.getContent());
            }
        }
    
        // Similarly for photographs and advertisements...
        List<Photograph> photographs = contentSystem.retrieveApprovedPhotographs();
        System.out.println("Approved Photographs:");
        for (Photograph photo : photographs) {
            if (photo.getMagazineIssueId() == magazineIssueId) {
                System.out.println("- " + photo.getPhotographId() + ": " + photo.getPhotographPath());
            }
        }
    
        List<Advertisement> advertisements = contentSystem.retrieveApprovedAdvertisements();
        System.out.println("Approved Advertisements:");
        for (Advertisement ad : advertisements) {
            if (ad.getMagazineIssueId() == magazineIssueId) {
                System.out.println("- " + ad.getAdvertId()+ ": " + " by" + ad.getAuthor());
            }
        }
    
        // Retrieve approved content by magazine issue
        List<Content> approvedContent = contentSystem.retrieveApprovedContentByMagazineIssue(issue);
        System.out.println("All approved content for issue ID " + magazineIssueId + ":");
        for (Content content : approvedContent) {
            System.out.println("- Content ID: " + content.contentId + "-" +content.contentTitle);
        }
    }
    
}
