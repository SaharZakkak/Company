package com.webservices.company.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    private String name;
    @Id
    @GeneratedValue(generator = "employees_generator")
    @SequenceGenerator(name = "employees_generator", sequenceName = "employees_id_seq", allocationSize = 1)
    private int id;
    private double salary;
    private String emailAddress;
    private String department;
    private Instant hiringDate;
    @Enumerated(EnumType.STRING)
    private EmploymentType employmentType;

    private Long companyId;

    public Employee() {
    }

    public Employee(String name, int id, double salary, String emailAddress, String department, Instant hiringDate, EmploymentType employmentType) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.emailAddress = emailAddress;
        this.department = department;
        this.hiringDate = hiringDate;
        this.employmentType = employmentType;
    }

    public Employee(String name, double salary, String emailAddress, String department, Instant hiringDate, EmploymentType employmentType, Long companyId) {
        this.name = name;
        this.salary = salary;
        this.emailAddress = emailAddress;
        this.department = department;
        this.hiringDate = hiringDate;
        this.employmentType = employmentType;
        this.companyId = companyId;
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

    public Instant getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Instant hiringDate) {
        this.hiringDate = hiringDate;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                ", emailAddress='" + emailAddress + '\'' +
                ", department='" + department + '\'' +
                ", hiringDate=" + hiringDate +
                ", employmentType=" + employmentType +
                '}';
    }
}
