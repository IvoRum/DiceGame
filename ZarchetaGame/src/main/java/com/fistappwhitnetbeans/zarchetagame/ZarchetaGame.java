/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fistappwhitnetbeans.zarchetagame;

/**
 *
 * @author ivail
 */
public class ZarchetaGame {

    public static void main(String[] args) {
        DiceGame game1=new DiceGame(4,3);
        game1.playGame();
        System.out.println(game1.getDiceData());
    }
}
