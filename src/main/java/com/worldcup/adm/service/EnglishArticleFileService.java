package com.worldcup.adm.service;

import com.worldcup.adm.entity.EnglishArticleFile;

import java.util.List;

public interface EnglishArticleFileService {
    void save(EnglishArticleFile file);

    void updateStatusById(Integer id, Integer status);

    List<EnglishArticleFile> listByStatus(Integer status);
}
