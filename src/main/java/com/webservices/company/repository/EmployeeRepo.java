package com.webservices.company.repository;

import com.webservices.company.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    void deleteByCompanyId(Long companyId);

    List<Employee> getByCompanyId(Long companyId);

}
