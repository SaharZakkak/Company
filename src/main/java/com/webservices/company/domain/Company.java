package com.webservices.company.domain;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private String name;
    private String address;
    private int numberOfEmployees;
    private LocalDate yearFounded;
    private String typeOfBusiness;

    private List <Employee> employees;


    public Company() {
    }

    public Company(String name, String address, int numberOfEmployees, LocalDate yearFounded, String typeOfBusiness, List<Employee> employees) {
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.yearFounded = yearFounded;
        this.typeOfBusiness = typeOfBusiness;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public LocalDate getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(LocalDate yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
