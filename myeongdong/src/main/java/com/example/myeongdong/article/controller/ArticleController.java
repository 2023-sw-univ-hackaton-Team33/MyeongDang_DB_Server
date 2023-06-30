package com.example.myeongdong.article.controller;

import com.example.myeongdong.article.dto.ArticleFindAllByCompanyNameResponseDto;
import com.example.myeongdong.article.dto.ArticleRequestDto;
import com.example.myeongdong.article.dto.ArticleResponseDto;
import com.example.myeongdong.article.entity.Article;
import com.example.myeongdong.article.repository.ArticleRepository;
import com.example.myeongdong.company.dto.CompanyResponseDto;
import com.example.myeongdong.exception.BusinessException;
import com.example.myeongdong.response.BaseResponseDto;
import com.example.myeongdong.response.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.myeongdong.response.ErrorMessage.ALREADY_EXISTED_ARTICLE;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    @PostMapping("")
    public BaseResponseDto<ArticleResponseDto> create(@RequestBody ArticleRequestDto articleRequestDto){
        Article article = Article.builder()
                .companyName(articleRequestDto.getCompanyName())
                .title(articleRequestDto.getTitle())
                .keyWord(articleRequestDto.getKeyWord())
                .summary(articleRequestDto.getSummary())
                .url(articleRequestDto.getUrl())
                .build();

        articleRepository.save(article);
        if(article.getId() == -1) return new BaseResponseDto(ALREADY_EXISTED_ARTICLE);
        else return new BaseResponseDto(new CompanyResponseDto(article.getId()));
    }

    @GetMapping("/{companyName}")
    public BaseResponseDto<Stream<ArticleFindAllByCompanyNameResponseDto>> findByCompanyName(@PathVariable String companyName){
        List<Article> articles = articleRepository.findAllByCompanyName(companyName);
        if(articles.isEmpty())
            throw new BusinessException(ErrorMessage.ARTICLE_NOT_FOUND);

        //articles.stream().map(ArticleFindAllByCompanyNameResponseDto::new);

        return new BaseResponseDto<>(articles.stream().map(ArticleFindAllByCompanyNameResponseDto::new));
    }

}
