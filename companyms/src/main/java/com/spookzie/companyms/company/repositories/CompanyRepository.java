package com.spookzie.companyms.company.repositories;

import com.spookzie.companyms.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long>
{

}
