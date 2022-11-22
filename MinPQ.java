/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package covid;


/**
 *
 * @author tiegancozzie
 * 
 * MinPQ takes in an array and turns each line into a node with Cases being its key
 * it will then sort all the data for each country into least to greatest
 * it will then take the three smallest cases and send them into the BST
 * Then clear whatever is left inside the MinPQ for the next set of entries
 * 
 * @param E
 * @throws N/A
 * @return N/A
 */
    
// Node

public class MinPQ<E extends Comparable<E>>{
    
        BST<Integer,String> bst=new BST(); // Creating the BST with generic Integer,String
    
        private Node<E> first; // first of the MinPQ
        private Node<E> last; // last of the MinPQ
    int count=0; // how many items are in the MinPQ
    int max=5; // max number of nodes allowed into the MinPQ
    boolean firstTime=true;
    int secondTime=0;
    
    
    
    
    public void LinkedList(int m){ // creates the LinkedList
        first=null; // setting first to null
        
    }
    
    private static class Node<E>{
        private E data;
        private Node<E> next;
        private Node<E> prev;
        private String continent;
        private String location;
        private String date;
        private int total_cases;
        private  int new_cases;
        private long population;
        private String printFormat;
        
        
        
        public Node(E input,String cont,String loc,String d,int tc,int nc,long pop){ // initializes the first node in the stack
            this.data=input;
            this.continent=cont;
            this.location=loc;
            this.date=d;
            this.total_cases=tc;
            this.new_cases=nc;
            this.population=pop;
            this.next=null;
            this.prev=null;
            // makes the print format 
            this.printFormat="New Cases: "+nc+" at "+location+"/"+continent+" on "+date+" Total: "+total_cases+" Pop: "+population;
        }
        
        public void setPrev(Node p){ // sets previous node
            prev = p;
        }
        
        public void setNext(Node n){ // sets next node
            next = n;
        }
        
        public Node getNext(){ // gets next node
            return next;
        }
        
        public Node getPrev(){ // gets prev node
            return prev;
        }

        
    }
        /**
         * pushes a node into the linked list
         * 
         * @param in
         * @param cont
         * @param loc
         * @param d
         * @param tc
         * @param nc
         * @param pop 
         * @returns N/A
         * @throws N/A
         */
        public void push(E in,String cont,String loc,String d,int tc,int nc,long pop){
            
            secondTime++;
            count++; // counts how many things are on the stack
            Node temp=first; //assigns the old first to temp
            first=new Node(in,cont,loc,d,tc,nc,pop); //makes the new input node first
            first.setNext(temp); //makes the new nodes next = oldfirst
            
            if(secondTime==2){ // assignes last when two nodes are inside the Linked list
                last=temp;
            }
            
            
            
            if(firstTime!=true){ // makes sure every node has correct prev
                temp.setPrev(first);
                sort(); // sorts the Linked List to have the largest value at the bottom
            }
            firstTime=false;
            
            
            if(count==max){ // if the link list is at its allowed limit, it will them remove the smallest node from the MinPQ
                pop(); // removes node
            }
            
        }
        
        /**
         * method to remove a node from the MinPQ
         * 
         * @param N/A
         * @return Node
         * @throws N/A
         */
        public Node pop(){ 
            Node removeTemp=null; // makes a temp node to return
            if(count==0){ //Checks to see if there is anything to pop off in the stack 
            }else{
                removeTemp=first; // removing the front
                first=first.getNext(); //makes the new first the second node
                count--; // subtracts a number from the total in the stack
            }
            return removeTemp; // returns node removed

        }

        /**
         * sorting method to make sure the largest nodes are at the bottom (MinPQ)
         * 
         * @param N/A
         * @return N/A
         * @throws N/A
         */
        public void sort(){ 
            Node temp=first; 
            Node iter=first;
            if(first.getNext()==last){ // if there are only two nodes in the MinPQ
                iter=last;
                if(temp.new_cases<iter.new_cases){ // if the first node is greater then the last. Flip
                        temp.setNext(null); 
                        temp.setPrev(iter);
                        iter.setNext(temp);
                        iter.setPrev(null);
                        first=iter;
                        last=temp;
                    }
            }else{ // if there are more then two nodes in the MinPQ
            iter=first;
            temp=first;
            while(iter.getNext()!=last){ // checks every node
                iter=iter.getNext(); 
                temp=iter.getPrev();
                
                if(first.new_cases>first.getNext().new_cases){ // checks if the first node is greater then the second
                    temp=first.getNext();                       // and flips if its true
                    first.setNext(temp.getNext());
                    temp.getNext().setPrev(first);
                    temp.setPrev(null);
                    temp.setNext(first);
                    first.setPrev(temp);
                    first=temp;
                    temp=first;
                    
                }
                
                
                if(temp.new_cases>iter.new_cases){ // checking each node after first
                    temp.setNext(iter.getNext());
                    temp.getNext().setPrev(temp);
                    iter.setPrev(temp.getPrev());
                    iter.getPrev().setNext(iter);
                    iter.setNext(temp);
                    temp.setPrev(iter);
                }
            }
        }
    }
        
        /**
         * sends the top three values in the MinPQ into BST
         * 
         * @param N/A
         * @return N/A
         * @throws N/A
         */
        public void sendThree(){ 
            
            
            if(count>=3){ // if there are three values in the MinPQ. send all three
            Node a=pop();
            Node b=pop();
            Node c=pop();
            

            
            bst.insert(a.new_cases,a.printFormat); // sending the first
            bst.insert(b.new_cases,b.printFormat); // sending the second
            bst.insert(c.new_cases,c.printFormat); // sending the third
            
            }else if(count==2){ // if there are only two values in the MinPQ
                Node a=pop(); 
                Node b=pop(); 
                
                bst.insert(a.new_cases,a.printFormat); // sending one
                bst.insert(b.new_cases,b.printFormat); // sending two
                
            }else if(count==1){ // if there is only one value in the MinPQ
                Node a=pop(); 
                
                bst.insert(a.new_cases,a.printFormat); // send one value
            }
            
            if(first!=null){ // checks to see if the MinPQ is empty
                while(first.getNext()!=null){ //empty's MinPQ
                    pop();
            }
                
        }
                if(first!=null){ // empty's MinPQ 
                    pop();
                }

            
            
            firstTime=true; // resets first time through
            secondTime=0; // resets second time through
            
        }
        
        /**
         * print function only used for testing
         * 
         * @param N/A
         * @return N/A
         * @throws N/A
         */
        public void printStack(){
            System.out.println("----------PRINTING----------");
            Node temp=first; //a variable we can use to iterate through the stack
            if(count==0){ 
                System.out.println("There is nothing in the stack");
            }else{
                while(temp!=null){ // goes over everything in the stack and outputs its value
                    System.out.println(temp.data+" ");
                    temp=temp.getNext();
                }
   
            }
            
        }
        
}
