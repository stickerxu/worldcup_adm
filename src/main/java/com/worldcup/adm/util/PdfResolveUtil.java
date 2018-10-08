package com.worldcup.adm.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PdfResolveUtil {
    public static void main(String[] args) throws Exception{
        String path = "/Users/xujingyao/Programming/document/pdf/2018.08.18.pdf";
        PDDocument pdDocument = PDDocument.load(new File(path));
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(1);
        stripper.setEndPage(1);
        String text = stripper.getText(pdDocument);
        System.out.println(text);

    }
}
