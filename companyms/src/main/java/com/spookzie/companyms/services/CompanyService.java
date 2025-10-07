package com.spookzie.companyms.services;

import com.spookzie.companyms.domain.dtos.ReviewDto;
import com.spookzie.companyms.domain.entities.Company;

import java.util.List;

public interface CompanyService
{
    List<Company> findAll();

    Company findById(Long id);

    Company createCompany(Company company);

    Company updateCompany(Long id, Company company);
    void updateCompanyRating(ReviewDto reviewDto);

    boolean deleteById(Long id);
}