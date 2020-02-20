package com.company;

public class Hammer extends ItemsBad {

    private static final int lifeMinus = 2;
    private static final String hammerPath = "icon/hammer.png";

    public Hammer(Game game, int positionX){
        super(game, hammerPath, positionX, lifeMinus);
    }

    @Override
    public void effect() {
        super.effect();
        getMr().mrStart(); //also effect of Hammer
        //TODO mostrar vides i comprobar que funcioona
    }
}
