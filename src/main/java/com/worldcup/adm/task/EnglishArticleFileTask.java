package com.worldcup.adm.task;

import com.worldcup.adm.Constants;
import com.worldcup.adm.entity.EnglishArticle;
import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.entity.EnglishWord;
import com.worldcup.adm.entity.SiteData;
import com.worldcup.adm.entity.jsonobject.IndexEnglishArticleData;
import com.worldcup.adm.service.EnglishArticleFileService;
import com.worldcup.adm.service.EnglishArticleService;
import com.worldcup.adm.service.EnglishWordService;
import com.worldcup.adm.service.SiteDataService;
import com.worldcup.adm.util.JsonUtil;
import com.worldcup.adm.util.PdfResolveUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class EnglishArticleFileTask {
    @Autowired
    private EnglishArticleFileService englishArticleFileService;
    @Autowired
    private EnglishArticleService englishArticleService;
    @Autowired
    private EnglishWordService englishWordService;
    @Autowired
    private SiteDataService siteDataService;

    @Value("${task.english.article.file}")
    private String taskAOnOff;
    @Value("${task.english.article.data}")
    private String taskBOnOff;
    @Value("${task.english.article.word.search}")
    private String taskCOnOff;
    @Value("${upload.english.article.path}")
    private String articlePath;
    //批量处理pdf文件提取文章内容
    @Scheduled(cron = "0 0/30 6-11,13-23 * * ?")
    public void taskA() {
        if (taskAOnOff.equals("on")) {
            log.info("开始扫描未处理pdf文件...");
            //查出所有未处理的pdf
            List<EnglishArticleFile> files = englishArticleFileService.listByStatus(0);
            log.info("扫描完毕，共需要处理pdf文件 {} 个", files.size());
            if (files.size() > 0) {
                //读取文件
                String filePath;
                EnglishArticle article;
                Set<String> words;
                Integer pageNumber;
                StringBuffer contentSb;
                String content;
                try {
                    for (EnglishArticleFile file : files) {
                        filePath = Paths.get(articlePath, file.getFileName().substring(0,8), file.getFileName()).toString();
                        log.info("开始处理pdf：{}", filePath);
                        //处理结果集数据：单词和页码
                        List<Map<String, Object>> maps = PdfResolveUtil.readByItext(filePath);
                        log.info("共需要处理文章：{}篇", maps.size());
                        for (Map<String, Object> map : maps) {
                            //处理单个pdf中的文章内容
                            words = (Set<String>) map.get(PdfResolveUtil.PDF_MAP_WORD_SET_NAME);
                            pageNumber = (Integer) map.get(PdfResolveUtil.PDF_MAP_PAGE_NAME);
                            contentSb = new StringBuffer();
                            article = new EnglishArticle();
                            for (String word : words) {
                                contentSb.append(word).append(" ");
                            }
                            content = contentSb.toString();
                            article.setType("fileExtract");
                            article.setSecondTitle(content.substring(0, 40) + "...");
                            article.setPdfPageNumber(pageNumber);
                            article.setContent(content);
                            article.setPdfPrimaryFileId(file.getId());
                            //将文章入库
                            englishArticleService.save(article);
                        }
                        englishArticleFileService.updateStatusById(file.getId(), 1);
                        log.info("pdf文件：{} 处理完毕", filePath);
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    //更新首页文章相关数据
    @Scheduled(cron = "0 5/30 6-11,13-23 * * ?")
    public void taskB() {
        if (taskBOnOff.equals("on")) {
            log.info("开始统计首页英文文章数据...");
            //今日上传文件数
            Integer countTodayNewFiles = englishArticleFileService.countTodayNewFiles();
            //今日新增文章数
            Integer countTodayNewArticles = englishArticleService.countTodayNewArticles();
            //今日新增词汇数
            Integer countTodayNewWords = englishWordService.countTodayNewWords();
            //历史总计文件数
            Integer totalFiles = englishArticleFileService.countAll();
            //历史总计文章数
            Integer totalArticles = englishArticleService.countAll();
            //历史总计词汇数
            Integer totalWords = englishWordService.countAll();
            IndexEnglishArticleData data = new IndexEnglishArticleData();
            data.setTodayNewFile(countTodayNewFiles);
            data.setTodayNewArticle(countTodayNewArticles);
            data.setTodayNewWord(countTodayNewWords);
            data.setHistoryTotalFile(totalFiles);
            data.setHistoryTotalArticle(totalArticles);
            data.setHistoryTotalWord(totalWords);
            //转为json字符串入库
            String jsonString = JsonUtil.obj2Json(data);
            //查询数据库 key是否存在，存在就更新value，不存在就插入;
            SiteData siteData = new SiteData();
            siteData.setDataKey(Constants.KEY_INDEX_ENGLISH_ARTICLE_DATA);
            siteData.setDataValue(jsonString);
            siteDataService.save(siteData);
            log.info("首页英文文章数据统计完毕");
        }
    }

    //文章包含高频词汇统计
    @Scheduled(cron = "0 15 0,12 * * ?")
    public void taskC() {
        if (taskCOnOff.equals("on")) {
            log.info("开始进行词汇文章匹配...");
            //查询所有单词
            List<EnglishWord> words = englishWordService.listAll();
            //查询所有文章
            List<EnglishArticle> articles = englishArticleService.findByTypeAndStatus("fileExtract", 0);
            int wordSize = words.size();
            int articleSize = articles.size();
            if (wordSize == 0 || articleSize == 0) {
                return;
            }
            log.info("查询完毕，共需对 {} 篇文章和 {} 个单词进行匹配", wordSize, articleSize);
            //根据文章内容对单词进行检索，添加计数器
            String content;
            String wordStr;
            StringBuffer wordInfo;
            //将处理结果放在一个 List<EnglishArticleSearch> 中
            int countWord;
            int updateArticle = 0;
            for (EnglishArticle article : articles) {
                content = article.getContent();
                wordInfo = new StringBuffer();
                countWord = 0;
                //对文章内容进行单词检索，记录 word,article
                for (EnglishWord word : words) {
                    wordStr = word.getWord();
                    if (content.contains(wordStr)) {
                        countWord++;
                        wordInfo.append(word.getWord()).append(",");
                        continue;
                    }
                }
                //只对包含词汇 > 1 的 文章进行存储
                //修改english_article表的 contain_words字段，并更新文章状态为：1、已更新高频词汇
                if (countWord > 1) {
                    updateArticle++;
                    wordInfo.setLength(wordInfo.length() -1);
                    article.setStatus(1);
                    article.setContainWords(wordInfo.toString());
                    article.setContainWordSize(countWord);
                    englishArticleService.updateByObj(article);
                }
            }
            log.info("词汇文章匹配完毕，共更新数据：{} 条", updateArticle);
        }
    }
}
