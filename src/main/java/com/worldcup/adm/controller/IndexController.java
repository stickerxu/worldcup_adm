package com.worldcup.adm.controller;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.SiteData;
import com.worldcup.adm.entity.jsonobject.IndexEnglishArticleData;
import com.worldcup.adm.service.SiteDataService;
import com.worldcup.adm.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {
    @Autowired
    private SiteDataService siteDataService;

    @RequestMapping({"","/"})
    public String index(ModelMap modelMap) {
        //首页英文文章数据统计
        SiteData dataA = siteDataService.getByDataKey(Constants.KEY_INDEX_ENGLISH_ARTICLE_DATA);
        if (dataA != null) {
            IndexEnglishArticleData englishArticleData = JsonUtil.json2Obj(dataA.getDataValue(), IndexEnglishArticleData.class);
            modelMap.put("englishArticleData",englishArticleData);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }
}
