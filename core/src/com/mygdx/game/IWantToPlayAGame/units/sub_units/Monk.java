package com.mygdx.game.IWantToPlayAGame.units.sub_units;

import com.mygdx.game.IWantToPlayAGame.AnsiColors;
import com.mygdx.game.IWantToPlayAGame.units.Position;
import com.mygdx.game.IWantToPlayAGame.units.Wizard;

import java.util.Random;


public class Monk extends Wizard {

    
    public Monk(String name, int x, int y) {

        super(100, name, new Position(x, y), x, y, 10, new Random(), new int[] { 1, 8 },
                new Random(), 2, 100, 100, 1, 10, 5, "stand");

    }

    @Override
    public String getInfo() {
        return "Монах ";
    }

}
