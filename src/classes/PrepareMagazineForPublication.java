package classes;

import java.util.List;

public interface PrepareMagazineForPublication {

    
    String sendForPrinting(MagazineIssue magazineIssue);

    boolean saveIssue(MagazineIssue magazineIssue);

    String sendMagazineToContributors(MagazineIssue magazineIssue, Editor editor);

    boolean notifyAccountDept(MagazineIssue magazineIssue, List<Contributors> contributors);

    List<Content> retrieveContentForIssue(ContentSystem contentSystem, MagazineIssue magazineIssue);
}
