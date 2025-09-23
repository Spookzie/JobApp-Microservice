package com.spookzie.companyms.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController
{
    private final CompanyService companyService;


    /*  GET */
    @GetMapping
    public ResponseEntity<List<Company>> findAll()
    {
        List<Company> companiesFound = this.companyService.findAll();

        if(companiesFound.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(companiesFound, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id)
    {
        Company companyFound = this.companyService.findById(id);
        if(companyFound != null)
            return new ResponseEntity<>(companyFound, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  POST    */
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company)
    {
        Company createdCompany = this.companyService.createCompany(company);

        if(createdCompany != null)
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    /*  PUT */
    @PutMapping("/{id}")
    public ResponseEntity<Company> fullUpdateCompany(@PathVariable Long id, @RequestBody Company company)
    {
        Company updatedCompany = this.companyService.updateCompany(id, company);

        if(updatedCompany != null)
            return new ResponseEntity<>(updatedCompany, HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    /*  DELETE  */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id)
    {
        boolean isDeleted = this.companyService.deleteById(id);
        if(isDeleted)
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);

        return new ResponseEntity<>("Company doesn't exist", HttpStatus.NOT_FOUND);
    }
}