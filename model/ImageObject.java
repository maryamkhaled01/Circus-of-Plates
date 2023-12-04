/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.MovementInY;
import controller.MovementInX;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author LENOVO
 */
public class ImageObject implements GameObject {

    private BufferedImage[] spriteImages;
    private int x;
    private int y;
    private boolean visible;
    private int type;
    private MovementInY inY;
    private MovementInX inX;
    private static final int max = 1;
    private String path;
   
    
    public ImageObject(int x, int y, String path, MovementInY yMovement, MovementInX xMovement) {
        this.x = x;
        this.y = y;
        this.path=path;
        this.spriteImages = new BufferedImage[max];
        this.visible = true;
        this.inY = yMovement;
        this.inX = xMovement;
        try {
            spriteImages[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
     @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int xNew) {
        this.x = inX.returnX(xNew, this.x);
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int yNew) {
        this.y = inY.returnY(yNew, this.y);
        
    }

    
    public void setMovementY(MovementInY y){
        this.inY = y;
    }
    
    public void setMovementX(MovementInX x){
        this.inX = x;
    }
    
    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }
    
}
