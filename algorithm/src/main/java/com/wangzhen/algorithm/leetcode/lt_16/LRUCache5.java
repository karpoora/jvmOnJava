package com.wangzhen.algorithm.leetcode.lt_16;

import java.util.HashMap;


/**
 * Description: LRU实现 map+双向列表
 * Datetime:    2020/9/28   8:47 上午
 * Author:   王震
 *
 * ["LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
 * [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
 */
public class LRUCache5 {
    HashMap<Integer,Node> map;
    Node head; // dummy
    Node tail; // dummy
    int capacity;
    public LRUCache5(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.prev=null;
        head.next=tail;
        tail.next=null;
        tail.prev=head;

    }

    public int get(int key) {
        // 如果不存在key 那么返回 -1
        if(!map.containsKey(key)){
            return -1;
        }
        // 删除这个在列表中的节点
        Node<Integer,Integer> x = map.get(key);
        x.prev.next=x.next;
        x.next.prev=x.prev;
        moveToTail(x);
        return x.value;
    }


    public void put(int key, int value) {
        // 表示存在这个节点 会把这个节点移动到最后
        if(get(key)!=-1){
            tail.prev.value=value;
            return;
        }
        // 不存在这个节点需要新建节点
        Node newNode = new Node(key,value);
        // 如果空间已经满了 移除第一个节点
        if(map.size()==capacity){
            map.remove(head.next.key);
            Node next = head.next;
            head.next=next.next;
            next.next=head.prev;
        }
        map.put(key,newNode);
        moveToTail(newNode);

    }

    // 移动节点 到 列表的结尾
    public void moveToTail(Node node){
        tail.prev.next=node;
        node.prev=tail.prev;
        node.next=tail;
        tail.prev=node;
    }

    class Node<K,V>{
        K key;
        V value;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache5 cache = new LRUCache5( 2 /* 缓存容量 */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2)); // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1)); // 返回 -1 (未找到)
        System.out.println(cache.get(3));// 返回  3
        System.out.println(cache.get(4));  // 返回  4
    }

}
