package com.company;

public class Wrench extends ItemsBad {

    public static final int lifeMinus = 1;
    public static final String wrenchPath = "icon/wrench.png";

    public Wrench(Game game, int positionX){
        super(game, wrenchPath, positionX, lifeMinus);
    };


    @Override
    public void effect() {

    }
}
