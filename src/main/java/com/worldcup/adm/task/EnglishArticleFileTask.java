package com.worldcup.adm.task;

import com.worldcup.adm.entity.EnglishArticle;
import com.worldcup.adm.entity.EnglishArticleFile;
import com.worldcup.adm.service.EnglishArticleFileService;
import com.worldcup.adm.service.EnglishArticleService;
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

    @Value("${task.english.article.file}")
    private String taskOnOff;
    @Value("${upload.english.article.path}")
    private String articlePath;
    //批量处理pdf文件提取文章内容
    @Scheduled(cron = "0 */30 * * * ?")
    public void taskA() {
        if (taskOnOff.equals("on")) {
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
                StringBuffer content;
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
                            content = new StringBuffer();
                            article = new EnglishArticle();
                            for (String word : words) {
                                content.append(word).append(" ");
                            }
                            article.setType("fileExtract");
                            article.setPdfPageNumber(pageNumber);
                            article.setContent(content.toString());
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

}
