/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.CircusOfPlates;
import controller.IStrategy;

/**
 *
 * @author LENOVO
 */
public class Main {
    
   public  Main(IStrategy s){
        GameController gameController = new GameController(() -> new CircusOfPlates(800, 600, s));
        gameController.start();
    }
}
