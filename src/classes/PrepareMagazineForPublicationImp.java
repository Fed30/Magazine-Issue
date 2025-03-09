package classes;

import java.util.ArrayList;
import java.util.List;

public class PrepareMagazineForPublicationImp implements PrepareMagazineForPublication{

    @Override
    public String sendForPrinting(MagazineIssue magazineIssue) {
        // Check if the magazine issue has been saved before sending it for printing
        if (magazineIssue == null || !magazineIssue.isSaved()) { // check if it has been saved -precondition
            return "Magazine issue with ID " + magazineIssue.getMagazineId()
                    + " cannot be sent for printing because it is not saved.";
        }
        magazineIssue.setIsSentForPrinting(true);
        return "Magazine issue with ID " + magazineIssue.getMagazineId() + " sent for printing.";
    }

    @Override
    public boolean saveIssue(MagazineIssue magazineIssue) {
        //  RULE 2 PRECONDITION
        if (magazineIssue == null || 
        magazineIssue.getStories().stream().noneMatch(Story::isApproved) &&
        magazineIssue.getAdverts().stream().noneMatch(Advertisement::isApproved) &&
        magazineIssue.getPhotos().stream().noneMatch(Photograph::isApproved)) {
        System.out.println("Cannot save: No approved content in the magazine issue.");
        return false;
    }
    magazineIssue.setMagazineIssue(magazineIssue); // RULE 2 POST CONDITION
    magazineIssue.setSaved(true);
    return true;
    }

    @Override
    public String sendMagazineToContributors(MagazineIssue magazineIssue, Editor editor) {
        // RULE 3 AND 9 IMPLEMENTATION
        if (magazineIssue == null || editor == null|| !magazineIssue.isIsSentForPrinting()) {
            return "Failed: Magazine issue or editor is missing.";
        }
        if (!magazineIssue.getMagazineIssue().equals(magazineIssue)) {
            return "Failed: Editor does not match the magazine issue.";
        }
        if (magazineIssue.getContributors() == null || magazineIssue.getContributors().isEmpty()) {
            return "Failed: No contributors found in the magazine issue.";
        }
        
        // Precondition passed
        return notifyContributors(magazineIssue, editor);
    }

    @Override
    public boolean notifyAccountDept(MagazineIssue magazineIssue, List<Contributors> contributors) {
        // RULE 4 and 6 IMPLEMENTATION
        // Simulate notifying the accounts department
        if (magazineIssue == null || contributors == null || contributors.isEmpty()) {
            System.out.println("Failed: Magazine issue or contributors missing.");
            return false;
        }

        // Check which contributors are eligible for payment based on approved content
        List<Contributors> eligibleContributors = new ArrayList<>();
        for (Contributors contributor : contributors) {
            // Check if the contributor has worked on approved content
            if (contributorHasApprovedContent(contributor, magazineIssue)) {
                eligibleContributors.add(contributor);
                contributor.setNotified(true); // Mark contributor as notified
                System.out.println("Contributor " + contributor.getContributorId() + " is eligible for payment.");
            } else {
                System.out.println("Contributor " + contributor.getContributorId() + " is NOT eligible for payment.");
            }
        }

        // Notify the accounts department if there are eligible contributors
        if (!eligibleContributors.isEmpty()) {
            System.out.println("Notified accounts department for magazine issue ID " +
                    magazineIssue.getMagazineId() + " with " + eligibleContributors.size()
                    + " contributors eligible for payment.");
            return true;
        } else {
            System.out.println("No contributors are eligible for payment.");
            return false;
        }
    }
    private String notifyContributors(MagazineIssue magazineIssue, Editor editor) {
        for (Contributors contributor : magazineIssue.getContributors()) {
            contributor.setNotified(true);
        }
        return "Magazine issue with ID " + magazineIssue.getMagazineId() +
                " sent to contributors by Editor " + editor.getEditorId() + ". All contributors have been notified.";
    }

    private boolean contributorHasApprovedContent(Contributors contributor, MagazineIssue magazineIssue) {
        // Check if the contributor has worked on any approved content
        for (Story story : magazineIssue.getStories()) {
            if (story.getContributors().contains(contributor) && story.isApproved()) {
                System.out.println("Contributor " + contributor.getContributorId() + " has worked on approved story: " + story.getStoryTitle());
                return true;
            }
        }
        for (Photograph photo : magazineIssue.getPhotos()) {
            if (photo.getContributors().contains(contributor) && photo.isApproved()) {
                System.out.println("Contributor " + contributor.getContributorId() + " has worked on approved photo: " + photo.getPhotographId());
                return true;
            }
        }
        for (Advertisement advert : magazineIssue.getAdverts()) {
            if (advert.getContributors().contains(contributor) && advert.isApproved()) {
                System.out.println("Contributor " + contributor.getContributorId() + " has worked on approved advert: " + advert.getAdvertId());
                return true;
            }
        }
        return false;
    }
    

    @Override
    public List<Content> retrieveContentForIssue(ContentSystem contentSystem, MagazineIssue magazineIssue) {
        List<Content> approvedContent = new ArrayList<>();

        // Retrieve approved content for this issue
        List<Content> allContent = contentSystem.retrieveApprovedContentByMagazineIssue(magazineIssue);

        for (Content content : allContent) {
            if (content instanceof Story && ((Story) content).isApproved()) {
                approvedContent.add(content);
            } else if (content instanceof Photograph && ((Photograph) content).isApproved()) {
                approvedContent.add(content);
            } else if (content instanceof Advertisement && ((Advertisement) content).isApproved()) {
                approvedContent.add(content);
            }
        }
        return approvedContent;
    }
}
