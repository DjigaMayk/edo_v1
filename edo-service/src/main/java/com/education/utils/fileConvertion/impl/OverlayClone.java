package com.education.utils.fileConvertion.impl;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.geom.AffineTransform;

public class OverlayClone extends org.apache.pdfbox.multipdf.Overlay {
    @Override
    protected AffineTransform calculateAffineTransform(PDPage page, PDRectangle overlayMediaBox) {
        AffineTransform at = new AffineTransform();
//        PDRectangle pageMediaBox = page.getMediaBox();
        at.translate(200, overlayMediaBox.getWidth() + 100);
        at.quadrantRotate(3, 0, 0);
        return at;
    }
}
