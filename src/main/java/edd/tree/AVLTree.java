package edd.tree;

/*

 The height of an AVL tree is always O(Logn) where n is the number of nodes in the tree

*/
public class AVLTree {

Node root;

int height(Node node){
    if(node == null){
        return 0;
    }
    return node.height;
}

int max(int a, int b){
    return (a > b? a : b);
}







/*

Insertion

Steps to follow for insertion
Let the newly inserted node be w
1) Perform standard BST insert for w.
2) Starting from w, travel up and find the first unbalanced node. Let z be the first unbalanced node, y be the child of z that comes on the path from w to z and x be the grandchild of z that comes on the path from w to z.
3) Re-balance the tree by performing appropriate rotations on the subtree rooted with z. There can be 4 possible cases that needs to be handled as x, y and z can be arranged in 4 ways. Following are the possible 4 arrangements:
a) y is left child of z and x is left child of y (Left Left Case)
b) y is left child of z and x is right child of y (Left Right Case)
c) y is right child of z and x is right child of y (Right Right Case)
d) y is right child of z and x is left child of y (Right Left Case)
*/

/*
What is an unbalanced node?


 */

/*
 T1, T2 and T3 are subtrees of the tree
rooted with y (on the left side) or x (on
the right side)
     y                               x
    / \     Right Rotation          /  \
   x   T3   - - - - - - - >        T1   y
  / \       < - - - - - - -            / \
 T1  T2     Left Rotation            T2  T3
Keys in both of the above trees follow the
following order
 keys(T1) < key(x) < keys(T2) < key(y) < keys(T3)
So BST property is not violated anywhere.
 */

    Node rightRotate(Node y){
        Node x = y.left;
        Node T2 = x.right;
        //Perform rotation
        x.right = y.left;
        y.left = T2;

        //Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        //return new root
        return x;
    }

    Node leftRotate(Node x){
        Node y = x.right;
        Node T2 = y.left;

        //Perform Rotation
        y.left = x;
        x.right = T2;

        //Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        //Return new root
        return y;
    }

    int getBalance(Node node){
        if(node == null){
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    Node insert(Node node, int key){
        if(node == null){
            return (new Node(key));
        }

        /* 1.  Perform the normal BST insertion */
        if(key < node.key){
            node.left = insert(node.left, key);
        }else if(key > node.key){
            node.right = insert(node.right, key);
        }else{
            return node;
        }

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;


    }


}


