package com.webservices.company.service;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.repository.CompanyRepo;
import com.webservices.company.repository.EmployeeRepo;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {
    private final ICompanyRepository iCompanyRepository;
    private final IEmployeeRepository iEmployeeRepository;

    private final CompanyRepo companyRepo;
    private final EmployeeRepo employeeRepo;

    public CompanyService(ICompanyRepository iCompanyRepository, IEmployeeRepository iEmployeeRepository, CompanyRepo companyRepo, EmployeeRepo employeeRepo) {
        this.iCompanyRepository = iCompanyRepository;
        this.iEmployeeRepository = iEmployeeRepository;
        this.companyRepo = companyRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Company get(Long companyId) {
        return companyRepo.findById(companyId).
                orElseThrow(() -> new ResourceNotFoundException("Company with id :" + companyId + "is not found!"));

    }

    @Transactional
    @Override
    public Company add(Company newCompany) {
        if (newCompany.getNumberOfEmployees() < 0) {
            throw new IllegalArgException("Number of employees can't be negative !");
        }
        return companyRepo.save(newCompany);
    }

    @Transactional
    @Override
    public Company update(Long companyId, Company companyToUpdate) {
        get(companyId);
        if (companyToUpdate.getNumberOfEmployees() < 0) {
            throw new IllegalArgException("Number of employees can't be negative !");
        }
        List<Employee> companyEmployees = employeeRepo.getByCompanyId(companyId);
        if (companyEmployees != null) {
            employeeRepo.deleteByCompanyId(companyId); // delete all previous employees
        }
        companyToUpdate.getEmployees().forEach(employee -> employeeRepo.save(employee));
        companyToUpdate.getEmployees().forEach(employee -> employee.setCompanyId(companyId));
        List<Employee> employees = employeeRepo.getByCompanyId(companyId);
        Company updatedCompany = companyRepo.getById(companyId);
        updatedCompany.setEmployees(employees);
        updatedCompany.setId(companyId);
        updatedCompany.setAddress(companyToUpdate.getAddress());
        updatedCompany.setName(companyToUpdate.getName());
        updatedCompany.setDateFound(companyToUpdate.getDateFound());
        updatedCompany.setNumberOfEmployees(companyToUpdate.getNumberOfEmployees());
        updatedCompany.setTypeOfBusiness(companyToUpdate.getTypeOfBusiness());
        return updatedCompany;
    }

    @Transactional
    @Override
    public void delete(Long companyId) {
        get(companyId);
        if (employeeRepo.getByCompanyId(companyId) == null) { //if company has no employees
            companyRepo.deleteById(companyId);
        }

        employeeRepo.deleteByCompanyId(companyId); // delete all employees in company
        companyRepo.deleteById(companyId);
    }
}
