package classes;


public abstract class User {

    protected int userId;
    protected int mobile;
    protected String name;
    protected String address;

    // Abstract method for login, to be implemented by subclasses
    public abstract boolean login(int userID);
}
