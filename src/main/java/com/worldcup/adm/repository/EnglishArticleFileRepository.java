package com.worldcup.adm.repository;

import com.worldcup.adm.entity.EnglishArticleFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnglishArticleFileRepository extends JpaRepository<EnglishArticleFile, Integer>, JpaSpecificationExecutor<EnglishArticleFile> {
    List<EnglishArticleFile> findAllByStatus(Integer status);

    @Modifying
    @Query(value = "update english_article_file file set file.status = ?2 where file.id = ?1", nativeQuery = true)
    void updateStatusById(Integer id, Integer status);
}
