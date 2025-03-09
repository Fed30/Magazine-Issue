package classes;

import java.util.List;

public class Editor extends User {
    
    private int editorId;
    private List<MagazineIssue> magazineIssues;
    private List<ContentSystemImp> content;
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

    public Editor(int userId, int editorId, int mobile, String name, String address) {
        this.userId = userId;
        this.editorId = editorId;
        this.mobile = mobile;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean login(int editorId) {
        
         // Convert userId and editorId to strings
        String userIdStr = String.valueOf(this.userId);
        String editorIdStr = String.valueOf(this.editorId);

        // Check if the first three digits of editorId match the userId
        if (editorIdStr.length() >= 3 && userIdStr.equals(editorIdStr.substring(0, 3))) {
            System.out.println("Login successful for Editor User " + this.name);
            return true;
        } else {
            System.out.println("Login failed. Invalid User ID");
            return false;
        }
    }

    public List<ContentSystemImp> getContent() {
        return content;
    }

    public void setContent(List<ContentSystemImp> content) {
        this.content = content;
    }

    public List<MagazineIssue> getMagazineIssues() {
        return magazineIssues;
    }

    public void setMagazineIssues(List<MagazineIssue> magazineIssues) {
        this.magazineIssues = magazineIssues;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public void reviewChanges(int magazineIssueId) {
        System.out.println("Reviewing changes for magazine issue ID: " + magazineIssueId);
    }

    public Command getCommand() {
        return command;
    }

    public void assembleMagazineIssue(MagazineIssue issue) {
        if (issue == null) {
            System.out.println("Cannot assemble magazine issue: Issue is null.");
            return;
        }
        if (!issue.getPhotos().isEmpty() && !issue.getAdverts().isEmpty() && !issue.getStories().isEmpty()) {
            System.out.println("Assembling magazine issue ID: " + issue.getMagazineId() );
        }
    }
}
