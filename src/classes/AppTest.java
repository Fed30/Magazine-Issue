package classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private Magazine magazine;
    private MagazineIssue issue;
    private ContentSystemImp contentSystem;
    private Editor editor;
    private ProcessingCentreStaff staff;
    private Story story;
    private Photograph photo;
    private Advertisement add;
    private RetrieveApprovedStoriesCommand command;

    @Before
    public void setUp() {

        // Initialize a magazine and its issue
        magazine = new Magazine();
        magazine.setMagazineName("Tech Innovators Monthly");

        issue = new MagazineIssue();
        issue.setMagazineId(101);

        contentSystem = new ContentSystemImp();
        contentSystem.addMagazineIssue(issue);

        editor = new Editor(101, 101501, 123456789, "Alice Muffin", "123 Editor Street");
        staff = new ProcessingCentreStaff(201, 201601, 987654321, "Bob Staff", "456 Staff Lane", contentSystem);

        story = new Story("This is a valid story content.", 101, 180, "Fun of playground", 201, "Tech Trends");
        photo = new Photograph(2, "AI Research", 2, "/images/ai.jpg",101);
        add = new Advertisement(3, "Buy Now opportunity!", 401, "jpg", "John's Tech Shop",101);

        issue.setEditor(editor); // Editor assigned to the issue
        command = new RetrieveApprovedStoriesCommand(contentSystem);
    }

    // TEST USERS LOGIN
    @Test
    public void usersLogin() {
        // Test users login functionalities
        assertTrue(editor.login(editor.getEditorId()));
        assertTrue(staff.login(staff.getStaffId()));
    }
    
    // TEST STORY VALIDATION

    @Test
    public void testValidStory() {
        // Ensure the story title and content are set correctly
        assertEquals("Fun of playground", story.getStoryTitle());
        assertEquals("This is a valid story content.", story.getContent());
        story.setApproved(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryTitleTooShort() {
        // Title is too short (less than 10 characters)
        story = new Story("Valid content", 101, 1, "Short", 201, "Tech Trends");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryTitleTooLong() {
        // Title is too long (more than 20 characters)
        story = new Story("Valid content", 101, 1, "This is a really long title that exceeds the max length", 201, "Tech Trends");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryContentTooShort() {
        // Content is too short (less than 1 character)
        story = new Story("", 101, 1, "Valid Title", 201, "Tech Trends");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryContentTooLong() {
        // Content is too long (more than 400 characters)
        String longContent = "A".repeat(401); // 401 characters long
        story = new Story(longContent, 101, 1, "Valid Title", 201, "Tech Trends");
    }

    // TEST PHOTOGRAPH VALIDATION
    @Test
    public void testValidPhoto() {
        // Ensure valid photograph
        assertEquals("AI Research", photo.getContentTitle());
        photo.setApproved(true);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPhotoTitleTooShort() {
        // Content is too short (less than 1 character)
        photo = new Photograph(2, "1", 2, "/images/ai.jpg",101);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPhotoTitleTooLong() {
        // Content is too short (mores than 20 characters)
        photo = new Photograph(2, "The research of artificial intelligence in mobile ", 2, "/images/ai.jpg",101);
    }

    // TEST ADVERTISEMENT VALIDATION
    @Test
    public void testValidAdd() {
        // Ensure valid add
        assertEquals("Buy Now opportunity!", add.getContentTitle());
        assertEquals("jpg", add.getFormat());
        assertEquals("John's Tech Shop", add.getAuthor());
        add.setApproved(true);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddTitleTooLong() {
        // Content is too short (mores than 20 characters)
        add = new Advertisement(3, "Buy Now opportunity and do not loose it!", 401, "jpg", "John's Tech Shop",101);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddTitleTooShort() {
        // Content is too short (mores than 20 characters)
        add = new Advertisement(3, "Buy!", 401, "jpg", "John's Tech Shop",101);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddFormatWrong() {
        // Add format not jpg
        add = new Advertisement(3, "Buy Now opportunity!", 401, "png", "John's Tech Shop",101);
    }

    // TEST CONTENT VALIDATION
    @Test
    public void addingContentToIssue() {

        // Add valid content to the issue
        story.setApproved(true); // Approve the story
        issue.addStory(story); // Add the story to the issue
        assertEquals(1, issue.getStories().size());
        assertTrue(issue.getStories().contains(story));

        photo.setApproved(true); // Approve the photo
        issue.addPhoto(photo); // Add the photo to the issue
        assertEquals(1, issue.getPhotos().size());
        assertTrue(issue.getPhotos().contains(photo));

        add.setApproved(true); // Approve the add
        issue.addAdvert(add); // Add the story to the issue
        assertEquals(1, issue.getAdverts().size());
        assertTrue(issue.getAdverts().contains(add));

        // Test duplicate addition
        issue.addStory(story); // Add the story to the issue
        assertEquals(1, issue.getStories().size());

        // Test unapproved content
        Story unapprovedStory = new Story("Perfect content posted", 101, 160, "IEEE Science", 301, "Journal of IT");
        unapprovedStory.setApproved(false); // Not approved
        assertFalse(issue.addStory(unapprovedStory)); // Should not add unapproved story
    }
    
    // TESTING PROCESSING CENTER COMMAND PATTERN
    @Test
    public void testingProcessingCentreCommands() {

        story = new Story("Exploring the future.", 102, 200, "Future of EdTech", 202, "Tech Innovations");
        photo = new Photograph(6, "Machine Learning", 15, "jpg", 101);
        add = new Advertisement(7, "Summer Sale 50% Off!", 403, "jpg", "TechWorld Store", 101);
        contentSystem = new ContentSystemImp();

        // Add approved content to the issue
        story.setApproved(true);
        issue.addStory(story);

        photo.setApproved(true);
        issue.addPhoto(photo);

        add.setApproved(true);
        issue.addAdvert(add);

        contentSystem.addMagazineIssue(issue);

        System.out.println("Story approved: " + story.isApproved());
        System.out.println("Photo approved: " + photo.isApproved());
        System.out.println("Add approved: " + add.isApproved());

        // Simulate staff login
        staff.login(201601);

        // Test retrieve approved stories command
        Command retrieveStories = new RetrieveApprovedStoriesCommand(contentSystem);
        staff.setCommand(retrieveStories);

        // Use a try-catch block to check that no exception is thrown
        try {
            staff.executeCommand();
        } catch (Exception e) {
            fail("Expected no exception, but got: " + e.getClass().getName());
        }

        // Test retrieve approved photographs command
        Command retrievePhotographs = new RetrieveApprovedPhotographsCommand(contentSystem);
        staff.setCommand(retrievePhotographs);

        // Use a try-catch block again
        try {
            staff.executeCommand();
        } catch (Exception e) {
            fail("Expected no exception, but got: " + e.getClass().getName());
        }

        // Test retrieve approved advertisement command
        Command retrieveAdvertisements = new RetrieveApprovedAdvertisementsCommand(contentSystem);
        staff.setCommand(retrieveAdvertisements);

        // Use a try-catch block again
        try {
            staff.executeCommand();
        } catch (Exception e) {
            fail("Expected no exception, but got: " + e.getClass().getName());
        }
    }
    
    // TESTING EDITOR COMMAND PATTERN
    @Test
    public void testingEditorCommands() {
        // Initialize the objects with new attributes
        story = new Story("Innovating Education with AI", 101, 210, "AI in Schools", 203, "Education Today");
        photo = new Photograph(8, "Quantum Computing", 18, "png", 101);
        add = new Advertisement(9, "Holiday Discount!", 404, "jpg", "Gizmo Electronics", 101);
        contentSystem = new ContentSystemImp();
        issue.setEditor(editor); // Editor assigned to the issue

        // Add approved content to the issue
        story.setApproved(true);
        issue.addStory(story);
        System.out.println("Added approved story: " + story.getStoryTitle());

        photo.setApproved(true);
        issue.addPhoto(photo);
        System.out.println("Added approved photo: " + photo.getContentTitle());

        add.setApproved(true);
        issue.addAdvert(add);
        System.out.println("Added approved advert: " + add.getContentTitle());

        // Assign contributors to content
        story.addContributor(new Contributors(325, 325126, 17, "John Doe", "Liverpool"));
        story.addContributor(new Contributors(565, 565125, 78, "Patrick Manor", "Bristol"));

        photo.addContributor(new Contributors(325, 325126, 17, "John Doe", "Liverpool"));
        photo.addContributor(new Contributors(565, 565125, 78, "Patrick Manor", "Bristol"));

        add.addContributor(new Contributors(325, 325126, 17, "John Doe", "Liverpool"));
        add.addContributor(new Contributors(565, 565125, 78, "Patrick Manor", "Bristol"));

        // Simulate editor login and review
        editor.login(101501);
        System.out.println("Editor logged in with ID: " + editor.getEditorId());
        editor.reviewChanges(issue.getMagazineId());
        System.out.println("Editor reviewed changes for magazine issue ID: " + issue.getMagazineId());
        editor.assembleMagazineIssue(issue);
        System.out.println("Editor assembled magazine issue ID: " + issue.getMagazineId());

        issue.getContributors();
        //issue.setContributors(contributors);
        System.out.println("Contributors assigned: " + issue.getContributors().size() + " contributors.");

        // Unsaved issue should not be sent for printing
        PrepareMagazineForPublicationImp publication = new PrepareMagazineForPublicationImp();
        
        // Command to save the issue
        Command saveIssueCommand = new SaveIssueCommand(publication, issue);
        editor.setCommand(saveIssueCommand);
        editor.executeCommand();
        System.out.println("Executing save command for issue ID: " + issue.getMagazineId());

        assertTrue(issue.isSaved());  // Verify that the issue was saved
        System.out.println("Issue saved successfully: " + issue.isSaved());

        // Verify send to printing precondition
        Command sendToContributors = new SendMagazineToContributorsCommand(publication, issue, editor);
        
        // Test that no exception is thrown when sending to contributors
        try {
            editor.setCommand(sendToContributors);
            editor.executeCommand();  // Execute the command if necessary
            System.out.println("Magazine issue sent to contributors for issue ID: " + issue.getMagazineId());
        } catch (Exception e) {
            fail("Expected no exception, but got: " + e.getClass().getName());
        }

        // Command to notify the accounts department
        Command notifyAccountsCommand = new NotifyAccountDeptCommand(publication, issue, issue.getContributors());
        editor.setCommand(notifyAccountsCommand);
        editor.executeCommand();  // Execute the notification command
        System.out.println("Notified accounts department for issue ID: " + issue.getMagazineId());
    }    
}
