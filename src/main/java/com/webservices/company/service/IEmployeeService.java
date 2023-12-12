package com.webservices.company.service;

import com.webservices.company.domain.Employee;

public interface IEmployeeService {
   Employee get(Long id);
   Employee add(Long companyId, Employee employee);
   Employee update (Long employeeId, Employee employeeToUpdate );
   void delete(Long employeeId);

}
