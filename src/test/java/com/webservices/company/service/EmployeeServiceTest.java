package com.webservices.company.service;

import com.webservices.company.domain.Company;
import com.webservices.company.domain.Employee;
import com.webservices.company.domain.EmploymentType;
import com.webservices.company.exceptions.CompanyException;
import com.webservices.company.exceptions.IllegalArgException;
import com.webservices.company.exceptions.ResourceNotFoundException;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private ICompanyRepository companyRepository;
    @Mock
    private IEmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployeeThrowsResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.get(6l));
        assertEquals("Employee with id6is not found!", resourceNotFoundException.getMessage());
    }

    @Test
    void getEmployee() {
        Mockito.when(employeeRepository.get(23l)).thenReturn(new Employee());
        Employee employee = employeeService.get(23L);
        assertNotNull(employee);
    }

    @Test
    void addEmployeeThrowsResourceNotFoundException() {
        Employee newEmployee = new Employee("Dania", 5500, "Dania@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 14L);
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.add(14l, newEmployee));
        assertEquals("Company with companyId: 14is not available!", resourceNotFoundException.getMessage());
    }

    @Test
    void addEmployeeWithIllegalArgException() {
        Employee newEmployee2 = new Employee("Julia", -2500, "Julia@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 19L);
        Mockito.when(companyRepository.get(19L)).thenReturn(new Company());
        IllegalArgException illegalArgException = assertThrowsExactly(IllegalArgException.class, () -> employeeService.add(19l, newEmployee2));
        assertEquals("Salary can't be a negative value!", illegalArgException.getMessage());
    }

    @Test
    void addEmployeeThrowsCompanyException() {
        Employee newEmployee3 = new Employee(null, 2500, "Julia@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 22L);
        Mockito.when(companyRepository.get(22L)).thenReturn(new Company());
        CompanyException companyException = assertThrowsExactly(CompanyException.class, () -> employeeService.add(22l, newEmployee3));
        assertEquals("Employee's name can't be a null value !", companyException.getMessage());
    }

    @Test
    void addEmployeeSuccess() {
        Employee newEmployee4 = new Employee("Loreen", 2500, "Loreen@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 15L);
        Mockito.when(companyRepository.get(15L)).thenReturn(new Company());
        Mockito.when(employeeRepository.add(newEmployee4, 15L))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(0);
                    Employee employeeArg = (Employee) firstArgument;
                    employeeArg.setId(40);
                    return employeeArg;
                });
        Mockito.when(employeeRepository.get(40L)).thenReturn(newEmployee4);
        Employee addedEmployee = employeeService.add(15l, newEmployee4);
        assertNotNull(addedEmployee);
        Mockito.verify(employeeRepository).add(newEmployee4, 15L);
        assertEquals(40, addedEmployee.getId());
    }

    @Test
    void updateEmployeeThrowsResourceNotFoundException() {
        Employee newEmployee = new Employee("Sarah", 4500, "Sarah@gmail.com", "HR", Instant.now(), EmploymentType.PART_TIME, 25L);
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.update(25l, newEmployee));
        assertEquals("Employee with employeeId: 25is not available!", resourceNotFoundException.getMessage());
    }

    @Test
    void updateEmployeeThrowsCompanyException() {
        Employee newEmployee5 = new Employee(null, 2500, "Mary@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 27L);
        Mockito.when(employeeRepository.get(26L)).thenReturn(new Employee());
        CompanyException companyException = assertThrowsExactly(CompanyException.class, () -> employeeService.update(26l, newEmployee5));
        assertEquals("Employee's name can't be a null value !", companyException.getMessage());
    }

    @Test
    void updateEmployeeWithIllegalArgException() {
        Employee newEmployee6 = new Employee("Julia", -2500, "Julia@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 28L);
        Mockito.when(employeeRepository.get(29L)).thenReturn(new Employee());
        IllegalArgException illegalArgException = assertThrowsExactly(IllegalArgException.class, () -> employeeService.update(29l, newEmployee6));
        assertEquals("Salary can't be a negative value!", illegalArgException.getMessage());
    }

    @Test
    void updateEmployeeSuccess() {
        Employee newEmployee7 = new Employee("Loreen", 2500, "Loreen@gmail.com", "Finance", Instant.now(), EmploymentType.PART_TIME, 35L);
        Mockito.when(employeeRepository.get(38L)).thenReturn(new Employee());
        Mockito.when(employeeRepository.update(38l, newEmployee7))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Object firstArgument = invocation.getArgument(1);
                    Employee employeeArg = (Employee) firstArgument;
                    employeeArg.setId(39);
                    return employeeArg;
                });
        Mockito.when(employeeRepository.get(39L)).thenReturn(newEmployee7);
        Employee updatedEmployee = employeeService.update(38l, newEmployee7);
        assertNotNull(updatedEmployee);
        Mockito.verify(employeeRepository).update(38l, newEmployee7);
        assertEquals(39, updatedEmployee.getId());
    }

    @Test
    void deleteEmployeeThrowsResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException =
                assertThrowsExactly(ResourceNotFoundException.class, () -> employeeService.delete(49l));
        assertEquals("Employee with id49is not found!", resourceNotFoundException.getMessage());
    }

    @Test
    void deleteEmployeeSuccess() {
        Mockito.when(employeeRepository.get(50l)).thenReturn(new Employee());
        employeeService.delete(50l);
        Mockito.verify(employeeRepository, times(1)).delete(50l);
    }

}