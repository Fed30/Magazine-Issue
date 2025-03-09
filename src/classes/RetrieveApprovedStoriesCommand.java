package classes;

import java.util.List;

public class RetrieveApprovedStoriesCommand implements Command {

    private final ContentSystemImp contentSystem;

    public RetrieveApprovedStoriesCommand(ContentSystemImp contentSystem) {
        this.contentSystem = contentSystem;
    }

    @Override
    public void execute() {
        System.out.println("Executing RetrieveApprovedStoriesCommand...");
        List<Story> stories = contentSystem.retrieveApprovedStories();
        System.out.println("Approved Stories:");
        for (Story story : stories) {
            System.out.println("- Story ID: " + story.getStoryId() + " Title: " + story.getStoryTitle());
        }
    }
    
}
