package com.worldcup.adm.controller.article;

import com.worldcup.adm.entity.ArticleType;
import com.worldcup.adm.service.ArticleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article/type")
@Slf4j
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("/add")
    public @ResponseBody Integer add(ArticleType articleType) {
        int result = 1;
        articleTypeService.save(articleType);
        return result;
    }
}
