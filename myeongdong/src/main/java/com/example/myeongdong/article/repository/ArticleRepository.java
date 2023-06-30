package com.example.myeongdong.article.repository;

import com.example.myeongdong.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCompanyName(String companyName);

}
