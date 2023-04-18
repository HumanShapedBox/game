package com.mygdx.game.IWantToPlayAGame;

import com.mygdx.game.IWantToPlayAGame.unt.Game;

import java.util.Scanner;


public class Simulation {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Game game = new Game();
        View view = new View(game);
        game.start();
        while (true) {
            game.unitSort();
            game.nextMove();
            view.view();
            if(game.endGame(game.good) || game.endGame(game.bad)){
                game.itsAlive(game.unitedList);
                view.view();
                System.out.println("Thanks guys, see you next time!");
                return;
            }
            Simulation.sc.nextLine();
        }
    }
}
