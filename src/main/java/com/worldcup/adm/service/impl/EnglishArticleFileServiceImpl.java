package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.repository.EnglishArticleFileRepository;
import com.worldcup.adm.service.EnglishArticleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnglishArticleFileServiceImpl implements EnglishArticleFileService {
    @Autowired
    private EnglishArticleFileRepository englishArticleFileRepository;
    @Override
    public void save(EnglishArticleFile file) {
        englishArticleFileRepository.save(file);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatusById(Integer id, Integer status) {
        englishArticleFileRepository.updateStatusById(id, status);
    }

    @Override
    public List<EnglishArticleFile> listByStatus(Integer status) {
        return englishArticleFileRepository.findAllByStatus(status);
    }

    @Override
    public EnglishArticleFile getById(Integer id) {
        return englishArticleFileRepository.findById(id).get();
    }
}
