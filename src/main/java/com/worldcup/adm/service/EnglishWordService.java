package com.worldcup.adm.service;

import com.worldcup.adm.entity.EnglishWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Set;

public interface EnglishWordService {
    void save(EnglishWord englishWord);
    void saveAll(Set<EnglishWord> wordSet);
    Integer countTodayNewWords();
    Integer countAll();
    Page<EnglishWord> listByPage(EnglishWord englishWord, Sort sorts);
}
