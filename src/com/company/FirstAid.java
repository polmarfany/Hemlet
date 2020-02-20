package com.company;

public class FirstAid extends ItemsGood {

    private static final String firstAidPath = "icon/firstaid.png";

    public FirstAid(Game game, int positionX) {
        super(game, firstAidPath, positionX);
    }

    @Override
    public void effect() {
        game.incrementLifes();
    }
}
