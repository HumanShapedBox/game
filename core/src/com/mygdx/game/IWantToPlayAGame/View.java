package com.mygdx.game.IWantToPlayAGame;

import com.mygdx.game.IWantToPlayAGame.units.BaseHero;
import com.mygdx.game.IWantToPlayAGame.unt.Game;

import java.util.Collections;

public class View {
    public Game game;
    public View(Game game){
        this.game = game;
        this.top = formatDiv("a") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-b"))) + formatDiv("-c");
        this.midl = formatDiv("d") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-e"))) + formatDiv("-f");
        this.bottom = formatDiv("g") + String.join("", Collections.nCopies(game.teamSize-1, formatDiv("-h"))) + formatDiv("-i");
    }
    protected int step;
    private final int[] l = {0};
    private final String top;
    private final String midl;
    private final String bottom;
    private void tabSetter(int cnt, int max){
        int dif = max - cnt + 2;
        if (dif>0) System.out.printf("%" + dif + "s", ":"); else System.out.print(":");
    }
    private static String formatDiv(String str) {
        return str.replace('a', '\u250c')
                .replace('b', '\u252c')
                .replace('c', '\u2510')
                .replace('d', '\u251c')
                .replace('e', '\u253c')
                .replace('f', '\u2524')
                .replace('g', '\u2514')
                .replace('h', '\u2534')
                .replace('i', '\u2518')
                .replace('-', '\u2500');
    }
    private String getChar(int x, int y){
        String out = "| ";
        for (BaseHero human: this.game.unitedList) {
            if (human.getCoords()[0] == x && human.getCoords()[1] == y){
                if (human.getHp() == 0) {
                    out = "|" + (AnsiColors.ANSI_RED + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                    break;
                }
                if (this.game.bad.contains(human)) out = "|" + (AnsiColors.ANSI_RED + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                if (this.game.good.contains(human)) out = "|" + (AnsiColors.ANSI_WHITE + human.getInfo().charAt(0) + AnsiColors.ANSI_RESET);
                break;
            }
        }
        return out;
    }
    public void view() {
        if (step == 1 ){
            System.out.print(AnsiColors.ANSI_CYAN + "First step" + AnsiColors.ANSI_RESET);
        } else {
            System.out.print(AnsiColors.ANSI_CYAN + "Step:" + step + AnsiColors.ANSI_RESET);
        }
        step++;
        //Main.sortedList.forEach((v) -> l[0] = Math.max(l[0], v.toString().length()));
        System.out.print("_".repeat(l[0]*2));
        System.out.println("");
        System.out.print(this.top + "    ");
        System.out.print(AnsiColors.HEART + " White Fame ");
        System.out.print(" ".repeat(l[0]-13));
        System.out.println(":\tRed Power" + AnsiColors.SPADE);
        for (int i = 1; i < 11; i++) {
            System.out.print(getChar(1, i));
        }
        System.out.print("|    ");
        System.out.print(this.game.good.get(0));
        tabSetter(this.game.good.get(0).toString().length(), l[0]);
        System.out.println(this.game.bad.get(0));
        System.out.println(this.midl);

        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(getChar(i, j));
            }
            System.out.print("|    ");
            System.out.print(this.game.good.get(i-1));
            tabSetter(this.game.good.get(i-1).toString().length(), l[0]);
            System.out.println(this.game.bad.get(i-1));
            System.out.println(this.midl);
        }
        for (int j = 1; j < 11; j++) {
            System.out.print(getChar(10, j));
        }
        System.out.print("|    ");
        System.out.print(this.game.good.get(9));
        tabSetter(this.game.good.get(9).toString().length(), l[0]);
        System.out.println(this.game.bad.get(9));
        System.out.println(this.bottom);
    }
}