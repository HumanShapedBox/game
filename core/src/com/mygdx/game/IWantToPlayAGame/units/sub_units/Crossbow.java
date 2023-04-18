package com.mygdx.game.IWantToPlayAGame.units.sub_units;

import com.mygdx.game.IWantToPlayAGame.AnsiColors;
import com.mygdx.game.IWantToPlayAGame.units.Position;
import com.mygdx.game.IWantToPlayAGame.units.Shooter;

import java.util.Random;


public class Crossbow extends Shooter {

    public Crossbow(String name, int x, int y) {

        super(100, name, new Position(x, y), x, y, 10, new Random(), new int[] { 1, 8 },
                new Random(), 5, 10, 10, 0, "stand");

    }


    @Override
    public String getInfo() {
        return "Арбалетчик ";
    }
    
}
