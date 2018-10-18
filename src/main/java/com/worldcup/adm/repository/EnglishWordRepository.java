package com.worldcup.adm.repository;

import com.worldcup.adm.entity.EnglishWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnglishWordRepository extends JpaRepository<EnglishWord, Integer>, JpaSpecificationExecutor<EnglishWord> {

    @Query(value = "select count(*) from english_word word where date(word.create_time)=current_date()", nativeQuery = true)
    Integer countTodayNewWords();
}
