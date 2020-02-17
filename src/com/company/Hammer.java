package com.company;

public class Hammer extends ItemsBad {

    public static final int lifeMinus = 2;
    public static final String hammerPath = "icon/hammer.png";

    public Hammer(Game game, int positionX){
        super(game, hammerPath, positionX, lifeMinus);
    };

    @Override
    public void effect() {

    }
}
