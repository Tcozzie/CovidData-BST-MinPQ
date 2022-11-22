/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package covid;

import java.io.FileWriter;
import java.io.IOException;



/**
 * @author tiegancozzie
 * 
 *Takes Nodes given from the MinPQ and stores them inside a BST
 * this this BST will have all the data from the whole file from least to greatest
 * 
 * @param Key Value
 * @return N/A
 * @throws N/A
 */
public class BST<Key extends Comparable<Key>,Value> {
    
        private String finalStr=""; // makes the final string for printing
        private int count=0; // count how many values are in the BST
    
        class Node{
        private Key key;
        private Value val;
        private Node left;
        private Node right;


        
        
        public Node(Key input,Value print){ // initializes the first node in the stack
            this.key=input;
            this.val=print;
        }
    }
    
    
    
    public Node root; // consructor for the root of the BST
 
    BST() { root = null; } // constuctor for BinarySearchTree
 
    BST(Key key, Value value) { root = new Node(key,value); count++;} // making the root node for the BST
 
    void insert(Key key,Value value) { root = insertRec(root, key, value); count++;}     // This method calls insertRec()

    /**
     * Adds the nodes into the BST
     * 
     * @param root
     * @param key
     * @param value
     * @return N/A
     * @throws N/A
     */
    Node insertRec(Node root, Key key, Value value){ //recursive methos to insert a new key into te BST

        if (root == null) {
            root = new Node(key,value); // if the tree is fully empty, this will make the first node
            return root;
        }else if (key.compareTo(root.key)<0) // if the key is less then the root key, will go down the left side of the subtree
            root.left = insertRec(root.left, key, value); // recall insertRec
        else if (key.compareTo(root.key)>0) // if the key is greater then the root key, will go down the right side of the subtree
            root.right = insertRec(root.right, key, value); //recall insertRec
 
        return root; //returns the node pointer
    }
    

      /**
       * recursive method for formatting the final print statement
       * 
       * @param N/A
       * @return N/A
       * @throws N/A
       */
      public void inOrder() {
        inOrder(root);
    }  
        
      /**
       * function for formatting the final print statement... iterates through the BST
       * 
       * @param N/A
       * @return N/A
       * @throws N/A
       */
        private void inOrder(Node node) {
            if (node == null) { // if there is nothing in the BST/leaf
                return;
            }   
    
            inOrder(node.left); // goes down the left side of the BST
        
            
            finalStr+=node.val+"\n"; // making line for printing
            
            
            
            inOrder(node.right); // goes down the right side of the BST
    } 
        
        /**
         * Prints the final message to a file
         * 
         * @param N/A
         * @return N/A
         * @throws IOException
         */
        public void finalPrint(){
            try{ // trying to make a new file and write to it
                FileWriter fWriter=new FileWriter("Output.txt");
                fWriter.write("Inorder traversal\n");
                fWriter.write(finalStr);
                fWriter.close();
                System.out.println("Successful Write.");
            }catch(IOException e){ // throws an error if the method failed to write to a file
            System.out.println("error writing to file.");
            e.printStackTrace();
        }
            
            
        }
    
    
}






