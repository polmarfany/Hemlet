package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Mr {

    //constants for movement calculus
    private static final int POSITIONY = 600;
    private static final int startingPositionX = 0;
    private static final int finalPositionX = 600;
    private static final int MOVEMENTAUGMENT = 100;
    private static int movementAugment = MOVEMENTAUGMENT;

    //game parameters
    private int lifes = 3; //NOSE SI ES MILLOR POSARHO AQUI O AL MAIN, DE MOMENT ES QUEDARA AL MAIN
    private int positionX = startingPositionX; //actual position, with 0 being the initial position
    private static BufferedImage icon;
    private Game game;

    private static int SWAPPER = 1;

    public Mr(Game game) {
        this.game = game;
        icon = Icon.resize("icon/mrgameandwatch.png");
    }

    public void paint(Graphics2D g) {
        g.drawImage(icon, positionX, POSITIONY, null);
    }

    public void keyReleased(KeyEvent e) {
        int futurePosition = 0;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            futurePosition = positionX + (movementAugment * SWAPPER);
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            futurePosition = positionX + (movementAugment * -SWAPPER);

        if (futurePosition >= 0)
            positionX = futurePosition;

        if (futurePosition == finalPositionX) {
            //TODO posar d'alguna manera que aparegui, ni que sigui mig segon, en la casella de sortida, per a veure com entra
            mrHasArrivedEnd();
            mrStart();
        }
    }

    //item effects
    public void wrenchEffect() {
        new Thread("wrench"){
            public void run(){
            movementAugment = 0;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            movementAugment = MOVEMENTAUGMENT;
            }
        }.start();
    }

    public void screwdriverEffect() {
        new Thread("screwdriver"){
            public void run(){
                SWAPPER = -1; //amb aquest SWAPPER per a canviar de 1 a -1, aconseguim fer el efecte del screwdriver
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SWAPPER = 1;
            }
        }.start();
    }

    //game functionalities
    public void mrStart() { //also effect of Hammer
        this.positionX = 0;
    }

    public void mrHasArrivedEnd() {
        game.incrementPoints();
    }

    public int getPositionX() {
        return positionX;
    }
}