package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FacsimileOverlayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Log
@Service
@RequiredArgsConstructor
public class FacsimileOverlayServiceImpl implements FacsimileOverlayService {
    @Override
    public byte[] overlay(byte[] bytes) {

        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream(bytes));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDDocument facsimile = PDDocument.load(new File("FACSIMILE.pdf"));

            PDDocument overlayDoc = new PDDocument();
            PDPage page = new PDPage();
            overlayDoc.addPage(page);
            Overlay overlayObj = new Overlay();
            PDFont font = PDType1Font.COURIER_OBLIQUE;

            PDPageContentStream contentStream = new PDPageContentStream(overlayDoc, page);
            contentStream.setFont(font, 50);
            contentStream.setNonStrokingColor(0);
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(200, 200);
            contentStream.drawString("deprecated");  // deprecated. Use showText(String text)
            contentStream.endText();
            contentStream.close();

            PDDocument originalDoc = PDDocument.load(bis);
            overlayObj.setOverlayPosition(Overlay.Position.FOREGROUND);
            overlayObj.setInputPDF(originalDoc);
            overlayObj.setAllPagesOverlayPDF(overlayDoc);
            Map<Integer, String> ovmap = new HashMap<Integer, String>(); // empty map is a dummy
//            overlayObj.setOutputFile("... result-with-overlay.pdf");
            overlayObj.overlay(ovmap);
            overlayDoc.close();
//            originalDoc.close();

            originalDoc.save(baos);
            originalDoc.close();

            return baos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
