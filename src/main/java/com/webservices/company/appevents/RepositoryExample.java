package com.webservices.company.appevents;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.domain.EmploymentType;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RepositoryExample {
    private ICompanyRepository iCompanyRepository;
    private IEmployeeRepository iEmployeeRepository;

    public RepositoryExample(ICompanyRepository iCompanyRepository, IEmployeeRepository iEmployeeRepository) {
        this.iCompanyRepository = iCompanyRepository;
        this.iEmployeeRepository = iEmployeeRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void companyRepositoryPlayground() {
        Company company = iCompanyRepository.get(1L);  // retrieve company with id =1
        System.out.println(company.toString());

        Company company2 = iCompanyRepository.get(2L); // retrieve company with id =2
        System.out.println(company2.toString());

        // Add a new company to the database : companyToBeAdded
        Company companyToBeAdded = new Company("Green", "Jerusalem", 95, Instant.now(), "Eco-Friendly Products");
        Company addedCompany = iCompanyRepository.add(companyToBeAdded);

        //Update a company in the database

        Company companyToUpdate = new Company("Green", "Jerusalem", 100, Instant.now(), "Eco-Friendly Products");
        Company companyAfterUpdate = iCompanyRepository.update(5L, companyToUpdate);

        //Delete a company with id = 6  from database
        // iCompanyRepository.delete(6L);

        // iCompanyRepository.getAll();

        // Add a new employee to the company with companyId = 2
        Employee employeeToBeAdded = new Employee("Sandy", 5000, "Sandy@gmail.com", "Accounting", Instant.now(), EmploymentType.CONSULTANT, 1L);
        Employee addedEmployee = iEmployeeRepository.add(employeeToBeAdded, 5L);

        Employee employee1 = iEmployeeRepository.get(1L); // retrieve employee with id =1
        System.out.println("The employee is:" + employee1.toString());

        //Delete an employee with id = 5  from database
        //  iEmployeeRepository.delete(5L);

        // iEmployeeRepository.getAll();

        //Update an employee in the database
        Employee employeeToUpdate = new Employee("Sandy", 4500, "Sandy@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 2L);
        Employee employeeAfterUpdate = iEmployeeRepository.update(2L, employeeToUpdate);


    }


}
