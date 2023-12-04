/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maria
 */
public class XMoveLimitedLeft implements MovementInX {

    @Override
    public int returnX(int xNew, int x) {
        if(xNew > 540 || xNew < 5)
            return x;
        else 
            return xNew;
    }  
}
