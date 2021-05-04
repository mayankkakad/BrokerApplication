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
public class Bill {
    String left,right,broker_name,broker_address,bill_title,bill_period;
    public Bill(String left,String right,String broker_name,String broker_address,String bill_title,String bill_period)
    {
        this.left=left;
        this.right=right;
        this.broker_name=broker_name;
        this.broker_address=broker_address;
        this.bill_title=bill_title;
        this.bill_period=bill_period;
    }
}
