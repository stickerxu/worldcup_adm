package com.worldcup.adm.repository;

import com.worldcup.adm.entity.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepostory extends JpaRepository<ArticleType, Integer> {
    @Modifying
    @Query(value = "update article_type set status = ?2 where id = ?1", nativeQuery = true)
    void updateStatusById(Integer id, Integer status);
}
