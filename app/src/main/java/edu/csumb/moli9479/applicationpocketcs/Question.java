package edu.csumb.moli9479.applicationpocketcs;

/**
 * Created by josephmolina on 11/22/16.
 */

public class Question {

    private int QID;
    private String QUESTION;
    private String FIRSTOPTION;
    private String SECONDOPTION;
    private String THIRDOPTION;

    private String ANSWER;
    private int QCATEGORY;

    public Question() {
        QID = 0;
        QUESTION = "";
        FIRSTOPTION = "";
        SECONDOPTION = "";
        THIRDOPTION = "";

        ANSWER = "";
        QCATEGORY = 0;

    }

    public Question(String qUESTION, String aNSWER,String oPTA, String oPTB, String oPTC, int category) {
        QUESTION = qUESTION;
        ANSWER = aNSWER;
        FIRSTOPTION = oPTA;
        SECONDOPTION = oPTB;
        THIRDOPTION = oPTC;
        QCATEGORY = category;
       //QCATEGORY = this.QCATEGORY;
    }

    public int getQID() {
        return QID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getFIRSTOPTION() {
        return FIRSTOPTION;
    }

    public String getSECONDOPTION() {
        return SECONDOPTION;
    }

    public String getTHIRDOPTION() {
        return THIRDOPTION;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public int getQCATEGORY(){
        return QCATEGORY;
    }

    public void setQID(int id) {
        QID = id;
    }

    public void setQUESTION(String qUESTION) {
        QUESTION = qUESTION;
    }

    public void setFIRSTOPTION(String oPTA) {
        FIRSTOPTION = oPTA;
    }

    public void setSECONDOPTION(String oPTB) {
        SECONDOPTION = oPTB;
    }

    public void setTHIRDOPTION(String oPTC) {
        THIRDOPTION = oPTC;
    }

    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }

    public void setCATEGORY(int QCATEGORY){
        QCATEGORY = this.QCATEGORY;
    }

}
