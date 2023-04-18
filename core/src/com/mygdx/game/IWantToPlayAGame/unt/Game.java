package com.mygdx.game.IWantToPlayAGame.unt;


import com.mygdx.game.IWantToPlayAGame.Names;
import com.mygdx.game.IWantToPlayAGame.units.BaseHero;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Archer;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Crossbow;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Feeder;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Knight;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Monk;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Thug;
import com.mygdx.game.IWantToPlayAGame.units.sub_units.Warlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Game {

    public ArrayList<BaseHero> good;
    public ArrayList<BaseHero> bad;
    public ArrayList<BaseHero> unitedList;
    public int teamSize;

    public void start(){
        this.teamSize = 10;
        this.good = new ArrayList<>();
        this.bad = new ArrayList<>();
        units(good, bad);
        this.unitedList = new ArrayList<>();
        unitedList.addAll(good);
        unitedList.addAll(bad);
    }

    public void nextMove(){
        unitSort();
        for (BaseHero unit : unitedList) {
            if (good.contains(unit))
                unit.step(good, bad);
            else
                unit.step(bad, good);
        }
    }

    public Boolean endGame(ArrayList<BaseHero> units){
        Boolean flag = false;
        int counter = 0;
        for (BaseHero human : units) {
            if(human.getHp() <= 0) counter += 1;
        }
        if (counter == 10) {flag = true;}
        return flag;
    }

    public void itsAlive(ArrayList<BaseHero> units){
        for (BaseHero human : units) {
            human.setHp(100);
        }
    }

    public void units(ArrayList<BaseHero> good, ArrayList<BaseHero> bad){
        randomGood(good);
        randomBad(bad);
    }

    public String getName(){
        return Names.values()[new Random().nextInt(Names.values().length)].toString();
    }

    public void unitSort() {
        for (BaseHero human : unitedList) {
            human.lifeChecker();
        }
        Collections.sort(
                unitedList,
                new Comparator<BaseHero>() {
                    public int compare(BaseHero t0, BaseHero t1) {
                        if (t0.getSpeed() > t1.getSpeed())
                            return -1;
                        if (t0.getSpeed() < t1.getSpeed())
                            return 1;
                        if(t0.getSpeed() == t1.getSpeed()){
                            return (int)t1.getHp() - (int)t0.getHp();
                        }
                        return 0;
                    }
                });
    }

    private void randomBad(ArrayList<BaseHero> bad) {
        for (int i = 0; i <= teamSize; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    bad.add(new Crossbow(getName(),  10, i + 1));
                    break;
                case 1:
                    bad.add(new Thug(getName(), 10, i + 1));
                    break;
                case 2:
                    bad.add(new Warlock(getName(), 10 , i + 1));
                    break;
                default:
                    bad.add(new Feeder(getName(), 10, i + 1));
            }
        }
    }

    private void randomGood(ArrayList<BaseHero> good) {
        for (int i = 0; i <= teamSize; i++) {
            switch (new Random().nextInt(4)) {
                case 0:
                    good.add(new Archer(getName(), 1, i + 1));
                    break;
                case 1:
                    good.add(new Knight(getName(), 1, i + 1));
                    break;
                case 2:
                    good.add(new Monk(getName(), 1, i + 1));
                    break;
                default:
                    good.add(new Feeder(getName(), 1, i + 1));
            }
        }
    }

}