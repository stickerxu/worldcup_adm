package com.worldcup.adm.repository;

import com.worldcup.adm.entity.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepostory extends JpaRepository<ArticleType, Integer> {

}
