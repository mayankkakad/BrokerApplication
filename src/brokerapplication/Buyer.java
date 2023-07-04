/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokerapplication;

/**
 *
 * @author Mayank Kakad
 */
public class Buyer {
    String code,name;
    int quantity;
    String place;
    public Buyer(String code,String name,int quantity)
    {
        this.code=code;
        this.name=name;
        this.quantity=quantity;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
}
