package com.webservices.company.repository;

import com.webservices.company.domain.Company;

import java.util.List;

public interface ICompanyRepository {
    Company add(Company newCompany);

    Company get(Long id);

    Company update(Long id, Company updatedInfo);

    void delete(Long id);

    List<Company> getAll();
}
