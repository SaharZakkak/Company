package com.webservices.company.repository;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {

}
