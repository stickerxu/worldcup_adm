package com.worldcup.adm.service;

import com.worldcup.adm.entity.SiteData;

public interface SiteDataService {
    void save(SiteData siteData);
    void updateById(SiteData siteData);
    SiteData getByDataKey(String dataKey);
}
