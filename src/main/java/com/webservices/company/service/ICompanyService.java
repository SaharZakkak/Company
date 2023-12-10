package com.webservices.company.service;

import com.webservices.company.domain.Company;

public interface ICompanyService {
    Company get(Long id);
    Company add( Company newCompany);
    Company update(Long id, Company company);
    void delete(Long id);

}
