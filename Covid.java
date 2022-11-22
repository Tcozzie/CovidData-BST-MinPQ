/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package covid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author tiegancozzie
 */
public class Covid {
    

    /**
     * This is the main class and will perform the following tasks
     * Create a scanner that will read from a given file.
     * Read each line of the file and store each line into an array
     * It will then send this array into MinPQ where it will be turned into a nodes
     * the three smallest entries for each country will then be sent into BST
     * The BST is where everything on the file will be stored
     * 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException 
     * @return N/A
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file=new File("owid-covid-data.csv"); // Stores the file i have to read from
        Scanner in=new Scanner(file); // creates a scanner to send info into Min PQ
        Scanner iter=new Scanner(file); // iterator for checking the Location
        Scanner tempIter=new Scanner(file); // iterator for checking the Location
        boolean firstTimeThrough=true; 
 
        
        MinPQ<String> pq= new MinPQ(); // Creates MinPQ with generic type String
        
        in.next(); // gets the read in scanner to the right position
       
        String[] array1=iter.nextLine().split(","); // gets the iterator scanner in the right position and splits the line into an array
        String[] array2=tempIter.nextLine().split(","); // gets the iterator scanner in the right position and splits the line into an array
        
        array1=iter.nextLine().split(","); 
        array2=tempIter.nextLine().split(",");
        
        in.nextLine();
        
        while(in.hasNext()){ // Goes through the whole file
            if(firstTimeThrough==true){ // if its the first time in the while loop
                array1=iter.nextLine().split(","); // gets one of the iterators one step ahead to check the locations
                firstTimeThrough=false;
            }
            
            String send[]=in.nextLine().split(","); // Splits up the first correct line in file for sending into MinPQ
            
            // Sends the information into MinPQ
            pq.push(send[4],send[0],send[1],send[2],Integer.parseInt(send[3]),Integer.parseInt(send[4]),Long.parseLong(send[5]));
            
            
            if(!array2[1].equals(array1[1])){ // checks to see if the iterators arent equal... So we can send the top three values
                                                // of that country into the BST
                
                
                pq.sendThree(); // sends the top three values into the BST

            }
            
            
            if(iter.hasNext()){ // Moves one of the iterators to the next line
            array2=tempIter.nextLine().split(",");
            }

            
            if(iter.hasNext()){ // Moves the other iterator to the next line
            array1=iter.nextLine().split(",");
            }
        }
        pq.sendThree(); // if only 2 lines. it will send those to BST
        pq.bst.inOrder(); // formats print value
        pq.bst.finalPrint(); // prints format into file
    }

}
