/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.XMoveClown;
import controller.NoMoveInY;
import controller.MovementInY;
import controller.MovementInX;

/**
 *
 * @author LENOVO
 */
public class ClownObject extends ImageObject{
    private static ClownObject clown;
    private ClownObject(int x, int y, String imageName, MovementInY yMovement, MovementInX xMovement) {
        super(x, y, imageName, yMovement, xMovement);
    }
    
    public static synchronized ClownObject getClown(int width, int height){
        if(clown == null){
            clown = new  ClownObject(width/ 3, (int) (height * 0.65), "robot.png", new NoMoveInY(), new XMoveClown());
        }
        return clown;
    }
}
