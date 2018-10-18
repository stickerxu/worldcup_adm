package com.worldcup.adm.service;

import com.worldcup.adm.entity.EnglishArticleFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EnglishArticleFileService {
    void save(EnglishArticleFile file);

    void updateStatusById(Integer id, Integer status);

    List<EnglishArticleFile> listByStatus(Integer status);

    EnglishArticleFile getById(Integer id);

    Integer countTodayNewFiles();

    Integer countAll();

    Page<EnglishArticleFile> listByPage(EnglishArticleFile file, Sort sorts);
}
