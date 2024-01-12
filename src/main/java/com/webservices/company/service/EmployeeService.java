package com.webservices.company.service;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.exceptions.CompanyException;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.repository.CompanyRepo;
import com.webservices.company.repository.EmployeeRepo;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository iEmployeeRepository;
    private final ICompanyRepository iCompanyRepository;
    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;

    public EmployeeService(IEmployeeRepository iEmployeeRepository, ICompanyRepository iCompanyRepository, EmployeeRepo employeeRepo, CompanyRepo companyRepo) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iCompanyRepository = iCompanyRepository;
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public Employee get(Long id) {
        return employeeRepo.findById(id.intValue()).
                orElseThrow(() -> new ResourceNotFoundException("Employee with id" + id + "is not found!"));
    }

    @Transactional
    @Override
    public Employee add(Long companyId, Employee employee) {
        if (companyRepo.findById(companyId).isEmpty()) {
            throw new ResourceNotFoundException("Company with companyId: " + companyId + "is not available!");
        }
        if (employee.getName() == null) {
            throw new CompanyException("Employee's name can't be a null value !");
        }
        if (employee.getSalary() < 0) {
            throw new IllegalArgException("Salary can't be a negative value!");
        }
        return employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public Employee update(Long employeeId, Employee employeeToUpdate) {
        if (employeeRepo.findById(employeeId.intValue()).isEmpty()) {
            throw new ResourceNotFoundException("Employee with employeeId: " + employeeId + "is not available!");
        }
        if (employeeToUpdate.getName() == null) {
            throw new CompanyException("Employee's name can't be a null value !");
        }
        if (employeeToUpdate.getSalary() < 0) {
            throw new IllegalArgException("Salary can't be a negative value!");
        }
        Employee updatedEmployee = get(employeeId);
        updatedEmployee.setId(employeeId.intValue());
        updatedEmployee.setCompanyId(employeeToUpdate.getCompanyId());
        updatedEmployee.setDepartment(employeeToUpdate.getDepartment());
        updatedEmployee.setName(employeeToUpdate.getName());
        updatedEmployee.setSalary(employeeToUpdate.getSalary());
        updatedEmployee.setEmailAddress(employeeToUpdate.getEmailAddress());
        updatedEmployee.setEmploymentType(employeeToUpdate.getEmploymentType());
        updatedEmployee.setHiringDate(employeeToUpdate.getHiringDate());
        return updatedEmployee;
    }

    @Transactional
    @Override
    public void delete(Long employeeId) {
        get(employeeId);
        employeeRepo.deleteById(employeeId.intValue());
    }
}
