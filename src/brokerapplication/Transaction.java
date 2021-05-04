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
public class Transaction {
    String date,time,seller_code,seller_name,buyer_code,buyer_name,item;
    int quantity,rate;
    public Transaction(String date,String time,String seller_code,String seller_name,String buyer_code,String buyer_name,String item,int quantity,int rate)
    {
        this.date=date;
        this.time=time;
        this.seller_code=seller_code;
        this.seller_name=seller_name;
        this.buyer_code=buyer_code;
        this.buyer_name=buyer_name;
        this.item=item;
        this.quantity=quantity;
        this.rate=rate;
    }
}
