package com.webservices.company.repository;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;

import java.util.List;

public interface IEmployeeRepository {

    Employee add(Employee newEmployee, Long companyId);

    Employee get(Long employeeId);

    Employee update(Long employeeId, Employee employeeToUpdate);

    void delete(Long employeeId);

    List<Employee> getAll();

    List<Employee> getAllByCompanyId(Long id);

    void deleteAllByCompanyId(Long companyId);
}
