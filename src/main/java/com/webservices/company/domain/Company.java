package com.webservices.company.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(generator = "companies_generator")
    @SequenceGenerator(name = "companies_generator", sequenceName = "companies_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private int numberOfEmployees;
    private Instant dateFound;
    private String typeOfBusiness;

    @Transient
    private List<Employee> employees;

    public Company() {
    }

    public Company(String name, String address, int numberOfEmployees, Instant dateFound, String typeOfBusiness) {
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.dateFound = dateFound;
        this.typeOfBusiness = typeOfBusiness;
    }

    public Company(String name, String address, int numberOfEmployees, Instant dateFound, String typeOfBusiness, List<Employee> employees) {
        this.name = name;
        this.address = address;
        this.numberOfEmployees = numberOfEmployees;
        this.dateFound = dateFound;
        this.typeOfBusiness = typeOfBusiness;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getDateFound() {
        return dateFound;
    }

    public void setDateFound(Instant dateFound) {
        this.dateFound = dateFound;
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

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                ", dateFound=" + dateFound +
                ", typeOfBusiness='" + typeOfBusiness + '\'' +
                ", employees=" + employees +
                '}';
    }
}
