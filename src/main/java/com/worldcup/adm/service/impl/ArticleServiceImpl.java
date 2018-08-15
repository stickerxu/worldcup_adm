package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.Article;
import com.worldcup.adm.repository.ArticleRepository;
import com.worldcup.adm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Page<Article> listArticleByCriteria(Article article, Sort sorts) {
        return articleRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(null != article.getType() && article.getType() != 0){
                predicates.add(builder.equal(root.get("type").as(Integer.class), article.getType()));
            }
            if(null != article.getStatus() && article.getStatus() != 0){
                predicates.add(builder.equal(root.get("status").as(Integer.class), article.getStatus()));
            }
            if(null != article.getTitle()){
                predicates.add(builder.like(root.get("title").as(String.class), "%"+article.getTitle()+"%"));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(article.getPage(), article.getSize(), sorts));
    }

    @Override
    public Integer countArticlesByType(Integer type) {
        return articleRepository.countArticlesByType(type);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void deleteById(Integer id) {
        articleRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatusAndPublishTimeById(Integer id, Integer status) {
        articleRepository.updateStatusAndPublishTimeById(id, status);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public void updateByObj(Article article) {
        articleRepository.saveAndFlush(article);
    }
}
