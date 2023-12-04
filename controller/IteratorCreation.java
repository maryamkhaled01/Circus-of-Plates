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
public class IteratorCreation implements CreateIterator{

    @Override
    public Iterator createIterator(List<GameObject> list) {
        return new ObjectIterator(list);
    }
    
}
