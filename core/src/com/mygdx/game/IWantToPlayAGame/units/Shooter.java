package com.mygdx.game.IWantToPlayAGame.units;

import java.util.ArrayList;
import java.util.Random;


public abstract class Shooter extends BaseHero{

    int arrows, maxArrows;
    int poisonedArrow;

    public Shooter(float hp, String name, Position position, int x, int y, int attack, Random defeat, int[] damage, Random accuracy,
            int speed, int arrows, int maxArrows, int poisonedArrow, String state) {

        super(hp, name, position, x, y, attack, defeat, damage, accuracy, speed, state);
        this.arrows = arrows;
        this.maxArrows = maxArrows;
        this.poisonedArrow = poisonedArrow;
    }

    private Boolean findFeeder(ArrayList<BaseHero> units){
        Boolean flag = false;
        for(int i = 0; i < units.size(); i++){
            if(units.get(i).speed == 1 && units.get(i).state.equals("stand")){
                flag = true;
            }
        }
        return flag;
    }  

    private void hit(BaseHero target, ArrayList<BaseHero> crew){
        if (this.accuracy != 0){
            if(!findFeeder(crew)) this.arrows -= 1;
            target.hp -= attack; 
        } else return; 
    }

    @Override
    public void step(ArrayList<BaseHero> crew, ArrayList<BaseHero> enemy){
        if ((arrows == 0) || (hp <= 0)){return;}
        BaseHero target = findTarget(enemy);
        hit(target, crew);
    }
    
}
