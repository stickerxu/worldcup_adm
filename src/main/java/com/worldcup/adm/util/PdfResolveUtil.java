package com.worldcup.adm.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PdfResolveUtil {
    private static final Integer PDF_PAGE_LENGTH = 4000;
    private static final Integer PDF_PAGE_READ_START = 5;
    public static final String PDF_MAP_WORD_SET_NAME = "words";
    public static final String PDF_MAP_PAGE_NAME = "pageNumber";

    //通过itext解析pdf, 返回一个Map， 包含所有单词和页码
    public static List<Map<String, Object>> readByItext(String filePath) throws IOException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        PdfReader reader = new PdfReader(filePath);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        int pageSize = reader.getNumberOfPages();
        String text;
        int textLength;
        Set<String> words;
        Map<String, Object> map;
        //从第5页开始（根据观察一般第5页之前都为目录部分），遍历Pdf每一页
        for (int i = PDF_PAGE_READ_START; i <= pageSize; i++) {
            text = parser.processContent(i, new SimpleTextExtractionStrategy()).getResultantText();
            //将页面内容总长度 > 指定长度的页面筛选出来
            textLength = text.length();
            if (textLength > PDF_PAGE_LENGTH) {
                map = new HashMap<>();
                //将页面中出现的单词统计出来
                words = readContent(text);
                map.put(PDF_MAP_WORD_SET_NAME,words);
                map.put(PDF_MAP_PAGE_NAME,i);
                resultList.add(map);
            }
        }
        reader.close();
        return resultList;
    }
    //读取文章中的单词
    private static Set<String> readContent(String content) {
        Set<String> wordSet = new HashSet<>();
        content = content.toLowerCase().replaceAll("[^A-Za-z]"," ").trim();
        String[] words = content.split("\\s+");
        for (String word : words) {
            if (ParameterUtil.isNotBlank(word)) {
                wordSet.add(word);
            }
        }
        return wordSet;
    }

    //提取文章所在的pdf页面(单页)
    public static void extractPdfPage(String filePath, int page, OutputStream os) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filePath);
        //根据页数提取pdf
        reader.selectPages(String.valueOf(page));
        PdfStamper stamper = new PdfStamper(reader, os);
        stamper.close();
        reader.close();
        os.flush();
        os.close();
    }
}