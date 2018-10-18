package com.worldcup.adm.controller.englishword;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.EnglishWord;
import com.worldcup.adm.service.EnglishWordService;
import com.worldcup.adm.util.ParameterUtil;
import com.worldcup.adm.util.ResponsePageUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/englishword")
@Slf4j
public class EnglishWordContorller {
    @Autowired
    private EnglishWordService englishWordService;

    @RequestMapping({"","/"})
    public String list(HttpServletRequest request, ModelMap modelMap) {
        Integer page = ParameterUtil.defaultZero(request.getParameter("page"));
        String wordStr = request.getParameter("word");
        EnglishWord word = new EnglishWord();
        word.setPage(page);
        modelMap.put("page", page);
        if (ParameterUtil.isNotBlank(wordStr)) {
            word.setWord(wordStr);
            modelMap.put("word", wordStr);
        }
        Page<EnglishWord> words = englishWordService.listByPage(word, Sort.by(Sort.Order.desc("id")));
        modelMap.put(Constants.MODEL_MAP_PAGE, words);
        return "englishword/list";
    }

    @GetMapping("/add")
    public String add() {
        return "englishword/add";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam("words") String words, ModelMap modelMap) {
        if (ParameterUtil.isBlank(words)) {
            return ResponsePageUtil.errorPage(modelMap, "单词填写有误！");
        }
        String[] wordArray = words.split(",+");
        //单词转为小写并通过Set去重
        Set<String> wordStringSet = new HashSet<>();
        for (String s : wordArray) {
            if (ParameterUtil.isNotBlank(s)) {
                wordStringSet.add(s.toLowerCase().trim());
            }
        }
        //将单词入库
        Set<EnglishWord> wordSet = new HashSet<>();
        EnglishWord word;
        for (String s : wordStringSet) {
            word = new EnglishWord();
            word.setWord(s);
            wordSet.add(word);
        }
        if (wordSet.size() > 0) {
            englishWordService.saveAll(wordSet);
            return ResponsePageUtil.successPage(modelMap, "操作成功", "词汇添加成功");
        }
        return ResponsePageUtil.errorPage(modelMap, "操作有误！");
    }
}
