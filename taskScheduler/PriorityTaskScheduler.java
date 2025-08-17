/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

import javax.print.attribute.SetOfIntegerSyntax;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
 
interface TaskScheduler{

  public List<String> scheduleTasks(List<Task> tasks, int timeReached);
}


  //Execption handling for null executionTime & null priority
  // if priority of 2 tasks is same, pick task with lower executionTime
  //if priority & executionTime are same, pick any available
  //print idle if no task available to choose
class PriorityTaskScheduler implements TaskScheduler{

  public List<String> scheduleTasks(List<Task> tasks, int timeReached){
    //if tasks list is empty, return empty list, throw exception
    List<String> taskNames = new ArrayList<>();
    int currentTime = 0;
    

    PriorityQueue<Task> readyQueue = new PriorityQueue<>(
      (a,b) -> {
        if (a.getPriority() == b.getPriority()){
          return a.executionTime - b.executionTime; //min heap
        } 
        return b.getPriority() - a.getPriority();
      }
    );
    //priority queue, sort it by priority (max heap)
    //if priority is same, sort by execution time (min heap)
    //build readyQueue

    //[[A, 5, 10, 30], [B, 4, 10, 10]]
    PriorityQueue<Task> cooldownQueue = new PriorityQueue<>(
      (a,b) -> {
        if( a.available == b.available){
          if (a.getPriority() == b.getPriority()){
            return a.executionTime - b.executionTime;
          }
          return b.getPriority() - a.getPriority();
        }
        return a.available - b.available;
      }
    );
    for (Task task : tasks){
      readyQueue.add(task);
    }


    //cooldownQueue 
    //Queue<Task> cooldownQueue = new LinkedList<>();

    while(currentTime < timeReached){
  
      while(!cooldownQueue.isEmpty() && cooldownQueue.peek().available <= currentTime){
        readyQueue.add(cooldownQueue.poll());
      }

    //[[A, 5, 10, 30], [B, 4, 10, 35]]
      if(!readyQueue.isEmpty()){
        Task current = readyQueue.poll(); //B
        taskNames.add(current.getName());
        currentTime = currentTime + current.executionTime;
        //current task would become available again after cooldown
        current.available = currentTime  + current.cooldown;  //  45
        cooldownQueue.add(current);
        
      } else{ 
        taskNames.add("idle");
        currentTime++;
      }

    }
    return taskNames;
  }
}

class Task{
  String name;
  int priority;
  int executionTime;
  int cooldown;
  int available;

  //constructors
  //getters and setters

  public int getPriority(){
    return priority;
  }
  public String getName(){
    return name;
  }
  public int getCooldown(){
    return cooldown;
  }
  public int getAvailableTime(){
    return available;
  }

  public void decrementAvailableTime(){
    available--;
  }
  public void setAvailableTime(){
    this.available = 0;
  }

}

class TaskManager{

}

class Solution {
  public static void main(String[] args) throws Exception {
   
  }

}


//Task Scheduler - pick highest priority task if cooldown over 
//task - executionTime, cooldown, name, priority 
//input : List<Task> tasks = [taskA, taskB, taskC]
//output : List<String> taskNames = []

//interface TaskScheduler
//PriorityTaskScheduler 
  //scheduleTasks(List<Task>)

//Edge cases 
  //if tasks list is empty, return empty list, throw exception
  //Execption handling for null executionTime & null priority
  
//Requirements
  // if priority of 2 tasks is same, pick task with lower executionTime
  //if priority & executionTime are same, pick any available
  //print idle if no task available to choose

