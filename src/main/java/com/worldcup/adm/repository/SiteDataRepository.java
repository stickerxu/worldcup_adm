package com.worldcup.adm.repository;

import com.worldcup.adm.entity.SiteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteDataRepository extends JpaRepository<SiteData, Integer> {
    SiteData findByDataKeyEquals(String dataKey);
}
