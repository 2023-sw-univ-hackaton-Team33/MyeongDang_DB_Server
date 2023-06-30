package com.example.myeongdong.company.repository;

import com.example.myeongdong.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsCompanyByCompanyName(String companyName);

    Company findAllByCompanyName(String companyName);
}
