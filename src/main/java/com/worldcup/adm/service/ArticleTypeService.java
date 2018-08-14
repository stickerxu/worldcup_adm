package com.worldcup.adm.service;

import com.worldcup.adm.entity.ArticleType;

import java.util.List;

public interface ArticleTypeService {
    void save(ArticleType articleType);
    void deleteById(Integer id);
    List<ArticleType> listAll();
}
