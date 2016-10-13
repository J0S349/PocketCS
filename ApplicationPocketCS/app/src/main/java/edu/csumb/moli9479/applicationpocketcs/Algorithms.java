package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/11/16.
 */

public class Algorithms extends CSTopic {
    private String runtime;

    public Algorithms() {
        super();
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
