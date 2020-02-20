package com.company;

public class Plier extends ItemsBad {

    private static final int lifeMinus = 1;
    private static final String plierPath = "icon/plier.png";

    public Plier(Game game, int positionX) {
        super(game, plierPath, positionX, lifeMinus);
    }

    @Override
    public void effect() {
        super.effect();
    }
}
