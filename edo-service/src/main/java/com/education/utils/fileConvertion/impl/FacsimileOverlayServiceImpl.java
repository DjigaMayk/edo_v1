package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FacsimileOverlayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
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

            PDDocument facsimile = PDDocument.load(new File("FACSIMILE_2.pdf"));
            PDDocument originalDoc = PDDocument.load(bis);
            OverlayClone overlayObj = new OverlayClone();

            overlayObj.setOverlayPosition(Overlay.Position.FOREGROUND);
            overlayObj.setInputPDF(originalDoc);
            overlayObj.setAllPagesOverlayPDF(facsimile);
            Map<Integer, String> ovmap = new HashMap<>();
            overlayObj.overlay(ovmap);
            originalDoc.save(baos);

            facsimile.close();
            originalDoc.close();

            return baos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
