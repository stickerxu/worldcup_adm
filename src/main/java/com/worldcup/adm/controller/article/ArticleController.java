package com.worldcup.adm.controller.article;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.Article;
import com.worldcup.adm.entity.OperateResult;
import com.worldcup.adm.repository.ArticleRepository;
import com.worldcup.adm.service.ArticleService;
import com.worldcup.adm.util.ParameterUtil;
import com.worldcup.adm.util.ResponsePageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Value("${web.article.path.dev}")
    private String webArticlePath;

    @RequestMapping({"", "/"})
    public String list(HttpServletRequest request, ModelMap modelMap) {
        Integer page = ParameterUtil.defaultZero(request.getParameter("page"));
        Integer type = ParameterUtil.defaultZero(request.getParameter("type"));
        Integer status = ParameterUtil.defaultZero(request.getParameter("status"));
        String title = request.getParameter("title");

        Article article = new Article();
        article.setPage(page);
        modelMap.put("page", page);
        article.setType(type);
        modelMap.put("type", type);
        article.setType(status);
        modelMap.put("status", status);
        if (ParameterUtil.isNotBlank(title)) {
            article.setTitle(title);
            modelMap.put("title", title);
        }
        Page<Article> articles = articleService.listArticleByCriteria(article, Sort.by(Sort.Order.desc("id")));
        modelMap.put("pageList", articles);
        return "article/list";
    }

    @GetMapping("/add")
    public String add() {
        return "article/add";
    }
    @PostMapping("/addSub")
    public String addSub(Article article, @RequestParam("file") MultipartFile file, ModelMap modelMap) {
        //校验文件类型
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (!suffix.equals(Constants.FILE_SUFFIX_HTML)) {
            return ResponsePageUtil.errorPage("文件类型有误! 请选择html格式文件", modelMap);
        }
        try {
            Integer maxArticleId = articleService.countArticlesByType(article.getType());
            StringBuilder builder = new StringBuilder();
            builder.append("article_").append(article.getType()).append("_").append(++maxArticleId)
                    .append(suffix);
            //上传
            Path dirPath = Paths.get(webArticlePath, String.valueOf(article.getType()));
            if (Files.notExists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            Files.copy(file.getInputStream(), Paths.get(dirPath.toString(), builder.toString()), StandardCopyOption.REPLACE_EXISTING);
            //入库
            article.setFileName(builder.toString());
            articleService.save(article);
            log.info("添加文章：{}",article.getFileName());
            //返回结果
            OperateResult operateResult = new OperateResult("文章添加成功","文章已在首页中显示！");
            modelMap.put("operateResult",operateResult);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "error";
        }
        return "success";
    }

    @PostMapping("/publish")
    public @ResponseBody int publish(@RequestParam("id") Integer id) {
        int result = 1;
        articleService.updateStatusById(id);
        return result;
    }

    @PostMapping("/delete")
    public @ResponseBody int delete(@RequestParam("id") Integer id) {
        int result = 1;
        articleService.deleteById(id);
        return result;
    }
}
