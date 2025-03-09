package classes;


import java.util.ArrayList;
import java.util.List;

public class MagazineIssue {

    private int magazineId;
    private boolean isSaved;
    private Magazine magazine;              // Association with Magazine
    private List<Advertisement> adverts;    // Aggregation with Advertisement
    private List<Story> stories;            // Aggregation with Story
    private List<Photograph> photos;        // Aggregation with Photograph
    private MagazineIssue magazineIssue;
    private List<Contributors> contributors;    // Association with Contributors
    private Editor editor; // Association with Editor
    private boolean isSentForPrinting;


    // Constructor
    public MagazineIssue() {
        this.stories = new ArrayList<>();
        this.photos = new ArrayList<>();
        this.adverts = new ArrayList<>();
        this.contributors = new ArrayList<>();

    }
    
    public boolean isSaved() {
        return isSaved;
    }
    
    public void setSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public MagazineIssue getMagazineIssue() {
        return magazineIssue;
    }

    public void setMagazineIssue(MagazineIssue magazineIssue) {
        this.magazineIssue = magazineIssue;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }
    // OCL RULE 1 and RULE 5 and RULE 10 IMPLEMENTATION
    public boolean  addPhoto(Photograph photograph) {
        if (photograph.isApproved() && editor != null) {
            // Ensure the photo is unique
            if (!photos.stream().anyMatch(p -> p.getContentId() == photograph.getContentId())) {
                photos.add(photograph);
                System.out.println("Photograph approved and it has been added to the magazine Issue.");
                return true;
            } else {
                System.out.println("Photograph already exists and cannot be added.");
                return false;
            }
        } else {// Duplicate detected
            System.out.println("Photograph not approved or editor is missing. Cannot add.");
            return false;
        }
    }

    public boolean addStory(Story story) {
        if (story.isApproved() && editor != null) {
            // Ensure the story is unique
            if (!stories.stream().anyMatch(s -> s.getContentId() == story.getContentId())) {
                stories.add(story);
                System.out.println("Story approved and it has been added to the magazine Issue.");
                return true;
            } else {
                System.out.println("Story already exists and cannot be added.");
                return false;
            }
        } else {// Duplicate detected
            System.out.println("Story not approved or editor is missing. Cannot add.");
            return false;
        }
    }

    public boolean addAdvert(Advertisement advertisement) {
        if (advertisement.isApproved() && editor != null) {
            // Ensure the advert is unique
            if (!adverts.stream().anyMatch(a -> a.getContentId() == advertisement.getContentId())) {
                adverts.add(advertisement);
                System.out.println("Advert approved and it has been added to the magazine Issue.");
                return true;
            } else {
                System.out.println("Advert already exists and cannot be added.");
                return false;
            }
        } else {// Duplicate detected
            System.out.println("Advert not approved or editor is missing. Cannot add.");
            return false;
        }
    }
    
    public List<Photograph> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photograph> photos) {
        this.photos = photos;
    }

    public List<Advertisement> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advertisement> adverts) {
        this.adverts = adverts;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }

    // Add contributors
    public void addContributor(Contributors contributor) {
        if (contributor != null) {
            this.contributors.add(contributor);
        }
    }

    // Getters and Setters
    public List<Contributors> getContributors() {
        List<Contributors> allContributors = new ArrayList<>();
        for (Story story : getStories()) {
            allContributors.addAll(story.getContributors());
        }
        for (Photograph photo : getPhotos()) {
            allContributors.addAll(photo.getContributors());
        }
        for (Advertisement advert : getAdverts()) {
            allContributors.addAll(advert.getContributors());
        }
        return allContributors;
    }

    public void setContributors(List<Contributors> contributors) {
        this.contributors = contributors;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public boolean isIsSentForPrinting() {
        return isSentForPrinting;
    }

    public void setIsSentForPrinting(boolean isSentForPrinting) {
        this.isSentForPrinting = isSentForPrinting;
    }
}
