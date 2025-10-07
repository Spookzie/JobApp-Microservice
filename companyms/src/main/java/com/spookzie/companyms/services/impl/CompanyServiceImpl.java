package com.spookzie.companyms.services.impl;

import com.spookzie.companyms.CompanymsApplication;
import com.spookzie.companyms.clients.ReviewClient;
import com.spookzie.companyms.domain.dtos.ReviewDto;
import com.spookzie.companyms.domain.entities.Company;
import com.spookzie.companyms.repositories.CompanyRepository;
import com.spookzie.companyms.services.CompanyService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepo;
    private final ReviewClient reviewClient;


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


    /*  PUT & PATCH */
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

    @Transactional
    @Override
    public void updateCompanyRating(ReviewDto reviewDto)
    {
        System.out.println(reviewDto.getDescription());

        Company company = this.companyRepo.findById(reviewDto.getCompanyId())
                .orElseThrow(
                        () -> new NotFoundException("Company not found with id " + reviewDto.getCompanyId())
                );

        double avgRating = this.reviewClient.getAvgRatingForCompany(reviewDto.getCompanyId());
        company.setRating(avgRating);
        this.companyRepo.save(company);
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
