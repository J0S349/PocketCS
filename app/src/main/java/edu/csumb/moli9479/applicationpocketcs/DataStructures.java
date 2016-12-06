package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/8/16.
 */

public class DataStructures extends CSTopic{
    private String runtime;

    public DataStructures() {
        super();
    }

    public DataStructures(String name, String description, String runtime, String pseudocode,String helpfulLink, int categoryID){
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
}
