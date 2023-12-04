/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author maria
 */
public class UpdateScore implements Observer{

    private Observable subject;
    
    
    public UpdateScore(Observable CircusOfPlates) {
        this.subject = CircusOfPlates;
        subject.registerObserver(this);
    }

    
    @Override
    public void update(String s) {
        int score = ((CircusOfPlates)subject).getScore();
        if(s.equals("Bomb")){
            ((CircusOfPlates)subject).setScore(score - 1);
        }
        else if(s.equals("Yellow")){
            ((CircusOfPlates)subject).setScore(score + 2);
        }
        else{
            ((CircusOfPlates)subject).setScore(score + 1);
        }
    }
    
    
    
}
