/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.MoveInX;
import controller.MoveInY;

/**
 *
 * @author Admin
 */
public class ShapeFactory {
    private static ShapeFactory factory;
    private ShapeFactory(){}
    public static synchronized ShapeFactory createFactory(){
        if(factory == null){
            factory = new ShapeFactory();
        }
        return factory;
    }
    public ImageObject getShape(String S,int width,int height) 
    {
        ImageObject shape = null;
        String[] str = S.split("_");
        if(S.equals(""))
           return null;
            if(str[0].equalsIgnoreCase("plate"))
            {
                shape = new PlateObject((int) (Math.random() *width ),(int) (Math.random() * height/4),S, new MoveInY(), new MoveInX());
            }
            else if(str[0].equalsIgnoreCase("cube"))
            {
                shape = new CubeObject ((int) (Math.random() *width ),(int) (Math.random() * height/4),S, new MoveInY(), new MoveInX());
                
            }
            else if (str[0].equalsIgnoreCase("ball"))
            {
                shape = new BallObject ((int) (Math.random() *width ),(int) (Math.random() * height/4),S, new MoveInY(), new MoveInX());
            }
            else if(str[0].equalsIgnoreCase("bomb"))
            {
                shape= new BombObject ((int) (Math.random() *width ),(int) (Math.random() * height/4),S, new MoveInY(), new MoveInX());
            }
        return shape;
    }
}
