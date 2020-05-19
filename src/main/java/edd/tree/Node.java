package edd.tree;

public class Node {
    int key, height;
    Node left;
    Node right;

    Node(int key){
        this.key = key;
        right = null;
        left = null;
    }


    Node(int key, int height){
        this.key = key;
        right = null;
        left = null;
        this.height = height;
    }

}
