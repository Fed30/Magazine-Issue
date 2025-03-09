package classes;

public class SendForPrintingCommand implements Command {

    private final PrepareMagazineForPublicationImp publication;
    private final MagazineIssue magazineIssue;


    public SendForPrintingCommand(PrepareMagazineForPublicationImp publication, MagazineIssue magazineIssue) {
        this.publication = publication;
        this.magazineIssue = magazineIssue;
    }

    @Override
    public void execute() {
        String result = publication.sendForPrinting(magazineIssue);
        System.out.println(result);
    }
    
}
