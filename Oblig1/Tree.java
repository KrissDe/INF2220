import java.util.*;
import java.io.*;

public class Tree{
    protected TreeNodes root;
    private int count = 0;
    
    //constructor
    public Tree(){
        root = null;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    //--INSERT method
    public void insertData(String data){
        root = insertData(root, data);
//         System.out.println("Item '" + data + "' is inserted");  --for testing bare
    }
    
    
    protected TreeNodes insertData(TreeNodes node, String data){
        if(node == null){
           return new TreeNodes(data, null, null);
        }else{
            if(data.compareTo(node.getKey()) <= 0){
                node.setLeft(insertData(node.getLeft(), data));
                return node;
            }else{
                node.setRight(insertData(node.getRight(), data));
                return node;
            }
        }
    }
    
    
    
    //--SEARCH method
    public boolean findWord(String data){
        return (findWord(root, data));
    }
    
    private boolean findWord(TreeNodes node, String data){
//      System.out.println("Searching");       --for testing

        if(node == null){
//          System.out.println("empty");
            return false;
        }
        
        if(node.getKey().equals(data)){
            return true;
            
        }else if(data.compareTo(node.getKey()) < 0){
            return(findWord(node.getLeft(), data));
            
        }else{
            return(findWord(node.getRight(), data));
        }
    }
    
    
    
    //--DELETE method
    public void deleteData(String data){
    
        if(isEmpty()){
            System.out.println("Tree is empty. Nothing to delete\n");
            
        }else if(findWord(data) == false){
            System.out.println("Can not find item '" + data + "'\n");
            
        }else{
            root = deleteData(root, data);
            System.out.println("Item '" + data + "' is deleted\n");
        }
    }
    
    
    protected TreeNodes deleteData(TreeNodes node, String data){
        TreeNodes tr1, tr2, tr3;
        
        if(node.getKey().equals(data)){
            TreeNodes lftNode, rhtNode;
            lftNode = node.getLeft();
            rhtNode = node.getRight();
            
            if(lftNode == null && rhtNode == null){
                return null;
                
            }else if(lftNode == null){
                tr1 = rhtNode;
                return tr1;
                
            }else if(rhtNode == null){
                tr1 = lftNode;
                return tr1;
                
            }else{
                tr2 = rhtNode;
                tr1 = rhtNode;
                
                while(tr1.getLeft() != null){
                    tr1 = tr1.getLeft();
                    tr1.setLeft(lftNode);
                    return tr2;
                }
                
            }
        }
        
        if(data.compareTo(node.getKey()) < 0){
            tr3 = deleteData(node.getLeft(), data);
            node.setLeft(tr3);
            
        }else{
            tr3 = deleteData(node.getRight(), data);
            node.setRight(tr3);
        }
        
        return node;
    }
     
     
     
     
    /*---------------READING DICTIONARY INTO BINARY SEARCH TREE----------------*/
    
    
    public void readFileIntoTree(BufferedReader br){
                
        try{
        
            while(true){
                String words = br.readLine();
                
                if(words == null){
                    break;
                }
                
                insertData(words);
                count++;
            }
            
//             System.out.println("Insertion done " + count); --for testing bare
            System.out.println();
            br.close();
            
        }catch(IOException ex){
            System.out.println("Error while reading from file. Program exiting...");
            System.exit(1);
        }
    }
    
    
    
    /*---------------METHODS FOR TREE STATISTICS--------------- */
    
    
    //--count MAX TREE DEPTH
    public int treeDepth(){
        return(treeDepth(root));
    }
    
    private int treeDepth(TreeNodes node){
        int leftNodeDepth, rightNodeDepth;
        
        if(node == null){
            return(0);
            
        }else{
            leftNodeDepth = treeDepth(node.getLeft());
            rightNodeDepth = treeDepth(node.getRight());
            
            return(Math.max(leftNodeDepth, rightNodeDepth) + 1);
        }
    }
    
    
    //--count amount of NODES PER EACH DEPTH of the tree
    public int nodesPerDepth(int depth){
        return(nodesPerDepth(depth, root));
    }
    
    private int nodesPerDepth(int depth, TreeNodes node){
    
        if(node == null){
            return(0);
            
        }else if(depth==1){
            return(1);
            
        }else{
            return nodesPerDepth(depth-1, node.getLeft()) + nodesPerDepth(depth-1, node.getRight());
        }
    }
    
    
    
    //--find alphabetically FIRST WORD of the dictionary
    public String firstWord(){
        return (firstWord(root));
    }
    
    private String firstWord(TreeNodes node){
    
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        
        return(node.getKey());
        
    }
    
    
    
    //--find alphabetically LAST WORD of the dictionary
    public String lastWord(){
        return (lastWord(root));
    }
        
    private String lastWord(TreeNodes node){
    
        if(node.getRight() == null){
            return node.getKey();
        }
        
        return lastWord(node.getRight());
    }
    
    
    
    //--count total amount of nodes
    public int countNodes(TreeNodes node){
    
        if(node == null){
            return(0);
            
        }else if(node.getLeft() == null && node.getRight() == null){
            return(1);
            
        }else{
            return countNodes(node.getLeft()) + countNodes(node.getRight());
        }
    }
   
  
 
    public int sumNodeDepth(TreeNodes node, int depth){
    
        if(node == null){
            return(0); //tree is empty
            
        }else if(node.getLeft() == null && node.getRight() == null){
            return depth; //node er løvnode
            
        }else{ //er ikke løvnode 
            return sumNodeDepth(node.getLeft(), depth+1) + sumNodeDepth(node.getRight(), depth+1);
        }
    }
    
    
    //--count AVERAGE DEPTH of all the nodes
    public double averageNodesDepth(){
        return(((double)sumNodeDepth(root,0))/countNodes(root));
    }
    
// ----------for testing bare(inOrder printing)------------

//     public void printTree(){
//         printTree(root);
//         System.out.println();
//     }
//     
//     private void printTree(TreeNodes node){
//         if(node == null) return;
//         
//         printTree(node.getLeft());
//         System.out.println(node.getKey() + " ");
//         printTree(node.getRight());
//     }

}