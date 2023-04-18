package com.mygdx.game.IWantToPlayAGame.units.sub_units;

import com.mygdx.game.IWantToPlayAGame.AnsiColors;
import com.mygdx.game.IWantToPlayAGame.units.Position;
import com.mygdx.game.IWantToPlayAGame.units.Warrior;

import java.util.Random;


public class Thug extends Warrior {

    public Thug(String name, int x, int y) {

        super(100, name, new Position(x, y), x, y, 15, new Random(), new int[] { 1, 8 },
                new Random(), 3, 0, "stand");
    }

    @Override
    public String getInfo() {
        return "Бандит ";
    }
    
}
