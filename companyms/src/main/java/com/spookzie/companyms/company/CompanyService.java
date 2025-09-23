package com.spookzie.companyms.company;

import java.util.List;

public interface CompanyService
{
    List<Company> findAll();

    Company findById(Long id);

    Company createCompany(Company company);

    Company updateCompany(Long id, Company company);

    boolean deleteById(Long id);
}