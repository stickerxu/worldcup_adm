package com.worldcup.adm.service;

import com.worldcup.adm.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ArticleService {
    Page<Article> listArticleByCriteria(Article article, Sort sorts);

    Integer countArticlesByType(Integer type);

    void save(Article article);
}
