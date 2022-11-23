package com.example.demo;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class ReadImage {
    public static void main(String[] args) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:/Program Files/Java/Tess4J/tessdata");

        // the path of your tess data folder
        // inside the extracted file

        String text = tesseract.doOCR(new File("image.jpg"));



        // path of your image file
        System.out.print(text);
    }
}
