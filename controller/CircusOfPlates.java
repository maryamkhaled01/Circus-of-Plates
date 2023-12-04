/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.ShapeFactory;
import model.BombObject;
import model.ClownObject;
import model.ImageObject;

/**
 *
 * @author LENOVO
 */
public class CircusOfPlates implements World, Observable {

    private static int MAX_TIME = 1 * 60 * 1000;

    private List<GameObject> left = new LinkedList<GameObject>();
    private List<GameObject> right = new LinkedList<GameObject>();

    private int speed;
    private static int DEFAULT_CONTROL_SEPEED = 20; 
    private static int NUM_OF_Shapes = 3;
    private static int NUM_OF_MOVING_OBJ = 5;
   
    private String state;
    private long startTime;
    private int score;
    private ArrayList<Observer> observerList = new ArrayList<>();
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
    private int width;
    private int height;

    private List<GameObject> constant;
    private List<GameObject> moving;
    private List<GameObject> control;

    public CircusOfPlates(int width, int height,IStrategy s) {
        this.width = width;
        this.height = height;
        this.speed = 1;
        this.startTime = System.currentTimeMillis();
        this.score = 0;
        this.constant = new LinkedList<>();
        this.moving = new LinkedList<>();
        this.control = new LinkedList<>();
        UpdateScore upToDateScore = new UpdateScore(this);
        createGameObject(s);
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    private void createGameObject(IStrategy s1) {
        //Add moving Objects
        ShapeFactory s = ShapeFactory.createFactory();
        for (int i = 0; i < NUM_OF_MOVING_OBJ; i++) {
            int imgNum1 = (int) (Math.random() * 4 + 1);
            String imagePath = "plate_" + imgNum1 + ".png";
            ImageObject plate=s.getShape(imagePath,width,height);
            moving.add(plate);
            int imgNum2 = (int) (Math.random() * 4 + 1);
            String cubePath ="cube_"+imgNum2+".png";
            ImageObject cube=s.getShape(cubePath, width,height);
            moving.add(cube);
            int imgNum3 = (int) (Math.random() * 4 + 1);
            String ballPath ="ball_"+imgNum3+".png";
            ImageObject ball=s.getShape(ballPath, width,height);
            moving.add(ball);
        }
         for (int i = 0; i <s1.getNum() ; i++)
         { 
           ImageObject bomb =s.getShape("bomb_0.png", width, height);
             moving.add(bomb);
         }
        //Add control objects
        control.add(ClownObject.getClown(width, height));
    }

    private boolean intersect(GameObject o1, GameObject o2) {
       return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth() - 20)
                && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight()-20);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return this.constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return this.moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return this.control;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getControlSpeed() {
        return DEFAULT_CONTROL_SEPEED;
    }

    @Override
    public boolean refresh() {
        state = null;
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        ImageObject clown = (ImageObject) control.get(0);
        //ObjectIterator iterate = moving.
        IteratorCreation c = new IteratorCreation();
        Iterator iterate = c.createIterator(moving);
        while(iterate.hasNext()){
            GameObject m = (GameObject)iterate.next();
            m.setY((m.getY() + speed));//move Down
            if (m.getY() >= getHeight()) {
                // reuse the plate in another position
                m.setY(-1 * (int) (Math.random() * getHeight()));
                m.setX((int) (Math.random() * getWidth()));
            }
            if(!timeout){
            if (left.isEmpty()) {
                if (clownIntersectleft(m)) {
                    if(m instanceof BombObject ){
                    moving.remove(m);
                    setState("Bomb");
                    }
                    else{        
                 moving.remove(m);
                    ((ImageObject) m).setMovementX(new XMoveLimitedLeft());
                   m.setX(clown.getX()-20);
                    ((ImageObject) m).setMovementY(new NoMoveInY());
                    control.add(m);
                    left.add(m);
                }}
              
            }
            else if (intersect(m, left.get(left.size() - 1))) {
                if(m instanceof BombObject ){
                    moving.remove(m);
                    setState("Bomb");
                    }
                else{
                    moving.remove(m);
                    m.setX(clown.getX()-20);
                    ((ImageObject) m).setMovementX(new XMoveLimitedLeft());
                    m.setY(left.get(left.size() - 1).getY() - 20);
                    ((ImageObject) m).setMovementY(new NoMoveInY());
                    control.add(m);
                    left.add(m);
                }}
            if(right.isEmpty()){
                if (clownIntersectRight(m)) {
                    if(m instanceof BombObject ){
                    moving.remove(m);
                    setState("Bomb");
                    }
                    else{
                    moving.remove(m);
                    ((ImageObject) m).setMovementX(new XMoveLimitedRight());
                    m.setX(clown.getX() + 170);
                    ((ImageObject) m).setMovementY(new NoMoveInY());
                    control.add(m);
                    right.add(m);
                }}
            }
            else if (intersect(m, right.get(right.size() - 1))) {
                if(m instanceof BombObject ){
                    moving.remove(m);
                    setState("Bomb");
                    }
                else{
                    moving.remove(m);
                    m.setX(clown.getX()+170);
                    ((ImageObject) m).setMovementX(new XMoveLimitedRight());
                    m.setY(right.get(right.size() - 1).getY() - 20);
                    ((ImageObject) m).setMovementY(new NoMoveInY());
                    control.add(m);
                    right.add(m);
                }}
                if (m.getX() > width || m.getY() > height) {

                }
            update();
            if (left.size() + right.size() == NUM_OF_MOVING_OBJ*NUM_OF_Shapes) {
                return timeout;
            }
        }
        }
        return !timeout;
    }

