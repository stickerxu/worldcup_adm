package com.worldcup.adm.repository;

import com.worldcup.adm.entity.EnglishArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnglishArticleRepository extends JpaRepository<EnglishArticle, Integer>, JpaSpecificationExecutor<EnglishArticle> {
    @Query(value = "select count(*) from english_article article where date(article.create_time)=current_date()", nativeQuery = true)
    Integer countTodayNewArticles();

    List<EnglishArticle> findByTypeAndStauts(String type, Integer status);
}
