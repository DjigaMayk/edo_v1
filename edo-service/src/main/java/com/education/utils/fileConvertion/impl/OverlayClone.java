package com.education.utils.fileConvertion.impl;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.awt.geom.AffineTransform;

public class OverlayClone extends org.apache.pdfbox.multipdf.Overlay {
    @Override
    protected AffineTransform calculateAffineTransform(PDPage page, PDRectangle overlayMediaBox) {
        AffineTransform at = new AffineTransform();
        PDRectangle pageMediaBox = page.getMediaBox();
        at.translate(0, pageMediaBox.getHeight() - overlayMediaBox.getHeight());
        return at;
    }
}
