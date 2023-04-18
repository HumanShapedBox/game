package com.mygdx.game.IWantToPlayAGame.units;

public class Position {
    
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    protected double distance(Position enemy) {
        return Math.sqrt((enemy.x - x) * (enemy.x - x) + (enemy.y - y) * (enemy.y - y));
    }

}
