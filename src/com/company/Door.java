package com.company;

public class Door {
    private Game game;
    private int Y;
    private int X;
    private int DIMENSIONs = 30;

    public Door(Game game){
        this.game = game;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

}
