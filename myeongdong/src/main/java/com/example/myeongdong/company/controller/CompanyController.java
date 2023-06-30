package com.example.myeongdong.company.controller;

import com.example.myeongdong.company.dto.CompanyFindByCompanyNameResponseDto;
import com.example.myeongdong.company.dto.CompanyRequestDto;
import com.example.myeongdong.company.dto.CompanyResponseDto;
import com.example.myeongdong.company.entity.Company;
import com.example.myeongdong.company.repository.CompanyRepository;
import com.example.myeongdong.exception.BusinessException;
import com.example.myeongdong.response.BaseResponseDto;
import com.example.myeongdong.response.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.myeongdong.response.ErrorMessage.ALREADY_EXISTED_COMPANY;


@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyRepository companyRepository;

    @PostMapping("")
    public BaseResponseDto<CompanyResponseDto> create(@RequestBody CompanyRequestDto companyRequestDto){
        Company companyInfo = Company.builder()
                .companyName(companyRequestDto.getCompanyName())
                .mainProduct(companyRequestDto.getMainProduct())
                .sales(companyRequestDto.getSales())
                .employeeNum(companyRequestDto.getEmployeeNum())
                .reputation(companyRequestDto.getReputation())
                .capital(companyRequestDto.getCapital())
                .location(companyRequestDto.getLocation())
                .build();

        companyRepository.save(companyInfo);
        if(companyInfo.getId() == -1) return new BaseResponseDto(ALREADY_EXISTED_COMPANY);
        else return new BaseResponseDto(new CompanyResponseDto(companyInfo.getId()));
    }

    @GetMapping("/{companyName}")
    public BaseResponseDto<CompanyFindByCompanyNameResponseDto> findByCompanyName(@PathVariable String companyName){
        if(!companyRepository.existsCompanyByCompanyName(companyName))
            throw new BusinessException(ErrorMessage.COMPANY_NOT_FOUND);
        Company company = companyRepository.findAllByCompanyName(companyName);

        return new BaseResponseDto<>(new CompanyFindByCompanyNameResponseDto(
                company.getCompanyName(),
                company.getMainProduct(),
                company.getSales(),
                company.getEmployeeNum(),
                company.getReputation(),
                company.getCapital(),
                company.getLocation())
        );
    }

}
