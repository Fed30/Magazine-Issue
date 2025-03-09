package classes;

import java.util.List;

public interface ContentSystem {

    // Retrieves content by its unique ID - dependency on Content class
    Content getContentById(int id);

    // Retrieves approved stories
    List<Story> retrieveApprovedStories();

    // Retrieves approved photographs
    List<Photograph> retrieveApprovedPhotographs();

    // Retrieves approved advertisements
    List<Advertisement> retrieveApprovedAdvertisements();

    // Retrieves all approved content for a specific magazine issue
    List<Content> retrieveApprovedContentByMagazineIssue(MagazineIssue magazineIssue);

    // Retrieves a MagazineIssue by its ID
    MagazineIssue getMagazineIssueById(int magazineId);// Dependency with MagazineIssue
}
