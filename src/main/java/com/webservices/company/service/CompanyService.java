package com.webservices.company.service;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.exceptions.CompanyException;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {
    private final ICompanyRepository iCompanyRepository;
    private final IEmployeeRepository iEmployeeRepository;

    public CompanyService(ICompanyRepository iCompanyRepository, IEmployeeRepository iEmployeeRepository) {
        this.iCompanyRepository = iCompanyRepository;
        this.iEmployeeRepository = iEmployeeRepository;
    }
    @Override
    public Company get(Long companyId) {
        Company company = iCompanyRepository.get(companyId);
        if (company == null) {
            throw new ResourceNotFoundException("Company with id :" + companyId + "is not found!");
        }
        List<Employee> employees = iEmployeeRepository.getAllByCompanyId(companyId);
        company.setEmployees(employees);
        return company;
    }

    @Override
    public Company add(Company newCompany) {
        if (newCompany.getNumberOfEmployees() < 0) {
            throw new IllegalArgException("Number of employees can't be negative !");
        }
        Company addedCompany = iCompanyRepository.add(newCompany);
        Long addedCompanyId = addedCompany.getId();
        //add all company Employees using companyId
        if (newCompany.getEmployees() != null) {
            newCompany.getEmployees().forEach(employee -> iEmployeeRepository.add(employee, addedCompanyId));
        }
        return get(addedCompanyId);
    }

    @Override
    public Company update(Long companyId, Company companyToUpdate) {
        Company company = iCompanyRepository.get(companyId);
        if (company == null) {
            throw new ResourceNotFoundException("Company with id :" + companyId + "is not found!");
        }
        if (companyToUpdate.getNumberOfEmployees() < 0) {
            throw new IllegalArgException("Number of employees can't be negative !");
        }
        List<Employee> companyEmployees = iEmployeeRepository.getAllByCompanyId(companyId);
        if (companyEmployees != null) {
            iEmployeeRepository.deleteAllByCompanyId(companyId); // delete all previous employees
        }
        companyToUpdate.getEmployees().forEach(employee -> iEmployeeRepository.add(employee, companyId));
        List<Employee> employees = iEmployeeRepository.getAllByCompanyId(companyId);
        Company updatedCompany = iCompanyRepository.update(companyId, companyToUpdate);
        updatedCompany.setEmployees(employees);
        return updatedCompany;
    }

    @Override
    public void delete(Long companyId) {
        Company company = iCompanyRepository.get(companyId);
        if (company == null) {
            throw new ResourceNotFoundException("Company with id :" + companyId + "is not found!");
        }
        if (iEmployeeRepository.getAllByCompanyId(companyId) == null) { //if company has no employees
            iCompanyRepository.delete(companyId);
        }
        iEmployeeRepository.deleteAllByCompanyId(companyId); // delete all employees in company
        iCompanyRepository.delete(companyId);
    }

}
