package website.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    private String firstName;

    private String surName;

    private int hoursWorked;

    private String businessTag;

    public Employee() {
        this.hoursWorked = -1;
        this.firstName = "";
        this.surName = "";
        this.businessTag = "";
    }

    public Employee(final String firstName, final String surName, final String businessTag, final int hoursWorked) {
        this.firstName = firstName;
        this.surName = surName;
        this.businessTag = businessTag;
        this.hoursWorked = hoursWorked;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }
}
