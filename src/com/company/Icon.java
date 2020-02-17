package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.ImageObserver;

public abstract class Icon {

    public static final int ICONDIM = 50;

    public static BufferedImage getIcon(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static BufferedImage resize(String path) {
        BufferedImage outputImage = new BufferedImage(ICONDIM, ICONDIM, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(getIcon(path), 0, 0, ICONDIM, ICONDIM, null);
        g2d.dispose();

        return outputImage;
    }

    /*
    public static BufferedImage IconScale(String path) {
        BufferedImage original = getIcon(path);
        BufferedImage iconScaled = new BufferedImage(ICONDIM, ICONDIM, original.getType());
        AffineTransform at = AffineTransform.getScaleInstance(original.getWidth() / ICONDIM, original.getHeight() / ICONDIM);
        AffineTransformOp scaleOp =  new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        iconScaled = scaleOp.filter(original, iconScaled);

        return iconScaled;
    }

    public static BufferedImage scale(String path) {
        BufferedImage sbi = getIcon(path);
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(ICONDIM, ICONDIM, sbi.getType());
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(sbi.getWidth() / ICONDIM, sbi.getHeight() / ICONDIM);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    } */



}
