/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author LENOVO
 */
public class NoMoveInY implements MovementInY{
     
    @Override
    public int returnY(int yNew, int y){
       return y;
    }
}
