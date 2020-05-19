package edd.tree;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class BTree{

    private static  int M; // Degree of the tree

    private Node root;       // root of the B-tree
    private int height;      // height of the B-tree //TODO
    private int n;           // number of key-value pairs in the B-tree  TODO
    private int treeNumber = 0;
    private StringBuilder declaration;
    private StringBuilder arrows;


    // helper B-tree node data type
    private static final class Node {
        private int m;
        public Book[] interiorNodes;// = new Book[M -1];
        public Node[] children;// = new Node[M];
        private boolean leaf;
        private int number;


        private Node(int k, int treeNumber) {
            m = k;
            interiorNodes = new Book[k -1];
            children = new Node[k];
            number = treeNumber;
            leaf = true;
        }
        public boolean isLeaf(){return leaf;}
        private void setLeaf(boolean leaf){this.leaf = leaf;}
        public int getInteriorNodesSize(){
            int counter = 0;
            for (int i = 0; i < interiorNodes.length;i++){
                if (interiorNodes[i] != null){
                    counter++;
                }
            }
            return counter;
        }

        public boolean isFull(){
            if(getInteriorNodesSize() == M - 1){
                return true;
            }
            return false;
        }
        public void addEntry(Book book){
            int index = 0;
            for(int i = 0; i < interiorNodes.length; i ++){
                if(interiorNodes[i] == null || compare(interiorNodes[i], book) > 0){
                    break;
                }
                index++;
            }
            Book temp = interiorNodes[index];
            interiorNodes[index] = book;
            for(int i = index + 1; i < interiorNodes.length; i++){
                Book temp2 = temp;
                temp = interiorNodes[i];
                interiorNodes[i] = temp2;

            }

        }

        public void setGraph(StringBuilder nodeText, StringBuilder connectionText){
            nodeText.append( "_" + number + " [\n");
            nodeText.append(         "   shape=plaintext\n");
            nodeText.append("   label=<\n");
            nodeText.append("     <table border='1' cellborder='1'>\n<tr>");
            for(int i = 0; i < children.length; i++){
                if(children[i] !=null){
                    nodeText.append("<td port='port_" + i +"'></td>");
                }


                if(i < interiorNodes.length){
                    if(interiorNodes[i] != null){
                        nodeText.append("<td>" +   interiorNodes[i].getISBN() +   "</td>");
                    }
                }
                if(children[i] != null){
                    connectionText.append( "_" + number + ":port_" + i + " -> _" +  children[i].number + ";\n");
                }

            }
            nodeText.append("</tr>\n     </table>\n");
            nodeText.append("  >];\n");


            for (int i =0; i <  children.length; i++){
                if(children[i] != null) {
                    children[i].setGraph(nodeText, connectionText);
                }
            }


        }

        public  void printItSelf(){
            System.out.println("Tree number: " + number + ".\nInterior Nodes: ");
            for (int i =0; i <  interiorNodes.length; i++){
                if(interiorNodes[i] != null) {
                    System.out.print(interiorNodes[i].getISBN() + " ");
                }
            }
            System.out.println("\nChildren: ");
            for (int i =0; i <  children.length; i++){
                if(children[i] != null) {
                    System.out.print(children[i].number + " ");
                }
            }
            System.out.println("\n");


        }

        public void print(){


            printItSelf();

            for (int i =0; i <  children.length; i++){
                if(children[i] != null) {
                    children[i].print();
                }
            }
        }

        public void deleteBookFromLeaf(String bookName){
            if(getInteriorNodesSize() <= Math.ceil(M/2) ){
                throw new IllegalCallerException("Can't Delete leaf with less than M/2 keys");
            }
            for (int i = 0; i < interiorNodes.length; i++){
                if(interiorNodes[i].getISBN().compareTo(bookName) == 0){
                    interiorNodes[i] = null;
                    Book[] aux = new Book[interiorNodes.length];
                    for(int j=0;j < interiorNodes.length;++j){
                        aux[j]=interiorNodes[j];
                    }
                    for(int j=0;j < aux.length;++j){
                        if(aux[j] != null){
                            pushInterior(aux[j]);
                        }
                    }



                }
            }
        }

        private void pushInterior(Book book){
            int index = 0;
            for(int i = 0; i < interiorNodes.length; i++){
                if(interiorNodes[i] == null){
                    break;
                }
                index++;
            }
            if(index >= interiorNodes.length){
                throw new ArrayIndexOutOfBoundsException("Cant add another book");
            }
            interiorNodes[index] = book;
        }

        private void pushChildren(Node child){
            int index = 0;
            for(int i = 0; i < children.length; i++){
                if(children[i] == null){
                    break;
                }
                index++;
            }
            if(index >= children.length){
                throw new ArrayIndexOutOfBoundsException("Cant add another child");
            }
            children[index] = child;
        }




    }




    public void print(){



        addSupport();
        declaration.delete(0, declaration.length());
        arrows.delete(0, arrows.length());
        root.setGraph(declaration, arrows);
        String dotText = "digraph H {\n" +
                "\n" +
                "  graph[bgcolor=\"#808080\"  center=true  ratio=fill fontsize=20]\n" +
                "  node[fillcolor=dimgray fontcolor=white  style=\"filled, bold\" fontsize=20 shape=record ]\n" +
                declaration.toString() +  arrows.toString() + "\n}";


        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:\\Users\\benav\\Documents\\graph.dot"));
            writer.write(dotText);
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }




        String fileName = "C:\\Users\\benav\\Documents\\graph.jpg";
        String [] commands = {
                "cmd.exe" , "/c", "start" , "\"DummyTitle\"", "\"" + fileName + "\""
        };
        Process p = null;

        /*
        try {
            p = Runtime.getRuntime().exec("dot -Tjpg C:\\Users\\benav\\Documents\\graph.dot - o C:\\Users\\benav\\Documents\\graph.jpg");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec("dot -Tjpg C:\\Users\\benav\\Documents\\graph.dot - o C:\\Users\\benav\\Documents\\graph.jpg");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


        try {
            p = Runtime.getRuntime().exec(commands);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    */

        /*
        try {
            Process pr = rt.exec("C:\\Users\\benav\\Documents\\graph.jpg");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }*/
        //System.out.println(declaration.toString() +  arrows.toString() );
        /*
        System.out.println();
        if(root != null) {
            root.print();
        }


        String text = "digraph D {\n" +
        "  graph[bgcolor=\"#808080\"  center=true  ratio=fill fontsize=20]\n" +
        " node[fillcolor=dimgray fontcolor=white  style=\"filled, bold\" fontsize=20 shape=record]\n";


        text += "}";

        //System.out.println("**************");
        //nodes.forEach(Node::printItSelf);*/
    }



    /**
     * Initializes an empty B-tree.
     */
    public BTree(int degree) {
        this.M = degree;
        height = 1;
        declaration = new StringBuilder();
        arrows = new StringBuilder();



    }

    public BTree() {
        this(5);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }


    public int height() {
        return height;
    } //TODO

    public Book findBook(String bookName){
        Book tempNode = null;

        return tempNode;
    }

    public boolean add(Book book){
        if (book == null) throw new IllegalArgumentException("argument key to put() is null");



        return true;
    }
    private void addSupport(){
        treeNumber = 0;
        height++;
        this.root = new Node(M, treeNumber++);

        Book[] booksTemp = new Book[M];

        for (Book book : booksTemp) {
            Node newRoot = addRecursive(root, book, -1);

            if (newRoot != null) {
                this.root = newRoot;
                height++;
            }

        }


    }

    private Node addRecursive(Node node, Book book, int ht){

        if(node.isLeaf()){
            if(node.isFull()){
                return splitLeaf(node, book);
            }else {
                node.addEntry(book);
                return null;
            }
        }else {
            int childrenPos = 0;
            for (int i = 0; i < node.interiorNodes.length ; i ++){
                if(node.interiorNodes[i] == null || compare( node.interiorNodes[i], book) > 0){
                    break;
                }
                childrenPos++;
            }
            Node returning_node = addRecursive(node.children[childrenPos], book, ht);

            if(returning_node == null){
                return null;
            }else {



                if(node.isFull()){//SPLIT and accomodate node
                    return splitNode(node, returning_node, childrenPos);
                }else{ //accomodate entry on this node
                    node.children[childrenPos] = returning_node.children[0];
                    Node auxNode = new Node(M, -1);
                    //Copying interior nodes
                    for(int i = 0; i < childrenPos ; i++){
                        auxNode.interiorNodes[i] = node.interiorNodes[i];
                    }
                    auxNode.interiorNodes[childrenPos] = returning_node.interiorNodes[0];
                    for(int i = childrenPos + 1; i < node.interiorNodes.length ; i++){
                        auxNode.interiorNodes[i] = node.interiorNodes[i - 1];
                    }



                    for(int i = 0; i <= childrenPos ; i++){
                        auxNode.children[i] = node.children[i];
                    }
                    auxNode.children[childrenPos + 1] = returning_node.children[1];
                    for(int i = childrenPos + 2; i < node.children.length ; i++){
                        auxNode.children[i] = node.children[i - 1];
                    }
                    copyNode(node, auxNode);
                    return null;
                }

            }
        }
    }

    private Node splitNode(Node node, Node insertionNode, int childrenPos){
        Node auxNode = new Node(M + 1, -1);
        node.children[childrenPos] = insertionNode.children[0];
        //Copying interior nodes
        for(int i = 0; i < childrenPos ; i++){
            auxNode.interiorNodes[i] = node.interiorNodes[i];
        }
        auxNode.interiorNodes[childrenPos] = insertionNode.interiorNodes[0];
        for(int i = childrenPos + 1; i < M; i++){
            auxNode.interiorNodes[i] = node.interiorNodes[i - 1];
        }


        for(int i = 0; i <= childrenPos ; i++){
            auxNode.children[i] = node.children[i];
        }
        auxNode.children[childrenPos + 1] = insertionNode.children[1];
        for(int i = childrenPos + 2; i <= node.children.length ; i++){
            auxNode.children[i] = node.children[i - 1];
        }

        int middleIndex = (int) Math.ceil(M/2);

        node.interiorNodes[0] = auxNode.interiorNodes[middleIndex];

        node.children[0] = new Node(M, treeNumber++);
        node.children[1] = new Node(M, treeNumber++);
        node.children[0].setLeaf(false);
        node.children[1].setLeaf(false);

        for(int i = 0; i < middleIndex; i++){
            node.children[0].interiorNodes[i] = auxNode.interiorNodes[i];
        }
        for(int i = 0; i <= middleIndex; i++){
            node.children[0].children[i] = auxNode.children[i];
        }



        for(int i = middleIndex + 1; i < M; i++){
            node.children[1].interiorNodes[i - (middleIndex + 1)] = auxNode.interiorNodes[i];
        }
        for(int i = middleIndex + 1; i <= M; i++){
            node.children[1].children[i - middleIndex-1] = auxNode.children[i];
        }
        node.setLeaf(false);
        return node;
    }

    private void copyNode(Node originalNode, Node newNode){
        for(int i = 0; i < originalNode.interiorNodes.length; i++){
            originalNode.interiorNodes[i] = newNode.interiorNodes[i];
        }
        for(int i = 0; i < originalNode.children.length; i++){
            originalNode.children[i] = newNode.children[i];
        }
    }

    public boolean deleteBook(String bookName){
        //Root should look if any of her children has the key
        return deleteRecursive(root, null, bookName);
    }

    private boolean deleteRecursive(Node parent, Node grandPa, String bookName){
        boolean found = false;
        Book temp = null;

        if (parent != null) {


            int counter = 0;

            for (int i = 0; i < parent.interiorNodes.length; i++) {
                if (parent.interiorNodes[i] == null) {
                    return false;
                } else if (parent.interiorNodes[i].getISBN().compareTo(bookName) > 0) {
                    break;
                } else if (parent.interiorNodes[i].getISBN().compareTo(bookName) == 0) {
                    //TODO por el moment solo funciona para hojas con mas de M/2
                    parent.deleteBookFromLeaf(bookName);
                    return true;
                }
                counter++;
            }
        }
        return found;

    }


    private Node splitLeaf(Node node, Book book){
        Book tempArr[] = new Book[M];

        int i =0;
        for(; i < node.interiorNodes.length; i++){
            if(compare(node.interiorNodes[i], book) > 0){
                tempArr[i] = book;
                break;
            }else{
                tempArr[i] = node.interiorNodes[i];
            }
        }

        if(i == node.interiorNodes.length){
            tempArr[M-1] = book;
        }else {
            i++;
            for(; i < tempArr.length; i++){
                tempArr[i] = node.interiorNodes[i-1];
            }
        }
        int middleIndex = (int) Math.ceil(M/2);



        node.interiorNodes[0] = tempArr[middleIndex];

        node.children[0] = new Node(M, treeNumber++);
        node.children[1] = new Node(M, treeNumber++);


        for( int j = 0; j < middleIndex; j++){
            node.children[0].addEntry(tempArr[j]);
        }

        for( int j = middleIndex + 1; j < tempArr.length; j++){
            if(tempArr[j]== null){

                System.out.println("NULL" + j); throw new NullPointerException();}
            node.children[1].addEntry(tempArr[j]);
        }
        node.setLeaf(false);
        return node;
    }

    private static int compare(@NotNull Book book_1, @NotNull Book book_2){ // Positivo book 1 es mayor, negativo book 1 es menor
        return book_1.getISBN().compareTo(book_2.getISBN());
    }







}





