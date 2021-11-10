package com.mindex.challenge.data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Compensation {

    private Employee employee;
    private double salary;
    private LocalDate effectiveDate;

    public Employee getEmployee() {
        return employee;
    }

    public double getSalary() {
        return salary;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Compensation(Employee employee, double salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Compensation(){

    }
}
