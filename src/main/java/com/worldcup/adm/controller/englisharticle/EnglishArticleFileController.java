package com.worldcup.adm.controller.englisharticle;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.service.EnglishArticleFileService;
import com.worldcup.adm.util.ParameterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/englisharticlefile")
@Slf4j
public class EnglishArticleFileController {
    @Autowired
    private EnglishArticleFileService englishArticleFileService;

    @RequestMapping({"","/"})
    public String list(HttpServletRequest request, ModelMap modelMap) {
        Integer page = ParameterUtil.defaultZero(request.getParameter("page"));
        EnglishArticleFile file = new EnglishArticleFile();
        file.setPage(page);
        modelMap.put("page", page);
        Page<EnglishArticleFile> files = englishArticleFileService.listByPage(file, Sort.by(Sort.Order.desc("id")));
        modelMap.put(Constants.MODEL_MAP_PAGE, files);
        return "englisharticle/file_list";
    }

}
