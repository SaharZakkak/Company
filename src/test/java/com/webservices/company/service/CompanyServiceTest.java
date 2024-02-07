package com.webservices.company.service;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.domain.EmploymentType;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
import com.webservices.company.repository.EmployeeRepository;
import com.webservices.company.repository.ICompanyRepository;
import com.webservices.company.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;
    @Mock
    private ICompanyRepository companyRepository;
    @Mock
    private IEmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCompanyThrowsResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> companyService.get(3l));
        assertEquals("Company with id :3is not found!", resourceNotFoundException.getMessage());
    }

    @Test
    void getCompanyWithoutEmployees() {
        Mockito.when(companyRepository.get(1l)).thenReturn(new Company());
        Company company = companyService.get(1L);
        assertNotNull(company);
        assertNotNull(company.getEmployees());
        assertEquals(0, company.getEmployees().size());
    }

    @Test
    void getCompanyWithEmployees() {
        Mockito.when(companyRepository.get(41L)).thenReturn(new Company());
        Mockito.when(employeeRepository.getAllByCompanyId(41l)).thenReturn(Arrays.asList(
                new Employee(), new Employee()));
        Company company = companyService.get(41L);
        assertNotNull(company);
        assertNotNull(company.getEmployees());
        assertEquals(2, company.getEmployees().size());
    }

    @Test
    void addedCompanyWithoutEmployees() {
        Company newCompany = new Company("GreenLand", "Jerusalem", 45, Instant.now(), "Eco-Friendly Products", null);
        Mockito.when(companyRepository.add(any()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(0);
                    Company companyArg = (Company) firstArgument;
                    companyArg.setId(50L);
                    return companyArg;
                });
        Mockito.when(companyRepository.get(50L)).thenReturn(newCompany);
        Company addedCompany = companyService.add(newCompany);
        assertNotNull(addedCompany);
        assertNotNull(addedCompany.getId());
        Mockito.verify(companyRepository).add(any());
        Mockito.verify(employeeRepository, never()).add(any(), anyLong());
    }

    @Test
    void updateThrowsResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> companyService.get(4l));
        assertEquals("Company with id :4is not found!", resourceNotFoundException.getMessage());
    }

    @Test
    void updateIllegalArgException() {
        Company companyToUpdate = new Company("GreenLand", "Jerusalem", -45, Instant.now(), "Eco-Friendly Products", null);
        Mockito.when(companyRepository.get(10L)).thenReturn(companyToUpdate);
        IllegalArgException illegalArgException = assertThrowsExactly(IllegalArgException.class, () -> companyService.update(10l, companyToUpdate));
        assertEquals("Number of employees can't be negative !", illegalArgException.getMessage());
    }

    @Test
    void updateCompanyWithoutEmployees() {
        List<Employee> newEmployees = Arrays.asList(new Employee("Juleen", 4500, "Juleen@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 12L));
        Company companyToUpdate = new Company("GreenLand", "Jerusalem", 45, Instant.now(), "Eco-Friendly Products", newEmployees);
        Mockito.when(companyRepository.get(12L)).thenReturn(new Company());
        Mockito.when(employeeRepository.getAllByCompanyId(12L)).thenReturn(null);
        Mockito.when(companyRepository.update(12L, companyToUpdate))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(1);
                    Company companyArg = (Company) firstArgument;
                    companyArg.setId(12L);
                    return companyArg;
                });
        Mockito.when(companyRepository.get(12L)).thenReturn(companyToUpdate);
        Company updatedCompany = companyService.update(12l, companyToUpdate);
        assertNotNull(updatedCompany);
        assertNotNull(updatedCompany.getId());
        Mockito.verify(companyRepository).update(12l, updatedCompany);
        Mockito.verify(employeeRepository, never()).deleteAllByCompanyId(12l);
    }

    @Test
    void updateCompanyWithEmployees() {
        List<Employee> newEmployees = Arrays.asList(new Employee("Juleen", 4500, "Juleen@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 17L));
        Company companyToUpdate = new Company("GreenLand2", "Jerusalem", 45, Instant.now(), "Eco-Friendly Products", newEmployees);
        Mockito.when(companyRepository.get(17L)).thenReturn(new Company());
        Mockito.when(companyRepository.update(17L, companyToUpdate))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(1);
                    Company companyArg = (Company) firstArgument;
                    companyArg.setId(17L);
                    return companyArg;
                });
        Mockito.when(companyRepository.get(17l)).thenReturn(companyToUpdate);
        Company updatedCompany = companyService.update(17l, companyToUpdate);
        assertNotNull(updatedCompany);
        assertNotNull(updatedCompany.getId());
        Mockito.verify(companyRepository).update(17l, updatedCompany);
        Mockito.verify(employeeRepository, times(1)).deleteAllByCompanyId(17l);
    }

    @Test
    void deleteCompanyThrowsResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> companyService.delete(9l));
        assertEquals("Company with id :9is not found!", resourceNotFoundException.getMessage());
    }

    @Test
    void deleteCompanyWithoutEmployees() {
        Mockito.when(companyRepository.get(20l)).thenReturn(new Company());
        companyService.delete(20l);
        Mockito.verify(companyRepository, times(1)).delete(20l);
    }

    @Test
    void deleteCompanyWithEmployees() {
        Mockito.when(companyRepository.get(40l)).thenReturn(new Company());
        Employee employee1 = new Employee("Celina", 4500, "Celina@gmail.com", "Accounting", Instant.now(), EmploymentType.FULL_TIME, 40L);
        Mockito.when(employeeRepository.getAllByCompanyId(40L)).thenReturn(Arrays.asList(employee1));
        companyService.delete(40l);
        Mockito.verify(employeeRepository, times(1)).deleteAllByCompanyId(40l);
        Mockito.verify(companyRepository, times(1)).delete(40l);
    }

}