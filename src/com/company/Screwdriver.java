package com.company;

public class Screwdriver extends ItemsBad {

    private static final int lifeMinus = 1;
    private static final String screwdriverPath = "icon/screwdriver.png";

    public Screwdriver(Game game, int positionX){
        super(game, screwdriverPath, positionX, lifeMinus);
    }

    @Override
    public void effect() {
        super.effect();
        getMr().screwdriverEffect();
    }
}
