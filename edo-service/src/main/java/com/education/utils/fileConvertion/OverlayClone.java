package com.education.utils.fileConvertion;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.geom.AffineTransform;

public class OverlayClone extends org.apache.pdfbox.multipdf.Overlay {
    @Override
    protected AffineTransform calculateAffineTransform(PDPage page, PDRectangle overlayMediaBox) {
        AffineTransform at = new AffineTransform();
        PDRectangle pageMediaBox = page.getMediaBox();
        at.translate(pageMediaBox.getWidth() - overlayMediaBox.getWidth() - 100, 200);
        return at;
    }
}
