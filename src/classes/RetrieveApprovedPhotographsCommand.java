package classes;

import java.util.List;

public class RetrieveApprovedPhotographsCommand implements Command {

    private final ContentSystemImp contentSystem;

    public RetrieveApprovedPhotographsCommand(ContentSystemImp contentSystem) {
        this.contentSystem = contentSystem;
    }

    @Override
    public void execute() {
        List<Photograph> photographs = contentSystem.retrieveApprovedPhotographs();
        System.out.println("Approved Photographs:");
        for (Photograph photo : photographs) {
            System.out.println("- Photograph ID: " + photo.getPhotographId() + " Path: " + photo.getPhotographPath());
        }
    }
    
}
