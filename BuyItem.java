/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Final;


public class BuyItem 
{
    private double quantity ;
    private int delivery ;
    private double totPrice ;

    public BuyItem() {
    }

   /* public double sumPrice(int size,double add, ArrayList<Double> array)
    {
        for(int i=0 ; i<size ; i++)
         {
             if(i==0)
             {
                add = add + getArl(i);
             }
             else
             add = add + getArl(i) - getArl(i-1);
         }
         
         return add ;
    }*/
    
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }

   
    
}
