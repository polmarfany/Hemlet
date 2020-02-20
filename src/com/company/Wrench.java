package com.company;

public class Wrench extends ItemsBad {

    private static final int lifeMinus = 1;
    private static final String wrenchPath = "icon/wrench.png";

    public Wrench(Game game, int positionX){
        super(game, wrenchPath, positionX, lifeMinus);
    }


    @Override
    public void effect() {
        super.effect();
        getMr().wrenchEffect();
    }
}
