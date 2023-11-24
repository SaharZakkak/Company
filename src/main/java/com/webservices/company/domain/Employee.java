package com.webservices.company.domain;
import java.time.LocalDate;
import java.util.List;

public class Employee {

    private String name;
    private int id;
    private double salary;
    private String emailAddress;
    private String department;
    private LocalDate hiringDate;
    private EmploymentType employmentType;

    public Employee() {
    }

    public Employee(String name, int id, double salary, String emailAddress, String department, LocalDate hiringDate, EmploymentType employmentType) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.emailAddress = emailAddress;
        this.department = department;
        this.hiringDate = hiringDate;
        this.employmentType = employmentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }
}
