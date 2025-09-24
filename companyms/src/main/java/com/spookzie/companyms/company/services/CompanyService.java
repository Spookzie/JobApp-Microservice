package com.spookzie.companyms.company.services;

import com.spookzie.companyms.company.domain.Company;

import java.util.List;

public interface CompanyService
{
    List<Company> findAll();

    Company findById(Long id);

    Company createCompany(Company company);

    Company updateCompany(Long id, Company company);

    boolean deleteById(Long id);
}