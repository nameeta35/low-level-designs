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

class Node<K, V>{
      K key;
      V val;
      Node<K,V> prev;
      Node<K,V>  next;
      public Node(K key, V val){
        this.key = key;
        this.val = val;
      }
}
class LRUCache<K, V> {
  
  Map<K, Node<K,V>> map;
  Node<K,V> head;
  Node<K,V> tail;
  int capacity;

  public LRUCache(int capacity){
    this.map = new HashMap<>();
    this.head = new Node<K,V>(null, null);
    this.tail = new Node<K,V>(null, null);
    head.next = tail;
    tail.prev = head;
    this.capacity = capacity;
  }

  public V getKey(K key){
    if (map.containsKey(key)){
      Node<K,V> node = map.get(key);
      removeNode(node);
      addNode(node);
      return node.val;
    } else {
      return null;
    }
  }

  public void put(K key, V val){
    if (map.containsKey(key)){
      Node<K,V> node = map.get(key);
      removeNode(node);
    }
    Node<K,V> newNode = new Node<K,V>(key, val);
    addNode(newNode);
    if (map.size() >= capacity){
      Node<K,V> nodeToEvict = head.next;
      removeNode(nodeToEvict);
      map.remove(nodeToEvict.key);
    }
    System.out.println("Adding key" + key);
    map.put(key, newNode);
    
  }

//head -> 1 -> 2 -> 3 -> tail
  public void removeNode(Node<K,V> node){
    Node<K,V> prev = node.prev;
    Node<K,V> next = node.next;
    prev.next = next;
    next.prev = prev;
  }

  //head -> 1 -> 2 -> 3 -> -> 4 ->tail
  public void addNode(Node<K,V> node){
    Node<K,V> prev = tail.prev; //3
    prev.next = node;  // 3.next = 4
    node.prev = prev; // 4.prev = 3
    node.next = tail;
    tail.prev = node;
  }
    
}
class Solution {
  public static void main(String[] args) {
    LRUCache<Integer, Integer> cache = new LRUCache<>(3);
    cache.put(1,1);
    cache.put(2,2);
    cache.put(3,3);
    cache.put(4,4);
    System.out.println(cache.getKey(1));
  }
}
