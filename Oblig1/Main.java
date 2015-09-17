import java.io.*;
import java.util.*;

/** Obligatory assignment 1. Main-class of the programme.
 *  @author Kristina Devochko
 */

class Main{
                     
    public static void main(String[] args){
        Tree tr = new Tree();
        Reader r = new Reader();
        
        r.readFile(tr);
        
//         tr.printTree(); --for testing only

        tr.deleteData("busybody");
        tr.insertData("busybody");
        
//         tr.deleteData("abactinally"); --fjerner root, for testing only

        menu(tr);
    }
    
    /** Method for user interface and communication with user
     *  @param tr     dictionary data structure(binary search tree)
     */
     
    public static void menu(Tree tr){
        Scanner sc = new Scanner(System.in);
        char usrInput;
        String searchWord;
        
        System.out.println("Welcome to the dictionary!\nPlease choose command or press q to exit:");
        System.out.println("(f-find word\nq - exit)\n");
        
        while(true){
            System.out.print("command(f/q): > "); 
            usrInput = sc.next().charAt(0);
            
            switch(usrInput){
            
                case 'f':
                    System.out.print("Enter a word:\n> ");
                    
                    searchWord = sc.next().toLowerCase();
                    
                    System.out.println("Given word: " + searchWord);
                    
                    if(tr.findWord(searchWord)){
                        System.out.println("Word '" + searchWord + "' is found"); 
                        
                    }else{
                        System.out.println("Generating similar words' suggestions...\n");
                        
                        SpellChecker sch = new SpellChecker(tr);
                        sch.spellCheck(searchWord);
                    }
                     
                    continue;
                
                //print some statistics before exiting
                case 'q': 
                    System.out.println("----------TREE STATISTICS---------\n");
                    System.out.println("1.The depth of the tree: " + tr.treeDepth() + "\n");
                    
                    System.out.println("2.Amount nodes for each depth of the tree: ");
                        System.out.println("---------Depth--------|--------Sum nodes--------");
                        
                        for(int i=0; i<tr.treeDepth(); i++){
                            System.out.println("\t   " + i + "\t      |\t\t   " + tr.nodesPerDepth(i));
                        }
                        
                        System.out.println();
                        
                    System.out.println("3.The average depth of all the nodes: " + tr.averageNodesDepth() + "\n");
                    System.out.println("4.First word of the dictionary: " + tr.firstWord());
                    System.out.println("  Last word of the dictionary: " + tr.lastWord() + "\n");
                    
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                
                default: 
                    System.out.println("Wrong command. Program exiting..");
                    System.exit(0);
                    break;
            }
            
        }
    } 
}