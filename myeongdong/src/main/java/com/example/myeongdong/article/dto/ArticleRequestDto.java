package com.example.myeongdong.article.dto;

import lombok.Getter;

@Getter
public class ArticleRequestDto {
    private String companyName;

    private String title;

    private String keyWord;

    private String summary;

    private String url;
}
