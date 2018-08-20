package com.worldcup.adm.controller.article;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.ArticleType;
import com.worldcup.adm.service.ArticleTypeService;
import com.worldcup.adm.util.ResponsePageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/article/type")
@Slf4j
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping({"","/"})
    public String list(ModelMap modelMap) {
        List<ArticleType> articleTypes = articleTypeService.listAll();
        modelMap.put(Constants.MODEL_MAP_PAGE, articleTypes);
        return "articletype/list";
    }

    @PostMapping("/add")
    public void add(ArticleType articleType, HttpServletResponse response) {
        articleTypeService.save(articleType);
        log.error("新闻增文章类型：{}", articleType.getName());
        try {
            ResponsePageUtil.backAndRefresh(response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @GetMapping("/update/get")
    public @ResponseBody ArticleType updateGet(@RequestParam("id") Integer id) {
        ArticleType articleType = articleTypeService.getById(id);
        return articleType;
    }

    @PostMapping("/update")
    public @ResponseBody Integer update(ArticleType articleType, HttpServletResponse response) {
        ArticleType type = articleTypeService.getById(articleType.getId());
        if (type == null) {
            return 0;
        }
        type.setName(articleType.getName());
        type.setWeight(articleType.getWeight());
        articleTypeService.updateByObj(type);
        return 1;
    }

    @PostMapping("/show")
    public @ResponseBody Integer show(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        Integer result = 1;
        articleTypeService.updateStatusById(id, status);
        return result;
    }

    @GetMapping("/delete")
    public @ResponseBody Integer delete(@RequestParam("id") Integer id) {
        Integer result = 1;
        articleTypeService.deleteById(id);
        return  result;
    }
}
