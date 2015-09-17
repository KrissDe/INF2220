import java.io.*;
import java.util.*;

/** Class Reader that reads the dictionary-file and calls
 *  the method from the Tree class to insert the words from
 *  the file into binary search tree.
 */

public class Reader{
    Tree tr = new Tree();
    
    
    /** Method readFile to read the file and call 
     *  method from Tree class to insert data into BST.
     *  @param tr    binary search tree for the dictionary
     *  @throws IOException  If an input or output exception has occured    
     */
     
    public void readFile(Tree tr){
        BufferedReader br = null;
        
        try{
            String currLine;
            br = new BufferedReader(new FileReader("dictionary.txt"));
            
//             System.out.println("Start reading"); --for testing only

            tr.readFileIntoTree(br);
            
        }catch(IOException e){
            System.out.println("Can not read the file. Program exiting..") ;
            System.exit(1);
        }
    }
}