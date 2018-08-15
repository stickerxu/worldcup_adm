package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.ArticleType;
import com.worldcup.adm.repository.ArticleTypeRepostory;
import com.worldcup.adm.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {
    @Autowired
    private ArticleTypeRepostory articleTypeRepostory;
    @Override
    public void save(ArticleType articleType) {
        articleTypeRepostory.save(articleType);
    }

    @Override
    public void deleteById(Integer id) {
        articleTypeRepostory.deleteById(id);
    }

    @Override
    public List<ArticleType> listAll() {
        return articleTypeRepostory.findAll();
    }

    @Override
    public ArticleType getById(Integer id) {
        return articleTypeRepostory.findById(id).get();
    }

    @Override
    public void updateByObj(ArticleType articleType) {
        articleTypeRepostory.saveAndFlush(articleType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatusById(Integer id, Integer status) {
        articleTypeRepostory.updateStatusById(id, status);
    }
}
