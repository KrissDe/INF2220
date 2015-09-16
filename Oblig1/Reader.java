import java.io.*;
import java.util.*;

public class Reader{
    Tree tr = new Tree();
    
    public void readFile(Tree tr){
        BufferedReader br = null;
        
        try{
            String currLine;
            br = new BufferedReader(new FileReader("dictionary.txt"));
            
//             System.out.println("Start reading"); --for testing bare

            tr.readFileIntoTree(br);
            
        }catch(IOException e){
            System.out.println("Can not read the file. Program exiting..") ;
            System.exit(1);
        }
    }
}