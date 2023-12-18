package com.webservices.company.controller;

import com.webservices.company.domain.Employee;
import com.webservices.company.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
@RestController
public class EmployeeController {

    private final IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }

    @GetMapping(path = "/employees/{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return iEmployeeService.get(employeeId);
    }

    @PostMapping(path = "/companies/{companyId}/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@PathVariable Long companyId, @RequestBody Employee newEmployee) {
        return iEmployeeService.add(companyId, newEmployee);
    }

    @PutMapping(path = "/employees/{employeeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employeeToUpdate) {
        return iEmployeeService.update(employeeId, employeeToUpdate);
    }

    @DeleteMapping(path = "/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long employeeId) {
        iEmployeeService.delete(employeeId);
    }


}
