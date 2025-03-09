import classes.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        // Step 1: Create a Magazine and Magazine Issue
        System.out.println("******************* STEP 1 *********************************");
        Magazine magazine = new Magazine();
        magazine.setMagazineName("Tech Innovators Monthly");

        MagazineIssue issue = new MagazineIssue();
        issue.setMagazineId(101);
        magazine.setMagazineIssues(List.of(issue));
    

        System.out.println("Magazine created: " + magazine.getMagazineName());

        // Step 2: Add Content to the Issue
        System.out.println("******************* STEP 2 *********************************");
        Story story = new Story("Exploring the latest in AI.", 101, 1, "Fun Facts", 201,  "Tech Trends");
        story.setApproved(true);

        Photograph photograph = new Photograph(2, "AI Research", 2, "/images/ai.jpg",101);
        photograph.setApproved(true);


        Advertisement ad = new Advertisement(3, "Buy Now!", 401, "Full Page", "John's Tech Shop",101);
        ad.setApproved(true);
        
        story.setMagazineIssueId(issue.getMagazineId());
        photograph.setMagazineIssueId(issue.getMagazineId());
        ad.setMagazineIssueId(issue.getMagazineId());

        issue.addStory(story);
        issue.addPhoto(photograph);
        issue.addAdvert(ad);

        System.out.println("Content added to Magazine Issue ID: " + issue.getMagazineId());

        // Step 3: Test Processing Centre Staff
        System.out.println("******************* STEP 3 *********************************");
        ContentSystemImp contentSystem = new ContentSystemImp();
        contentSystem.addMagazineIssue(issue);
        ProcessingCentreStaff staff = new ProcessingCentreStaff(201,201601, 987654321, "Bob Staff", "456 Staff Lane", contentSystem);
        staff.login(staff.getStaffId());
        staff.prepareIssue();
        staff.loadContent(issue.getMagazineId());
        

        // Execute commands via the Processing Staff as the invoker
        Command retrieveStories = new RetrieveApprovedStoriesCommand(contentSystem);
        Command retrievePhotographs = new RetrieveApprovedPhotographsCommand(contentSystem);
        Command retrieveAdvertisements = new RetrieveApprovedAdvertisementsCommand(contentSystem);

        System.out.println("Executing commands via ProcessingCentreStaff...");
        
        staff.setCommand(retrieveStories);
        staff.executeCommand();

        staff.setCommand(retrievePhotographs);
        staff.executeCommand();

        staff.setCommand(retrieveAdvertisements);
        staff.executeCommand();

        // Step 4: Test Editor Functions
        System.out.println("******************* STEP 4 **********************************");
        Editor editor = new Editor(101,101501, 123456789, "Alice Muffin", "123 Editor Street");
        editor.login(editor.getEditorId());
        editor.reviewChanges(issue.getMagazineId());
        editor.assembleMagazineIssue(issue);
        issue.setEditor(editor); // assign to the magazine issue an editor


        // Step 5: Prepare Magazine for Publication
        System.out.println("******************* STEP 5 *********************************");
        PrepareMagazineForPublicationImp publication = new PrepareMagazineForPublicationImp();
        List<Contributors> contributors = List.of(new Contributors(325,325126,17,"John Doe","Liverpool"), new Contributors(565,565125,78,"Patrick Manor","Bristol"));
        

        // Create commands
        Command saveIssueCommand = new SaveIssueCommand(publication, issue);
        Command sendMagazineCommand = new SendMagazineToContributorsCommand(publication, issue, editor);
        Command notifyAccountsCommand = new NotifyAccountDeptCommand(publication, issue, contributors);

        // Execute commands using the Editor as the Invoker
        editor.setCommand(saveIssueCommand);
        editor.executeCommand();

        editor.setCommand(sendMagazineCommand);
        editor.executeCommand();

        editor.setCommand(notifyAccountsCommand);
        editor.executeCommand();


        // Step 6: Retrieve Content via ContentSystem
        System.out.println("******************* STEP 6 *********************************");
        List<Content> approvedContent = publication.retrieveContentForIssue(contentSystem, issue);
        System.out.println("Retrieved " + approvedContent.size() + " approved content items for the issue.");
    }
    
}
