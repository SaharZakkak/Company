package com.webservices.company.service;

import com.webservices.company.domain.Employee;
import com.webservices.company.exceptions.CompanyException;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository iEmployeeRepository;
    private final ICompanyRepository iCompanyRepository;

    public EmployeeService(IEmployeeRepository iEmployeeRepository, ICompanyRepository iCompanyRepository) {
        this.iEmployeeRepository = iEmployeeRepository;
        this.iCompanyRepository = iCompanyRepository;
    }

    @Override
    public Employee get(Long id) {
        Employee employee = iEmployeeRepository.get(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id" + id + "is not found!");
        }
        return employee;
    }

    @Override
    public Employee add(Long companyId, Employee employee) {
        if(iCompanyRepository.get(companyId)== null){
            throw new ResourceNotFoundException("Company with companyId: "+ companyId + "is not available!");
        }
        if (employee.getName() == null) {
            throw new CompanyException("Employee's name can't be a null value !");
        }
        if (employee.getSalary() < 0) {
            throw new IllegalArgException("Salary can't be a negative value!");
        }
        Employee addedEmployee = iEmployeeRepository.add(employee, companyId);
        return addedEmployee;
    }

    @Override
    public Employee update(Long employeeId, Employee employeeToUpdate) {
        if(iEmployeeRepository.get(employeeId) == null){
            throw new ResourceNotFoundException("Employee with employeeId: " + employeeId + "is not available!");
        }
        if (employeeToUpdate.getName() == null) {
            throw new CompanyException("Employee's name can't be a null value !");
        }
        if (employeeToUpdate.getSalary() < 0) {
            throw new IllegalArgException("Salary can't be a negative value!");
        }
        Employee updatedEmployee = iEmployeeRepository.update(employeeId, employeeToUpdate);
        return updatedEmployee;
    }

    @Override
    public void delete(Long employeeId) {
        Employee employee = get(employeeId);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee with id" + employeeId + "is not found!");
        }
        iEmployeeRepository.delete(employeeId);
    }
}
