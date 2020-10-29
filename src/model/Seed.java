/*
 *  Created by Gulzar Safar on 10/29/2020
 */

package model;


import java.time.LocalDate;

public class Seed {

    private long id;
    private String name;
    private long code;
    private String hibridName;
    private String patentNumber;
    private String patentOwner;
    private String startDate;
    private String endDate;
    private String startRegisterDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getHibridName() {
        return hibridName;
    }

    public void setHibridName(String hibridName) {
        this.hibridName = hibridName;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public String getPatentOwner() {
        return patentOwner;
    }

    public void setPatentOwner(String patentOwner) {
        this.patentOwner = patentOwner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartRegisterDate() {
        return startRegisterDate;
    }

    public void setStartRegisterDate(String startRegisterDate) {
        this.startRegisterDate = startRegisterDate;
    }

    public Seed() {
    }


    @Override
    public String toString() {
        return "Seed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", hibridName='" + hibridName + '\'' +
                ", patentNumber='" + patentNumber + '\'' +
                ", patentOwner='" + patentOwner + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startRegisterDate='" + startRegisterDate + '\'' +
                '}';
    }
}
