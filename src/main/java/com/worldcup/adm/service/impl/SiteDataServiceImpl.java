package com.worldcup.adm.service.impl;

import com.worldcup.adm.entity.SiteData;
import com.worldcup.adm.repository.SiteDataRepository;
import com.worldcup.adm.service.SiteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteDataServiceImpl implements SiteDataService {
    @Autowired
    private SiteDataRepository siteDataRepository;

    @Override
    public void save(SiteData siteData) {
        SiteData data = getByDataKey(siteData.getDataKey());
        if (data != null) {
            data.setDataValue(siteData.getDataValue());
            updateById(data);
        } else {
            siteDataRepository.save(siteData);
        }
    }

    @Override
    public void updateById(SiteData siteData) {
        siteDataRepository.saveAndFlush(siteData);
    }

    @Override
    public SiteData getByDataKey(String dataKey) {
        return siteDataRepository.findByDataKeyEquals(dataKey);
    }
}
