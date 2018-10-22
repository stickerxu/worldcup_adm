package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.EnglishWord;
import com.worldcup.adm.repository.EnglishWordRepository;
import com.worldcup.adm.service.EnglishWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EnglishWordServiceImpl implements EnglishWordService {
    @Autowired
    private EnglishWordRepository englishWordRepository;
    @Override
    public void save(EnglishWord englishWord) {
        englishWordRepository.save(englishWord);
    }

    @Override
    public void saveAll(Set<EnglishWord> wordSet) {
        englishWordRepository.saveAll(wordSet);
    }

    @Override
    public Integer countTodayNewWords() {
        return englishWordRepository.countTodayNewWords();
    }

    @Override
    public Integer countAll() {
        return Long.valueOf(englishWordRepository.count()).intValue();
    }

    @Override
    public Page<EnglishWord> listByPage(EnglishWord word, Sort sorts) {
        return englishWordRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(null != word.getWord()){
                predicates.add(builder.equal(root.get("word").as(String.class), word.getWord()));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(word.getPage(), word.getSize(), sorts));
    }

    @Override
    public List<EnglishWord> listAll() {
        return englishWordRepository.findAll();
    }
}
