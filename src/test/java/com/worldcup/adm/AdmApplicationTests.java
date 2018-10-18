package com.worldcup.adm;

import com.worldcup.adm.service.EnglishArticleFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdmApplicationTests {
    @Autowired
    private EnglishArticleFileService englishArticleFileService;

    @Test
    public void contextLoads() {
        Integer count = englishArticleFileService.countAll();
        System.out.println(count);
    }

}
