package com.education.utils.fileConvertion;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.geom.AffineTransform;

public class OverlayClone extends org.apache.pdfbox.multipdf.Overlay {
    @Override
    protected AffineTransform calculateAffineTransform(PDPage page, PDRectangle overlayMediaBox) {
        AffineTransform at = new AffineTransform();
        at.translate(200, overlayMediaBox.getWidth() + 100);
        at.quadrantRotate(3, 0, 0);
//        Для PNG файлов:
//        PDRectangle pageMediaBox = page.getMediaBox();
//        at.translate(pageMediaBox.getWidth() - overlayMediaBox.getWidth() - 200, 200);
        return at;
    }
}
