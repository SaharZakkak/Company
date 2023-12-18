package com.webservices.company.controller;

import com.webservices.company.domain.Company;
import com.webservices.company.service.ICompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/companies")
@RestController
public class CompanyController {
    private final ICompanyService iCompanyService;

    public CompanyController(ICompanyService iCompanyService) {
        this.iCompanyService = iCompanyService;
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompany(@PathVariable Long companyId) {
        return iCompanyService.get(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company add(@RequestBody Company newCompany) {
        return iCompanyService.add(newCompany);
    }

    @PutMapping(path = "/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Company update(@PathVariable Long companyId, @RequestBody Company newCompany) {
        return iCompanyService.update(companyId, newCompany);
    }

    @DeleteMapping(path = "/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long companyId) {
        iCompanyService.delete(companyId);
    }

}
