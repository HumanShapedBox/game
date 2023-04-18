package com.mygdx.game.IWantToPlayAGame.units;

import java.util.ArrayList;

public interface GameInterface {
    
    void step(ArrayList<BaseHero> crew, ArrayList<BaseHero> enemy);
    String getInfo();
}
