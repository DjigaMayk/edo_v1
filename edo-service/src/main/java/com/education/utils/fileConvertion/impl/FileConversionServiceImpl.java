package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FileConversionService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.pdf.PdfReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Представляет реализацию конвертации файлов в формат pdf
 *
 * @author Дарья Лукьянова
 */
@Log
@Service
@RequiredArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public Map<String, Object> convertFile(MultipartFile multipartFile) {
        //определяем расширение файла
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//        без конвертации если файл уже в формате pdf
        if ("pdf".equals(extension)) {
            try (var bis = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()))) {
                var pdf = new PdfReader(bis);
                int pageCount = pdf.getNumberOfPages();
                return Map.of("pageCount", pageCount, "file", multipartFile.getBytes());
            } catch (Throwable e) {
                e.printStackTrace();
            }
//        конвертация файлов с расширениями doc, docx
        }
        if ("doc".equals(extension) || "docx".equals(extension)) {
            try (var buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 var os = new ByteArrayOutputStream()) {
                var wordMLPackage = WordprocessingMLPackage.load(buffIs);
                Docx4J.toPDF(wordMLPackage, os);
                var pdf = new PdfReader(os.toByteArray());
                int pageCount = pdf.getNumberOfPages();
                os.flush();
                return Map.of("pageCount", pageCount, "file", os.toByteArray());
            } catch (Throwable e) {
                e.printStackTrace();
            }
//        конвертация файлов с расширениями jpg, png
        } else if ("jpg".equals(extension) || "png".equals(extension)) {
            try (var buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 var os = new ByteArrayOutputStream()) {
                return convertJpgAndPng(os, buffIs, "file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Map.of("pageCount", 0, "file", new byte[0]);
    }

    @Override
    public Map<String, Object> convertFacsimile(byte[] facsimile) {
        try (var buffIs = new BufferedInputStream(
                new ByteArrayInputStream(facsimile));
             var os = new ByteArrayOutputStream()) {
            return convertJpgAndPng(os, buffIs, "facsimile");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Map.of("pageCount", 0, "facsimile", new byte[0]);
    }

    private Map<String, Object> convertJpgAndPng(ByteArrayOutputStream os, BufferedInputStream buffIs,
                                                 String key) throws IOException {
        var pdfWriter = new PdfWriter(os);
        var pdfDocument = new PdfDocument(pdfWriter);
        var document = new Document(pdfDocument);
        var data = ImageDataFactory.create(buffIs.readAllBytes());
        var image = new Image(data);
        document.add(image);
        int pageCount = document.getPdfDocument().getNumberOfPages();
        document.close();
        return Map.of("pageCount", pageCount, key, os.toByteArray());
    }
}