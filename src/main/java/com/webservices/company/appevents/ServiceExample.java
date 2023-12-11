package com.webservices.company.appevents;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.domain.EmploymentType;
import com.webservices.company.service.ICompanyService;
import com.webservices.company.service.IEmployeeService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Component
public class ServiceExample {
    private ICompanyService iCompanyService;
    private IEmployeeService iEmployeeService;


    public ServiceExample(ICompanyService iCompanyService, IEmployeeService iEmployeeService) {
        this.iCompanyService = iCompanyService;
        this.iEmployeeService = iEmployeeService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void companyServicePlayground() {
//       Company company = iCompanyService.get(2L);
//        System.out.println(company.getEmployees());
//
//        Employee employee1 = new Employee("Sahar", 4500, "Sahar@gmail.com", "Software Development", Instant.now(), EmploymentType.FULL_TIME, 2L);
//        Employee employee2 = new Employee("Mary", 4500, "Mary@gmail.com", "Finance", Instant.now(), EmploymentType.FULL_TIME, 2L);
//        List<Employee> employees = Arrays.asList(employee1, employee2);
//
//
//      Company companyToAdd = new Company("Green2", "Jerusalem", -2, Instant.now(), "Eco-Friendly Products",employees);
//      Company addedCompany = iCompanyService.add(companyToAdd);
//        System.out.println("=======newly added employees===========");
//        System.out.println(addedCompany);

        //     iCompanyService.delete(10L);
        //     iCompanyService.get(2l);
        //    iCompanyService.delete(40l);

      /* List<Employee> newEmployees = Arrays.asList(new Employee("Tania", 4500, "Tania@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 12L),
                                        new Employee("Sarah", 5500, "Sarah@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 12L));
        Company companyToUpdate = new Company("Green7", "Jerusalem", 9, Instant.now(), "Eco-Friendly Products",newEmployees);
        iCompanyService.update(12L,companyToUpdate); */
        //  System.out.println(iCompanyService.get(12L));
        //   System.out.println(iEmployeeService.get(4l));

        //    Employee newEmployee = new Employee("Dania", 5500, "Dania@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 12L);
        //  Company company3 = new Company("Green10", "Jerusalem", 0, Instant.now(), "Eco-Friendly Products",null);
        //  iCompanyService.add(company3);
     /*   List<Employee> newEmployees = Arrays.asList(new Employee("Juleen", 4500, "Juleen@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 12L),

                new Employee("Julia", 5500, "Julia@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 12L));
        Company companyToUpdate = new Company("Green16", "Jerusalem", 45, Instant.now(), "Eco-Friendly Products",newEmployees);
        iCompanyService.update(8L,companyToUpdate);*/

        // iCompanyService.delete(47l);
        //  Employee newEmployee = new Employee("Tamara", 6500, "Tamara@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 12L);
        //   iEmployeeService.update(71888l, newEmployee);
        //  iEmployeeService.get(426666666l);
        //iEmployeeService.add(8l, newEmployee);


    }
}
