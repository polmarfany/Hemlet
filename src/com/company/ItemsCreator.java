package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ItemsCreator implements Runnable {

    private static int HOLD_TIME = 1000 ; //milliseconds, TIME BETWEEN ITEM CREATED
    public boolean gameRunning = true;
    public static Game game;
    private static ArrayList<Integer> columnArrayList = new ArrayList<>(Arrays.asList(100, 200, 300, 400, 500)); //valors de diferents columnes, de moment hardcodejats

    public ItemsCreator(Game game) {
        this.game = game;
    }

    public void run(){
        while (gameRunning) {
            creator();
            try {
                Thread.sleep(HOLD_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void creator() {
        Collections.shuffle(columnArrayList); //d'aquesta manera, randomitzem on sortiran els items I (sobretot, important) no es poden repetir

        for (int index = 0; index < (Math.random() < 0.8 ? 1 : 2 ); index++) { //index = numero d'items que crearem per a cada tongada, de tant en quant en crearom 2 alhora
            Items nouItem = Math.random() < 0.95 ? createBadItem(columnArrayList.get(index)) : createGoodItem(columnArrayList.get(index)); //95% dels items sera BAD, el 5% sera GOOD
            game.itemsArrayList.add(nouItem);
            new Thread(nouItem).start();
        }
    }

    private static ItemsBad createBadItem(int X) {
        double dub = Math.random();
        return dub < 1.0/3.0 ? new Hammer(game, X) : (dub > 2.0/3.0 ) ? new Wrench(game, X) : new Screwdriver(game, X); //cada item te un 33% de possibilitats per cada BADITEM
    }

    private static ItemsGood createGoodItem(int X) {
        return (Math.random() < 0.8) ? new Shield(game, X) : new FirstAid(game, X); //80% dels GOOD ITEMS seran SHIELD, 20% FIRSTAID
    }
    public static void decreaseHoldTime() {
        HOLD_TIME = HOLD_TIME - 50;
    }
}
