package com.worldcup.adm.service;

import com.worldcup.adm.entity.EnglishArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface EnglishArticleService {
    void save(EnglishArticle article);

    void updateByObj(EnglishArticle article);

    void deleteById(Integer id);

    EnglishArticle getById(Integer id);

    Page<EnglishArticle> listArticleByCriteria(EnglishArticle article, Sort sorts);
}
