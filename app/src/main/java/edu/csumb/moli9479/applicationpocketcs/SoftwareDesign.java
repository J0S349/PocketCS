package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by stephennegron on 10/8/16.
 */

public class SoftwareDesign extends CSTopic{
    private String benefits;
    private String costs;

    public SoftwareDesign() {
        super();
    }

    public SoftwareDesign(String name, String description, String image,String benefits,String costs,String helpfulLink, int categoryID){
        setName(name);
        setDescription(description);
        setImage(image);
        setBenefits(benefits);
        setCosts(costs);
        setHelpfulLink(helpfulLink);
        setCategoryID(categoryID);
    }



    public String getBenefits() {
        return benefits;
    }
    public String getCosts() {
        return costs;
    }

    public void setBenefits(String benefits) {
        if(!benefits.equals(null)) {
            this.benefits = benefits;
        }
    }
    public void setCosts(String costs) {
        if(!costs.equals(null)) {
            this.costs = costs;
        }
    }
}
