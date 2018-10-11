package com.worldcup.adm.util;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.parser.FilteredTextRenderListener;
import com.itextpdf.text.pdf.parser.LocationTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PdfResolveUtil {
    private static final String TEST_PDF_FROM = "D:\\test\\engread\\pdf\\2018.08.25.pdf";
    private static final String TEST_PDF_TARGET = "D:\\test\\engread\\pdf\\test.pdf";
    private static final String TEST_TXT_TARGET = "D:\\test\\engread\\txt\\2018.08.25.txt";

    public static void main(String[] args) throws Exception{
        readByItext();
    }

    public static void readByItext() throws Exception{
        PdfReader reader = new PdfReader(TEST_PDF_FROM);
        //根据页数提取pdf
        /*Integer[] pages = new Integer[10];
        for (int i =0; i < 10; i++) {
            pages[i] = i+1;
        }
        reader.selectPages(Arrays.asList(pages));*/
//        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(TEST_PDF_TARGET));
//        stamper.close();
        /*PrintWriter writer = new PrintWriter(new FileOutputStream(TEST_TXT_TARGET));
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        int size = reader.getNumberOfPages();
        StringBuffer sb = new StringBuffer();
        for (int i = 1 ; i <= size; i++) {
            sb.append(parser.processContent(i, new SimpleTextExtractionStrategy()).getResultantText());
        }
        writer.write(sb.toString());*/
        reader.close();
    }
}