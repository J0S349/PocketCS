package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/8/16.
 */

public class User {

    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String facebookID;
    private String fullname;

    User(){

    }
    User(String facebookID, String fullname){
        this.facebookID = facebookID;
        this.fullname = fullname;
    }

    public int getID() {
        return ID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getFacebookID() {
        return facebookID;
    }

    public void setID(int ID) {
        if(ID > 0) {
            this.ID = ID;
        }
    }
    public void setFirstName(String firstName) {
        if(!firstName.equals(null)) {
            this.firstName = firstName;
        }
    }
    public void setLastName(String lastName) {
        if(!lastName.equals(null)) {
            this.lastName = lastName;
        }
    }
    public void setEmail(String email) {
        if(!email.equals(null)) {
            this.email = email;
        }
    }
    public void setFacebookID(String facebookID) {
        if(!facebookID.equals(null)) {
            this.facebookID = facebookID;
        }
    }
}
