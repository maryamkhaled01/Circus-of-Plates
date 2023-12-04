/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.MovementInY;
import controller.MovementInX;

/**
 *
 * @author Admin
 */
public class BallObject extends ImageObject {
    
    public BallObject(int x, int y, String path, MovementInY yMovement, MovementInX xMovement) {
        super(x, y, path, yMovement, xMovement);
    }
    
}
