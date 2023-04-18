package com.mygdx.game.IWantToPlayAGame.units;

import java.util.ArrayList;
import java.util.Random;

public abstract class Wizard extends BaseHero {
    int magic, maxMagic, trick, heal, power;

    public Wizard(float hp, String name, Position position, int x, int y, int attack, Random defeat, int[] damage, Random accuracy,
            int speed, int magic, int maxMagic, int trick, int heal, int power, String state) {

        super(hp, name, position, x, y, attack, defeat, damage, accuracy, speed, state);
        this.magic = magic;
        this.maxMagic = maxMagic;
        this.trick = trick;
        this.heal = heal;
        this.power = power;
    }

    private void heal(BaseHero target){
        if (this.accuracy != 0){
            target.hp += heal;
            magic -= power;
        } else return; 
    }

    @Override
    public void step(ArrayList<BaseHero> crew, ArrayList<BaseHero> enemy) {
        if (hp <= 0) {return;}
        
        if (magic <= 0){
            magic = maxMagic;
            return;}

        if (!timeToFight(crew)) {
            BaseHero patient = findSick(crew);
            if (patient == null) {return;}
            heal(patient);
        } else {
            BaseHero target = findTarget(enemy);
            hit(target);
        }
    }

    private BaseHero findSick(ArrayList<BaseHero> crew) {
        int minHp = (int)maxHp;
        BaseHero target = null;
        for (int i = 0; i < crew.size(); i++) {
            if (crew.get(i).hp > 0 && crew.get(i).hp < minHp) {
                minHp = (int)crew.get(i).getHp();
                target = crew.get(i);
            }
        }
        return target;
    }

    private void hit(BaseHero target){
        if (this.accuracy != 0){
            target.hp -= attack;
            magic -= power;
        } else return; 
    }

}
