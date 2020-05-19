package edd.tree;


/*
A binary tree is a recursive data structure where each node can have 2 children at most.
A common type of binary tree is a binary search tree, in which every node has a value that is greater than
or equal to the node values in the left sub-tree, and less than or equal to the node values in the right sub-tree.
Here's a quick visual representation of this type of binary tree:

 */
public class BinaryTree {
    private Node root;

    // letters numbers and two methods below just for testing purposes with Jison
    char[] letters ={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    public int getNumberByLetter(char letter){
        for (int i = 0; i < letters.length; i++){
            if(letter == letters[i]){
                return numbers[i];
            }
        }
        return -1;
    }
    int [][][] a = new int[1][1][1] ;



    public char getLetterByNumber(int number){
        return 'a';
    }

    /*
    if the new node's value is lower than the current node's, we go to the left child
    if the new node's value is greater than the current node's, we go to the right child
    when the current node is null, we've reached a leaf node and we can insert the new node in that position
    */
    public void add(int value){
        setRoot(addRecursive(getRoot(), value));
    }
    public boolean containsNode(int value){
        return containsNodeRecursive(getRoot(), value);
    }
    public void delete(int value){
        setRoot(deleteRecursive(getRoot(), value));
    }



    private Node addRecursive(Node current, int value){
        if(current == null){
            return new Node(value);
        }
        if(value < current.key){
            current.left = addRecursive(current.left, value);
        }else if(value > current.key){
            current.right = addRecursive(current.right, value);
        }else{// value already exists
            return current;
        }
        return current;
    }

    private boolean containsNodeRecursive(Node current, int value){
        if(current == null){
            return false;
        }
        if (value == current.key){
            return true;
        }
        return value < current.key ? containsNodeRecursive(current.left, value) : containsNodeRecursive(current.right, value);
    }

    private Node deleteRecursive(Node current, int value){
        if(current == null){
            return null;
        }
        if(value == current.key){
            if(current.right == null && current.left == null){
                return null;
            }
            if(current.right == null){
                return current.left;
            }
            if(current.left == null){
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.key = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.key){
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root){
        return root.left == null ? root.key : findSmallestValue(root.left);
    }

    public void traverseInOrder(Node node){
        if(node != null){
            traverseInOrder(node.left);
            System.out.println(" " + node.key);
            traverseInOrder(node.right);
        }
    }

    public void traversePreOrder(Node node){
        if(node != null){
            System.out.println(" " + node.key);
            traverseInOrder(node.left);
            traverseInOrder(node.right);
        }
    }


    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.key);
        }
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