    @Override
    public String getStatus() {
        if(left.size() + right.size() == NUM_OF_MOVING_OBJ*NUM_OF_Shapes)
            return "Score=" + score + "   |   Time=0";
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    private boolean clownIntersectleft(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
        return (Math.abs(clown.getX() - o.getX()) <= o.getWidth() -40 && o.getY() == clown.getY() -20);
    }
    
    private boolean clownIntersectRight(GameObject o) {
        ImageObject clown = (ImageObject) control.get(0);
      return ((o.getX() - clown.getX() > 0) && (o.getX() - clown.getX()) <= clown.getWidth() && (o.getX() - clown.getX()) > 0.5 *clown.getWidth()  && o.getY() == clown.getY() -20 );
    }

    private void update() {
        if (left.size() >= 3) {
            ImageObject p1 = (ImageObject) left.get(left.size() - 1);
            ImageObject p2 = (ImageObject) left.get(left.size() - 2);
            ImageObject p3 = (ImageObject) left.get(left.size() - 3);
            String S1= p1.getPath();
            String S2= p2.getPath();
            String S3= p3.getPath();
             if (S1.split("_")[1].equals(S2.split("_")[1]) && S2.split("_")[1].equals(S3.split("_")[1]))  {
                left.remove(p1);
                left.remove(p2);
                left.remove(p3);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                moving.add(p1);
                moving.add(p2);
                moving.add(p3);
                p1.setMovementY(new MoveInY());
                p2.setMovementY(new MoveInY());
                p3.setMovementY(new MoveInY());
                p1.setY(-1 * (int) (Math.random() * getHeight()));
                p1.setX((int) (Math.random() * getWidth()));
                p2.setY(-1 * (int) (Math.random() * getHeight()));
                p2.setX((int) (Math.random() * getWidth()));
                p3.setY(-1 * (int) (Math.random() * getHeight()));
                p3.setX((int) (Math.random() * getWidth()));
                if(S1.split("_")[1].equals("4.png")){
                    setState("Yellow");
                }
                else{
                    setState("one");
                }
                    
            }

        }
            if (right.size() >= 3) {
            ImageObject p1 = (ImageObject) right.get(right.size() - 1);
            ImageObject p2 = (ImageObject) right.get(right.size() - 2);
            ImageObject p3 = (ImageObject) right.get(right.size() - 3);
            String S1= p1.getPath();
            String S2= p2.getPath();
            String S3= p3.getPath();
            if (S1.split("_")[1].equals(S2.split("_")[1]) && S2.split("_")[1].equals(S3.split("_")[1])) {
                right.remove(p1);
                right.remove(p2);
                right.remove(p3);
                control.remove(p1);
                control.remove(p2);
                control.remove(p3);
                moving.add(p1);
                moving.add(p2);
                moving.add(p3);
                p1.setMovementY(new MoveInY());
                p2.setMovementY(new MoveInY());
                p3.setMovementY(new MoveInY());
                p1.setY(-1 * (int) (Math.random() * getHeight()));
                p1.setX((int) (Math.random() * getWidth()));
                p2.setY(-1 * (int) (Math.random() * getHeight()));
                p2.setX((int) (Math.random() * getWidth()));
                p3.setY(-1 * (int) (Math.random() * getHeight()));
                p3.setX((int) (Math.random() * getWidth()));
                if(S1.split("_")[1].equals("4.png")){
                    setState("Yellow");
                }
                else{
                    setState("one");
                }
            }
        }
    }
    
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for(int i=0; i < observerList.size(); i++){
            observerList.get(i).update(state);
        }
    } 
}
