/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author LENOVO
 */
public class XMoveClown implements MovementInX{

    @Override
    public int returnX(int xNew, int x) {
        if(xNew < 10 || xNew > 560)
            return x;
        else 
            return xNew;
    }
    
}
