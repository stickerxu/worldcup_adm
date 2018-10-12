package com.worldcup.adm.repository;

import com.worldcup.adm.entity.EnglishArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EnglishArticleRepository extends JpaRepository<EnglishArticle, Integer>, JpaSpecificationExecutor<EnglishArticle> {
}
