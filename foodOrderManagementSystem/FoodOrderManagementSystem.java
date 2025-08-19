/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


//priortize & assign orders
//class order ->
//class OrderManagementSystem -> assignOrder() -> 
  //priority depends on -> food type, special delivery
//interface OrderPriortization -> assignOrder(), getNextOrder() -> returns first item on priority list
//class SwiggyOrderPriorization  implements OrderPriortization 

enum TypeOfFood{
  FAST_FOOD(1);
  
  private final int value;

  private TypeOfFood(int value){
    this.value = value;
  }
  
  public int getValue(){
    return value;
  }

}

enum TypeOfDelivery{

  SPECIAL_DELIVERY(1),
  STANDARD_DELIVERY(2);
  
  private final int value;

  private TypeOfDelivery(int value){
    this.value = value;
  }
  
  public int getValue(){
    return value;
  }
}

class Item{
  String name;
}
class Order{

  List<Item> items;
  TypeOfFood typeOfFood;
  TypeOfDelivery typeOfDelivery;
  double amount;
  
  //constructors 
  //getters and setters
}

class SwiggyOrderPrioritization{
   PriorityQueue<Order> orderHeap; 
  public SwiggyOrderPrioritization(){
    orderHeap = new PriorityQueue<>((a,b) -> 
      { 
        if (a.typeOfDelivery.getValue() == b.typeOfDelivery.getValue()){
          return a.typeOfFood.getValue() - b.typeOfFood.getValue();
        }
        
        return a.typeOfDelivery.getValue() - b.typeOfDelivery.getValue();
    
    });

  }
  public void priortizeOrders(List<Order> orders){
    //edge case
    //if list is null, return empty list
    //build the heap by traversing orders
    for (Order order : orders){
      orderHeap.add(order);
    }
    


  
  //build the heap by traversing orders
  //traverse the heap
    //save orders to output list
  /*   Comparator<Order> comp = new Comparator<Order>(){

    @Override
    public int compare(Order order1, Order order2){
        int priority = Integer.compare(order1.typeOfDelivery.getValue(), order2.typeOfDelivery.getValue());
        if (priority == 0){
          priority = Integer.compare(order1.typeOfFood.getValue(), order2.typeOfFood.getValue());
        }
        
        return priority;
    }
  }; 
  Collections.sort(orders, comp);*/
  //return output list
}
  

  public Order getNextOrder(){
    //if output list is empty, return null, exception
    if(!orderHeap.isEmpty()){
      return orderHeap.poll();
    }
    return null;
    
  }

}



class Solution {
  public static void main(String[] args) {
   
  }
}
