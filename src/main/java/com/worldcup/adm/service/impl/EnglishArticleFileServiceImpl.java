package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.repository.EnglishArticleFileRepository;
import com.worldcup.adm.service.EnglishArticleFileService;
import com.worldcup.adm.util.DateTimeUtil;
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

    @Override
    public Integer countTodayNewFiles() {
        return englishArticleFileRepository.countTodayNewFiles();
    }

    @Override
    public Integer countAll() {
        return Long.valueOf(englishArticleFileRepository.count()).intValue();
    }

    @Override
    public Page<EnglishArticleFile> listByPage(EnglishArticleFile file, Sort sorts) {
        return englishArticleFileRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(file.getPage(), file.getSize(), sorts));
    }
}
