package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FacsimileOverlayService;
import com.education.utils.fileConvertion.OverlayClone;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FacsimileOverlayServiceImpl implements FacsimileOverlayService {

    @Override
    public byte[] overlay(Map<String, Object> map, byte[] bytes) {
        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream((byte[]) map.get("file")));
             BufferedInputStream bis2 = new BufferedInputStream(
                new ByteArrayInputStream(bytes));
             PDDocument facsimile = PDDocument.load(bis2);
             PDDocument originalDoc = PDDocument.load(bis);
             OverlayClone overlayObj = new OverlayClone();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {


            overlayObj.setOverlayPosition(Overlay.Position.FOREGROUND);
            overlayObj.setInputPDF(originalDoc);
            overlayObj.setAllPagesOverlayPDF(facsimile);
            Map<Integer, String> ovmap = new HashMap<>();
            overlayObj.overlay(ovmap);
            originalDoc.save(baos);
            return baos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
