package com.worldcup.adm.controller.englisharticle;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.EnglishArticle;
import com.worldcup.adm.service.EnglishArticleService;
import com.worldcup.adm.util.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/englisharticle")
public class EnglishArticleController {
    @Autowired
    private EnglishArticleService englishArticleService;

    @GetMapping("/add")
    public String add() {
        return "englisharticle/add";
    }

    @PostMapping("/add")
    public String addSub(EnglishArticle article) {
        englishArticleService.save(article);
        log.info("用户：{} 添加文章成功，文章标题：{}",
                Constants.ADMIN_USER.getUsername(),
                article.getPrimaryTitle());
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") Integer id, ModelMap modelMap) {
        modelMap.put("article", englishArticleService.getById(id));
        return "englisharticle/update";
    }

    @PostMapping("/update")
    public String updateSub(EnglishArticle article) {
        englishArticleService.updateByObj(article);
        log.info("用户：{} 编辑文章成功，文章标题：{}",
                Constants.ADMIN_USER.getUsername(),
                article.getPrimaryTitle());
        return "redirect:/englisharticle";
    }

    @PostMapping("/delete")
    public @ResponseBody Integer delete(@RequestParam("id") Integer id) {
        int result = 0;
        try {
            englishArticleService.deleteById(id);
            result = 1;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    @GetMapping({"","/"})
    public String listGet(HttpServletRequest request, ModelMap modelMap) {
        return listPost(request, modelMap);
    }
    @PostMapping({"","/"})
    public String listPost(HttpServletRequest request, ModelMap modelMap) {
        Integer page = ParameterUtil.defaultZero(request.getParameter("page"));
        String type = request.getParameter("type");
        EnglishArticle article = new EnglishArticle();
        article.setPage(page);
        modelMap.put("page", page);
        if (ParameterUtil.isNotBlank(type)) {
            article.setType(type);
            modelMap.put("type", type);
        }
        Page<EnglishArticle> articles = englishArticleService.listArticleByCriteria(article, Sort.by(Sort.Order.desc("id")));
        modelMap.put(Constants.MODEL_MAP_PAGE, articles);
        return "englisharticle/list";
    }
}
