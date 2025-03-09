package classes;

import java.util.List;

public class NotifyAccountDeptCommand implements Command {

    private final PrepareMagazineForPublicationImp publication;
    private final MagazineIssue magazineIssue;
    private final List<Contributors> contributors;

    public NotifyAccountDeptCommand(PrepareMagazineForPublicationImp publication, MagazineIssue magazineIssue, List<Contributors> contributors) {
        this.publication = publication;
        this.magazineIssue = magazineIssue;
        this.contributors = contributors;
    }

    @Override
    public void execute() {
        boolean result = publication.notifyAccountDept(magazineIssue, contributors);
        System.out.println(result ? "Accounts department notified successfully." : "Failed to notify accounts department.");
    }
}
