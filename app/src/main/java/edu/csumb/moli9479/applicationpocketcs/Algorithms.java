package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/11/16.
 */

public class Algorithms extends CSTopic {
    private String runtime;

    public Algorithms() {
        super();
    }


    public Algorithms(String name, String description, String runtime, String pseudocode,String helpfulLink, int categoryID){
        setName(name);
        setDescription(description);
        setRuntime(runtime);
        setImage(pseudocode);
        setHelpfulLink(helpfulLink);
        setCategoryID(categoryID);
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        if(!runtime.equals(null)) {
            this.runtime = runtime;
        }
    }

    public String toString() {
        return ("Name: " + getName() +
                "\nDescription: " + getDescription() +
                "\nUser ID: " + getUserID() +
                "\nRuntime: " + getRuntime() +
                "\nImage: " + getImage() +
                "\nCategory ID: " + getCategoryID() +
                "\nHelpful Links: " + getHelpfulLink());
    }
}
