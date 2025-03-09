package classes;

public class SaveIssueCommand implements Command {

    private final PrepareMagazineForPublicationImp publication;
    private final MagazineIssue magazineIssue;

    public SaveIssueCommand(PrepareMagazineForPublicationImp publication, MagazineIssue magazineIssue) {
        this.publication = publication;
        this.magazineIssue = magazineIssue;
    }

    @Override
    public void execute() {
        boolean result = publication.saveIssue(magazineIssue);
        System.out.println(result ? "Magazine issue with ID " + magazineIssue.getMagazineId() + " saved successfully.": "Failed to save issue.");
    }
    
}
