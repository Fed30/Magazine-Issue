package classes;

import java.util.List;

public class RetrieveApprovedAdvertisementsCommand implements Command {

    private final ContentSystemImp contentSystem;

    public RetrieveApprovedAdvertisementsCommand(ContentSystemImp contentSystem) {
        this.contentSystem = contentSystem;
    }

    @Override
    public void execute() {
        List<Advertisement> advertisements = contentSystem.retrieveApprovedAdvertisements();
        System.out.println("Approved Advertisements:");
        for (Advertisement ad : advertisements) {
            System.out.println("- Advertisement ID: " + ad.getAdvertId() + " Author: " + ad.getAuthor());
        }
    }
    
}
