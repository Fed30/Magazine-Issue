package classes;

import java.util.ArrayList;
import java.util.List;
// Component
public class ContentSystemImp implements ContentSystem {

    private List<Content> contentList;
    private ProcessingCentreStaff staff; // Association with Processing Staff
    private Editor editor;  // Association with Editor
    private final List<MagazineIssue> magazineIssues;

    public ContentSystemImp() {
        this.contentList = new ArrayList<>();
        this.magazineIssues = new ArrayList<>(); // Initialize the magazineIssues list
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public ProcessingCentreStaff getStaff() {
        return staff;
    }

    public void setStaff(ProcessingCentreStaff staff) {
        this.staff = staff;
    }


    @Override
    public Content getContentById(int id) {
        for (Content content : contentList) {
            if (content.contentId == id) {
                return content;
            }
        }
        return null;
    }

    @Override
    public List<Story> retrieveApprovedStories() {
        List<Story> approvedStories = new ArrayList<>();
        for (Content content : contentList) {
            if (content instanceof Story && ((Story) content).isApproved()) {
                approvedStories.add((Story) content);
            }
        }
        return approvedStories;
    }

    @Override
    public List<Photograph> retrieveApprovedPhotographs() {
        List<Photograph> approvedPhotographs = new ArrayList<>();
        for (Content content : contentList) {
            if (content instanceof Photograph && ((Photograph) content).isApproved()) {
                approvedPhotographs.add((Photograph) content);
            }
        }
        return approvedPhotographs;
    }

    @Override
    public List<Advertisement> retrieveApprovedAdvertisements() {
        List<Advertisement> approvedAdvertisements = new ArrayList<>();
        for (Content content : contentList) {
            if (content instanceof Advertisement && ((Advertisement) content).isApproved()) {
                approvedAdvertisements.add((Advertisement) content);
            }
        }
        return approvedAdvertisements;
    }

    @Override
    public List<Content> retrieveApprovedContentByMagazineIssue(MagazineIssue magazineIssue) {
        List<Content> approvedContent = new ArrayList<>();
        for (Advertisement ad : magazineIssue.getAdverts()) {
            if (ad.isApproved()) {
                approvedContent.add(ad);
            }
        }
        for (Story story : magazineIssue.getStories()) {
            if (story.isApproved()) {
                approvedContent.add(story);
            }
        }
        for (Photograph photo : magazineIssue.getPhotos()) {
            if (photo.isApproved()) {
                approvedContent.add(photo);
            }
        }
        return approvedContent;
    }

    @Override
    public MagazineIssue getMagazineIssueById(int magazineId) {
        for (MagazineIssue issue : magazineIssues) {
            if (issue.getMagazineId() == magazineId) {
                return issue;
            }
        }
        return null;
    }

    public void addMagazineIssue(MagazineIssue issue) {
        
        this.magazineIssues.add(issue);
        
        // Add content related to the issue
        for (Story story : issue.getStories()) {
            if (story.isApproved()) {
                contentList.add(story);
            }
        }
        for (Photograph photo : issue.getPhotos()) {
            if (photo.isApproved()) {
                contentList.add(photo);
            }
        }
        for (Advertisement ad : issue.getAdverts()) {
            if (ad.isApproved()) {
                contentList.add(ad);
            }
        }
    }
}
