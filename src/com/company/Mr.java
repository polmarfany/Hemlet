package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mr {
    private static final int positionY = 600;
    private int lifes = 3;
    private int x = 100;
    private static BufferedImage icon;
    private Game game;
    private static final int MRDIAMETER = 30;

    public Mr(Game game) {
        this.game = game;

        this.icon = Icon.resize("icon/mrgameandwatch.png");

    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, x, positionY, null);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && x > 100)
            x = x - 100;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && x < 600)
            x = x + 100;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, positionY, MRDIAMETER, MRDIAMETER);
    }

    public int getTopY() {
        return positionY - MRDIAMETER;
    }

    public static BufferedImage getIcon(){
        return icon;
    }


}