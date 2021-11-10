package com.mindex.challenge.data;

import java.util.List;

public class ReportingStructure {

    private int numberOfReports;
    private Employee employee;

    public ReportingStructure() {

    }

    public ReportingStructure(int numberOfReports, Employee employee){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
