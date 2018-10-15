package com.worldcup.adm.controller.englisharticle;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.EnglishArticle;
import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.entity.OperateResult;
import com.worldcup.adm.service.EnglishArticleFileService;
import com.worldcup.adm.service.EnglishArticleService;
import com.worldcup.adm.util.ParameterUtil;
import com.worldcup.adm.util.ResponsePageUtil;
import com.worldcup.adm.util.SequenceUtil;
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

@Controller
@Slf4j
@RequestMapping("/englisharticle")
public class EnglishArticleController {
    @Autowired
    private EnglishArticleService englishArticleService;
    @Autowired
    private EnglishArticleFileService englishArticleFileService;

    @Value("${upload.english.article.path}")
    private String uploadPath;

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

    //pdf上传页面
    @GetMapping("/upload")
    public String uploadGet() {
        return "englisharticle/upload";
    }
    //pdf上传提交
    @PostMapping("/upload")
    public String uploadPost(@RequestParam("files") MultipartFile[] files, ModelMap modelMap) {
        String fileName;
        EnglishArticleFile articleFile;
        Path path;
        try {
            for (MultipartFile file : files) {
                fileName = SequenceUtil.createFileNameStr() +
                        file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                //执行上传， 文件目录根据日期分组
                path = Paths.get(uploadPath,fileName.substring(0,8));
                if (Files.notExists(path)) {
                    Files.createDirectories(path);
                }
                Files.copy(file.getInputStream(), Paths.get(uploadPath,fileName.substring(0,8), fileName), StandardCopyOption.REPLACE_EXISTING);
                //数据入库
                articleFile = new EnglishArticleFile();
                articleFile.setFileName(fileName);
                englishArticleFileService.save(articleFile);
            }
            modelMap.put("operateResult", new OperateResult("上传成功", files.length + "个文件上传成功"));
            return "success";
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return ResponsePageUtil.errorPage(modelMap);
    }

    @GetMapping("/search")
    public String search(HttpServletRequest request, ModelMap modelMap) {
        return searchPost(request, modelMap);
    }
    @PostMapping("/search")
    public String searchPost(HttpServletRequest request, ModelMap modelMap) {
        String words = request.getParameter("words");
        if (ParameterUtil.isNotBlank(words)) {
            String[] wordArray = words.split(",");
            for (String word : wordArray) {

            }
        }
        return "englisharticle/search";
    }
}
