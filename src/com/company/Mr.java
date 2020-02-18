package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Mr {

    //constants for movement calculus
    private static final int POSITIONY = 600;
    private static final int startingPositionX = 0;
    private static final int finalPositionX = 600;
    private static final int movementAugment = 100;


    //game parameters
    private int lifes = 3;
    private int positionX = startingPositionX; //actual position, with 0 being the initial position
    private static BufferedImage icon;
    private Game game;
    private static final int MRDIAMETER = 30;

    private int SWAPPER = 1;

    public Mr(Game game) {
        this.game = game;
        icon = Icon.resize("icon/mrgameandwatch.png");
    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, positionX, POSITIONY, null);
    }

    public void keyReleased(KeyEvent e) {
        int futurePosition = 0;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            futurePosition = positionX + (movementAugment * -SWAPPER);

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            futurePosition = positionX + (movementAugment * SWAPPER);

        if (futurePosition >= 0 && futurePosition <= 500 )
            positionX = futurePosition;

        else if (futurePosition == finalPositionX) {
            positionX = futurePosition;
            //TODO posar d'alguna manera que aparegui, ni que sigui mig segon, en la casella de sortida, per a veure com entra
            mrHasArrivedEnd();
            mrStart();
        }
    }

    //item effects
    public void wrenchEffect() {

    }

    public void screwdriverEffect() {
        SWAPPER = SWAPPER * -1; //amb aquest SWAPPER per a canviar de 1 a -1, aconseguim fer el efecte del screwdriver
    }

    //game functionalities
    public void mrStart() { //also effect of Hammer
        this.positionX = 0;
    }

    public void mrHasArrivedEnd() {
        game.augmentPoints();
    }

    public int getPositionX() {
        return positionX;
    }
}