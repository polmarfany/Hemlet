package com.company;

public abstract class ItemsBad extends Items {
    private int lifeMinus;

    public ItemsBad(Game game, String iconPath, int positionX, int lifeMinus){
        super(game, iconPath, positionX);
        this.lifeMinus = lifeMinus;
    }
}
