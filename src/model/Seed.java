/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package model;


public class Seed {

    private int ID;
    private String NAME;
    private int CODE;
    private String HIBRID_NAME;
    private String PATENT_NUMBER;
    private String PATENT_OWNER;
    private String START_DATE;
    private String END_DATE;
    private String START_REGISTER_DATE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getCODE() {
        return CODE;
    }

    public void setCODE(int CODE) {
        this.CODE = CODE;
    }

    public String getHIBRID_NAME() {
        return HIBRID_NAME;
    }

    public void setHIBRID_NAME(String HIBRID_NAME) {
        this.HIBRID_NAME = HIBRID_NAME;
    }

    public String getPATENT_NUMBER() {
        return PATENT_NUMBER;
    }

    public void setPATENT_NUMBER(String PATENT_NUMBER) {
        this.PATENT_NUMBER = PATENT_NUMBER;
    }

    public String getPATENT_OWNER() {
        return PATENT_OWNER;
    }

    public void setPATENT_OWNER(String PATENT_OWNER) {
        this.PATENT_OWNER = PATENT_OWNER;
    }

    public String getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getSTART_REGISTER_DATE() {
        return START_REGISTER_DATE;
    }

    public void setSTART_REGISTER_DATE(String START_REGISTER_DATE) {
        this.START_REGISTER_DATE = START_REGISTER_DATE;
    }

    public Seed() {
    }


    @Override
    public String toString() {
        return "Seed{" +
                "id=" + ID +
                ", name='" + NAME + '\'' +
                ", code=" + CODE +
                ", hibridName='" + HIBRID_NAME + '\'' +
                ", patentNumber='" + PATENT_NUMBER + '\'' +
                ", patentOwner='" + PATENT_OWNER + '\'' +
                ", startDate=" + START_DATE +
                ", endDate=" + END_DATE +
                ", startRegisterDate='" + START_REGISTER_DATE + '\'' +
                '}';
    }
}
