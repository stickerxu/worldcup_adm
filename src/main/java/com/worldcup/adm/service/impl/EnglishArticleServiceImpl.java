package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.EnglishArticle;
import com.worldcup.adm.repository.EnglishArticleRepository;
import com.worldcup.adm.service.EnglishArticleService;
import com.worldcup.adm.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnglishArticleServiceImpl implements EnglishArticleService {
    @Autowired
    private EnglishArticleRepository englishArticleRepository;
    @Override
    public void save(EnglishArticle article) {
        englishArticleRepository.save(article);
    }

    @Override
    public void updateByObj(EnglishArticle article) {
        englishArticleRepository.saveAndFlush(article);
    }

    @Override
    public void deleteById(Integer id) {
        englishArticleRepository.deleteById(id);
    }

    @Override
    public EnglishArticle getById(Integer id) {
        return englishArticleRepository.findById(id).get();
    }

    @Override
    public Page<EnglishArticle> listArticleByCriteria(EnglishArticle article, Sort sorts) {
        return englishArticleRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.notEqual(root.get("type").as(String.class), "fileExtract"));
            if(null != article.getType()){
                predicates.add(builder.equal(root.get("type").as(String.class), article.getType()));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(article.getPage(), article.getSize(), sorts));
    }

    @Override
    public List<EnglishArticle> listArticleBySearchContent(EnglishArticle article) {
        return englishArticleRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("type").as(String.class), "fileExtract"));
            predicates.add(builder.like(root.get("content").as(String.class), "%" + article.getContent() + "%"));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

    @Override
    public Integer countTodayNewArticles() {
        return englishArticleRepository.countTodayNewArticles();
    }

    @Override
    public Integer countAll() {
        return Long.valueOf(englishArticleRepository.count()).intValue();
    }
}
