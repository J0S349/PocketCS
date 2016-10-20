package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/11/16.
 */

public class CSTopic {
    private int ID;
    private String name;
    private String description;
    private int userID;
    private String image;
    private int categoryID;
    private String helpfulLink;
    private String dateCreated;
    private String dateUpdated;

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getUserID() {
        return userID;
    }
    public String getImage() {
        return image;
    }
    public int getCategoryID() {
        return categoryID;
    }
    public String getHelpfulLink() {
        return helpfulLink;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setID(int ID) {
        if(ID > 0) {
            this.ID = ID;
        }
    }
    public void setName(String name) {
        if(!name.equals(null)) {
            this.name = name;
        }
    }
    public void setDescription(String description) {
        if(!description.equals(null)) {
            this.description = description;
        }
    }
    public void setUserID(int userID) {
        if(userID > 0) {
            this.userID = userID;
        }
    }
    public void setImage(String image) {
        if(!image.equals(null)) {
            this.image = image;
        }
    }
    public void setCategoryID(int categoryID) {
        if(categoryID > 0) {
            this.categoryID = categoryID;
        }
    }
    public void setHelpfulLink(String helpfulLink) {
        if(!helpfulLink.equals(null)) {
            this.helpfulLink = helpfulLink;
        }
    }
    public void setDateCreated(String dateCreated) {
        if(!dateCreated.equals(null)) {
            this.dateCreated = dateCreated;
        }
    }
    public void setDateUpdated(String dateUpdated) {
        if(!dateUpdated.equals(null)) {
            this.dateUpdated = dateUpdated;
        }
    }
}
