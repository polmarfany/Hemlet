package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Items implements Runnable {

    //game boundaries handling
    private static final int FLOOR_Y = 500; //floor, maximum reach of painting method
    private static final int SECTOR = 100; //travel movement (table 5x5, 500px/500px)
    private static int MOVE_TIME = 1000; //milliseconds, TIME FALLING BETWEEN POSITION

    //class atributes
    private BufferedImage icon;
    private int positionX;
    private int positionY = 0;
    public Game game;

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
        game.destroyItem(this);
        if ( getMr().getPositionX() == this.positionX ) {
            this.effect();
        }
    }

    public abstract void effect();

    public Mr getMr(){
        return this.game.getMr();
    }

    public static void disminuirMoveTime() {
        MOVE_TIME = MOVE_TIME - 50;
    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, positionX, positionY, null);
    }
}
