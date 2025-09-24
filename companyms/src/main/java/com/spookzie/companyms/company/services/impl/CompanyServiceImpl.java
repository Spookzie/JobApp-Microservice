package com.spookzie.companyms.company.services.impl;

import com.spookzie.companyms.company.domain.Company;
import com.spookzie.companyms.company.repositories.CompanyRepository;
import com.spookzie.companyms.company.services.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepo;


    /*  GET */
    @Override
    public List<Company> findAll()
    {
        return this.companyRepo.findAll();
    }

    @Override
    public Company findById(Long id)
    {
        return this.companyRepo.findById(id).orElse(null);
    }

    /*  POST    */
    @Override
    public Company createCompany(Company company)
    {
        return this.companyRepo.save(company);
    }


    /*  PUT */
    @Transactional
    @Override
    public Company updateCompany(Long id, Company company)
    {
        Optional<Company> companyOptional = this.companyRepo.findById(id);

        if(companyOptional.isPresent())
        {
            Company existingCompany = companyOptional.get();
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());

            this.companyRepo.save(existingCompany);
            return existingCompany;
        }


        return null;
    }


    /*  DELETE  */
    @Transactional
    @Override
    public boolean deleteById(Long id)
    {
        Optional<Company> companyFound = this.companyRepo.findById(id);

        if(companyFound.isPresent())
        {
            this.companyRepo.deleteById(id);
            return true;
        }

        return false;
    }
}
