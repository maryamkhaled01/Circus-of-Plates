/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author maria
 */
public class ObjectIterator implements Iterator<GameObject>{

    List<GameObject> objects;
    int  i = 0;
    
    public ObjectIterator(List<GameObject> list){
        this.objects = list;
    }
    
    @Override
    public boolean hasNext() {
        return i < objects.size();
    }

    @Override
    public GameObject next() {
        GameObject o =  objects.get(i);
        i += 1;
        return o;
    }
    
}
