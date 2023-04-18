package com.mygdx.game.IWantToPlayAGame.units;

import com.mygdx.game.IWantToPlayAGame.AnsiColors;

import java.util.ArrayList;
import java.util.Random;


public abstract class BaseHero implements GameInterface{
    
    protected float hp, maxHp;
    public String name;
    public Position position;
    public int x, y;
    protected int attack;
    protected int defeat;
    protected int[] damage;
    protected int accuracy;
    protected int speed;
    public String state;

    public BaseHero(float hp, String name, Position position, int x, int y, int attack, 
                    Random defeat, int[] damage, Random accuracy, int speed, String state){
        this.hp = this.maxHp = hp;
        this.name = name;
        this.position = new Position(x, y);
        this.attack = attack;
        this.defeat = new Random().nextInt(2);
        this.damage = damage;
        this.accuracy = new Random().nextInt(2);
        this.speed = speed;
        this.state = state;
    }

    public int[] getCoords() {return new int[]{position.x, position.y};}
    public int getSpeed() {return speed;}
    public float getHp() {return hp;}
    public String getState() {return state;}
    public void lifeChecker(){
        if (hp <= 0){
            state = "Dead";
            speed = 0;
        }
    }

    protected BaseHero findTarget(ArrayList<BaseHero> enemy){
        double dist = 1000.0f; 
        BaseHero target = null;
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i).position.distance(this.position) < dist && enemy.get(i).hp > 0) {
                dist = enemy.get(i).position.distance(this.position);
                target = enemy.get(i);
            }
        }
        return target;
    }

    @Override
    public String toString() {
        if (hp <= 0) {return AnsiColors.DEAD;
        }else return this.name + "  " + AnsiColors.CROSS + "  Здоровье: " + (int)this.hp;
    }

    protected int activeUnits(ArrayList<BaseHero> crew){
        int counter = 0;
        for (BaseHero human : crew) {
            if(human.speed > 2) counter++;
        }
        return counter;
    }

    protected Boolean timeToFight(ArrayList<BaseHero> crew){
        for (BaseHero human : crew) {
            human.lifeChecker();
        }
        Boolean flag = false;
        int counter = activeUnits(crew);
        if (counter == 0) flag = true;
        return flag;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
