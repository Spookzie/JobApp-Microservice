package com.spookzie.companyms.repositories;

import com.spookzie.companyms.domain.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long>
{

}
