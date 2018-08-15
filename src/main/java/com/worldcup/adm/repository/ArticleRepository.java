package com.worldcup.adm.repository;

import com.worldcup.adm.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    Integer countArticlesByType(Integer type);

    @Modifying
    @Query(value = "update article art set art.status = ?2, art.publish_time = now() where art.id = ?1", nativeQuery = true)
    void updateStatusAndPublishTimeById(Integer id, Integer status);
}
