package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FacsimileOverlayService;
import com.education.utils.fileConvertion.OverlayClone;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Log
@Service
@RequiredArgsConstructor
public class FacsimileOverlayServiceImpl implements FacsimileOverlayService {

    @Override
    public Map<String, Object> overlay(Map<String, Object> map, byte[] bytes) {
// TODO убрать Map и возвращать просто byte[]
        try (BufferedInputStream bis = new BufferedInputStream(
                new ByteArrayInputStream((byte[]) map.get("file")));
             BufferedInputStream bis2 = new BufferedInputStream(
                new ByteArrayInputStream(bytes));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDDocument facsimile = PDDocument.load(bis2);
            PDDocument originalDoc = PDDocument.load(bis);
            OverlayClone overlayObj = new OverlayClone();

            overlayObj.setOverlayPosition(Overlay.Position.FOREGROUND);
            overlayObj.setInputPDF(originalDoc);
            overlayObj.setAllPagesOverlayPDF(facsimile);
            Map<Integer, String> ovmap = new HashMap<>();
            overlayObj.overlay(ovmap);

            originalDoc.save(baos);

            facsimile.close(); // засунуть сразу в блок try, чтобы закрывались автоматом
            originalDoc.close();

            return Map.of("pageCount", map.get("pageCount"), "file", baos.toByteArray());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
