package com.mygdx.game.IWantToPlayAGame.units;

import java.util.ArrayList;
import java.util.Random;

public abstract class Peasant extends BaseHero {

    boolean berserk;

    public Peasant(float hp, String name, Position position, int x, int y, int attack, Random defeat, int[] damage, Random accuracy,
    int speed, boolean berserk, String state) {
        super(hp, name, position, x, y, attack, defeat, damage, accuracy, speed, state);

        this.berserk = berserk;
    }

    public Boolean berserkMode(ArrayList<BaseHero> crew){
        Boolean flag = false;
        int counter = activeUnits(crew);
        if (counter == 0) flag = true;
        return flag;
    }
   
    private void berserkSkills(){
        this.attack = 10;
        this.accuracy = 1;
    }

    @Override
    public void step(ArrayList<BaseHero> crew, ArrayList<BaseHero> enemy) {
        if (hp <= 0) {return;}

        if (!timeToFight(crew)) {return;}
        else {
            berserkSkills();
            BaseHero target = findTarget(enemy);
            Position targetPosition = target.position;
            move(targetPosition, crew);
            hit(target);
        }
    }
    
    private void move(Position target, ArrayList<BaseHero> myCrew) {
        if (stop(target)) {return;}
        
        int diffX = target.x - position.x;
        int diffY = target.y - position.y;

        if (Math.abs(diffX) > Math.abs(diffY)) {
            position.x += (int) Math.signum(diffX) * 2;
        } else {
            position.y += (int) Math.signum(diffY) * 2;
        }
    }

    private Boolean stop(Position target){
        Boolean flag = false;
        if(position.distance(target) < 3) {flag = true;}
        return flag;
    }

    private void hit(BaseHero target) {
        if (this.position.distance(target.position) < 3) 
            target.hp -= attack;
        else return;
    }
}
