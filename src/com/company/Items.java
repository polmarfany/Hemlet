package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Items implements Runnable {

    //game boundaries handling
    public static int FLOOR_Y = 600; //quan xoca amb el terra/mr
    public static int SECTOR = 100; //quan es recorre (taula de 5x5, 500px/500px)
    private static final int DIAMETER = 30;
    public static final int MOVE_TIME = 1000; //milliseconds

    //class atributes
    private BufferedImage icon;
    private int positionX;
    private int positionY = SECTOR;
    private Game game;

    public Items(Game game, String path, int positionX) {
        this.game = game;
        this.icon = Icon.resize(path);
        this.positionX = positionX;
    }

    public void run() {
        while (this.positionY < FLOOR_Y) {
            this.positionY = this.positionY + SECTOR;
            try {
                Thread.sleep(MOVE_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (this.getBounds().intersects(game.getMr().getBounds() ) ) {
            System.out.println("bro");
            game.destroyItem(this);
        }

        else {
            game.destroyItem(this);
        }
    }

    public abstract void effect();

    public Rectangle getBounds() {
        return new Rectangle(positionX, positionY, DIAMETER, DIAMETER);
    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, positionX, positionY, null);
    }
}
