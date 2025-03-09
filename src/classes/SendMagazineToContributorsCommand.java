package classes;

public class SendMagazineToContributorsCommand implements Command {

    private final PrepareMagazineForPublicationImp publication;
    private final MagazineIssue magazineIssue;
    private final Editor editor;

    public SendMagazineToContributorsCommand(PrepareMagazineForPublicationImp publication, MagazineIssue magazineIssue, Editor editor) {
        this.publication = publication;
        this.magazineIssue = magazineIssue;
        this.editor = editor;
    }

    @Override
    public void execute() {
        String result = publication.sendMagazineToContributors(magazineIssue, editor);
        System.out.println(result);
    }
    
}
