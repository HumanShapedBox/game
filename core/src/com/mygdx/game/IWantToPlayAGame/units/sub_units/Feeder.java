package com.mygdx.game.IWantToPlayAGame.units.sub_units;

import com.mygdx.game.IWantToPlayAGame.AnsiColors;
import com.mygdx.game.IWantToPlayAGame.units.Peasant;
import com.mygdx.game.IWantToPlayAGame.units.Position;

import java.util.Random;


public class Feeder extends Peasant {

    public Feeder(String name, int x, int y) {

        super(100, name, new Position(x, y), x, y, 0, new Random(), new int[] { 1, 8 },
                new Random(), 1, false, "stand");

    }

    @Override
    public String getInfo() {
       return "Крестьянин ";
    }
    
}
