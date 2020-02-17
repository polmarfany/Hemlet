package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ItemsCreator implements Runnable {

    public static final int HOLD_TIME = 1000; //milliseconds
    public static int DIFICULTAT = 1;
    public boolean gameRunning = true;
    public static Game game;
    private static ArrayList<Integer> columnArrayList = new ArrayList<>(Arrays.asList(200, 300, 400, 500, 600)); //valors de diferents columnes, de moment hardcodejats

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

        for (int index = 0; index < DIFICULTAT; index++) { //n = numero de items que crearem per a cada tongada, augmenta amb dificultat
            Items nouItem = Math.random() < 0.9 ? createBadItem(columnArrayList.get(index)) : createGoodItem(columnArrayList.get(index)); //1 de cada 10 items sera Good, la resta Bad
            game.itemsArrayList.add(nouItem);
            new Thread(nouItem).start();
        }
    }

    private static ItemsBad createBadItem(int X) {
        double dub = Math.random();
        System.out.println(dub);
        return (dub < 1/3) ? new Hammer(game, X) : (dub > 2/3 ) ? new Wrench(game, X) : new Screwdriver(game, X); //30% per a cada item
    }

    private static ItemsGood createGoodItem(int X) {
        return (Math.random() < 0.5) ? new FirstAid(game, X) : new Shield(game, X); //5% per a cada item
    }
}
