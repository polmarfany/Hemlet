package com.company;

public class Shield extends ItemsGood {

    public static final String shieldPath = "icon/shield.png";

    public Shield(Game game, int positionX) {
        super(game, shieldPath, positionX);
    }

    @Override
    public void effect() {

    }
}
