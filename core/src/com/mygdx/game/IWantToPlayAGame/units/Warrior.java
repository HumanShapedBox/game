package com.mygdx.game.IWantToPlayAGame.units;

import java.util.ArrayList;
import java.util.Random;

public abstract class Warrior extends BaseHero{

    int combo;

    public Warrior(float hp, String name, Position position, int x, int y, int attack, Random defeat, int[] damage, Random accuracy,
            int speed, int combo, String state) {
    
        super(hp, name, position, x, y, attack, defeat, damage, accuracy, speed, state);
        
        this.combo = combo;
    }

    private Boolean stop(Position target){
        Boolean flag = false;
        if(position.distance(target) < 2) {flag = true;}
        return flag;
    }

    private Boolean crewChecker(Position unit, ArrayList<BaseHero> myCrew){
        Boolean flag = false;
        for (BaseHero homie : myCrew) {
            if(homie.position.equals(unit)) flag = true;
        }
        return flag;
    }

    private void move(Position target, ArrayList<BaseHero> myCrew) {
        if (stop(target)) {return;}

        int diffX = target.x - position.x;
        int diffY = target.y - position.y;

        if (Math.abs(diffX) > Math.abs(diffY)) {
            position.x += (int) Math.signum(diffX);
            if (crewChecker(position, myCrew))
                position.y += 1;
        } else {
            position.y += (int) Math.signum(diffY);
            if (crewChecker(position, myCrew))
                position.x += 1;
        }
    }

    private void hit(BaseHero target) {
        if (this.accuracy != 0 && this.position.distance(target.position) < 2) 
            target.hp -= attack;
        else return;
    }

    @Override
    public void step(ArrayList<BaseHero> crew, ArrayList<BaseHero> enemy){
        if (hp <= 0){return;}
        BaseHero target = findTarget(enemy);
        Position targetPosition = target.position;
        move(targetPosition, crew);
        hit(target);
    }

}
