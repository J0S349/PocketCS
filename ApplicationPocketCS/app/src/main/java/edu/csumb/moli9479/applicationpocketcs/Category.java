package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/11/16.
 */

public class Category {
    private int ID;
    private String name;
    private String description;

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
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
}
