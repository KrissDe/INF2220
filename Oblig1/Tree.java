import java.util.*;
import java.io.*;


/** Class Tree is an implemented data structure for dictionary file and is a Binary Search Tree. 
 *  Includes an object of the TreeNode class in order to get information about each node of the tree
 *  in order to execute different methods.
 */
 
public class Tree{
    protected TreeNodes root;
    private int count = 0;
    
    //constructor
    public Tree(){
        root = null;
    }
    
    
    /** Method isEmpty() to check if the tree is empty.
     * @return true/false depending on the value of the root of the tree
     */
     
    public boolean isEmpty(){
        return root == null;
    }
    
    
    
    /** Method to insert data into the tree.
     *  @param data   element that must be inserted
     */
     
    public void insertData(String data){
        root = insertData(root, data);
        
//          System.out.println("Item '" + data + "' is inserted ");
//          System.out.println(countAllNodes(root) + " items");  //--for testing only
    }
    
    
    /** Method insertData() to insert data recursively - help-method for the one above.
     *  @param node   object of class TreeNodes that is a node of the tree being used in order to find a place to insert the element.
     *  @param data   element that must be inserted
     *  @return       return new inserted node
     */
     
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
    
    
    
    
    /** Method findWord() to chechk if the element can be found in the tree.
     *  @param data    element that must be found
     *  @return        true/false value after execution of the help-method
     */
     
    public boolean findWord(String data){
        return (findWord(root, data));
    }
    
    
    /** Method findWord() to find the word recursively - help-method for the one above
     *  @param node   object of class TreeNodes that is a node of the tree being used to compare with the word being searched in order to check if the word exists in the tree.
     *  @param data   element that must be found
     *  @return       true/false depending on if the word had been found in the tree
     */
     
    private boolean findWord(TreeNodes node, String data){
//      System.out.println("Searching");       --for testing only

        if(node == null){
//           System.out.println("empty"); --for testing only
            return false;
        }
        
        if(node.getKey().equals(data)){
//             System.out.println("found"); --for testing only
            return true;
            
        }else if(data.compareTo(node.getKey()) < 0){
            return(findWord(node.getLeft(), data));
            
        }else{
            return(findWord(node.getRight(), data));
        }
    }
    
    
    
    
    /** Method deleteData() to remove an element from the tree.
     *  @param data  element that must be deleted  
     */
     
    public void deleteData(String data){
    
        if(isEmpty()){
            System.out.println("Tree is empty. Nothing to delete\n");
            
        }else if(findWord(data) == false){
            System.out.println("Can not find item '" + data + "'\n");
            
        }else{
            root = deleteData(root, data);
            System.out.println("Item '" + data + "' is deleted\n");
        }
//         findWord("busybody");
//           System.out.println(countAllNodes(root) + " items"); //--for testing only
    }
    
    
    
    /** Method findMin() to find the smallest item in the subtree.
     *  @param node   tree node 
     *  @return       node with the smallest item
     */
     
    protected TreeNodes findMin(TreeNodes node){

        if(node == null){
            return null;
        
        }else if( node.getLeft() == null){
            return node;
        }
    
        return findMin(node.getLeft());
}




    /** Method deleteData() to remove element from the tree recursively - help-method for the one above.
     *  @param node    object of class TreeNodes that is a node of the tree being used in order to delete the element and keep the properties for the BST.
     *  @param data    element that must be deleted  
     */

    protected TreeNodes deleteData(TreeNodes node, String data){
    
        if( node == null ){
            return node;   // Item not found
        }
        
        if(data.compareTo(node.getKey()) < 0){
            node.setLeft(deleteData(node.getLeft(), data));
            
        }else if(data.compareTo(node.getKey()) > 0){
            node.setRight(deleteData(node.getRight(), data));
            
        }else if(node.getLeft() != null && node.getRight() != null){ // In case with two children
            node.setKey(findMin(node.getRight()).getKey());
            node.setRight(deleteData(node.getRight(), node.getKey()));
            
        }else{
            node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        }
        
        return node;
}
     
     
     
     
    /** Method readFileIntoTree() that inserts words from the dictionary into data structure(BST)
     *  @param br   buffer with data from the file
     *  @throws IOException   If an input or output exception has occured
     */
    
    
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
            
//            System.out.println("Insertion done " + count); //--for testing only
//            System.out.println();
            br.close();
            
        }catch(IOException ex){
            System.out.println("Error while reading from file. Program exiting...");
            System.exit(1);
        }
    }
    
    
    
    /** Method treeDepth() to count max depth of the tree.
     *  @return   max depth value returned from the help-method
     */
    
    public int treeDepth(){
        return(treeDepth(root));
    }
    
    
    /** Method treeDepth() to count max depth of the tree recursively - help-method for the one above.
     *  @param node   tree node 
     *  @return       max depth of the tree as an int
     */
     
    private int treeDepth(TreeNodes node){
        int leftNodeDepth, rightNodeDepth;
        
        if(node == null){
            return 0;
            
        }else if(node.getLeft() == null && node.getRight() == null){ //if it is root 
            return 0;
            
        }else{
            leftNodeDepth = treeDepth(node.getLeft());
            rightNodeDepth = treeDepth(node.getRight());
            
            return(Math.max(leftNodeDepth, rightNodeDepth) + 1);
        }
    }
    
    
    
    /** Method nodesPerDepth() to count amount of nodes per each depth of the tree
     *  @param depth    layer of the tree
     *  @return         resulted value from the help-method as an int
     */
     
    public int nodesPerDepth(int depth){
        return(nodesPerDepth(depth, root));
    }
    
    
    /** Method nodesPerDepth() to count amount of nodes per each depth of the tree recursively - help-method for the one above.
     *  @param depth   layer of the tree
     *  @param node    tree node
     *  @return        amount of nodes per tree depth as an int
     */
     
    private int nodesPerDepth(int depth, TreeNodes node){
    
        if(node == null){
            return 0;
            
        }else if(depth==0){
            return 1;
            
        }else{
            return nodesPerDepth(depth-1, node.getLeft()) + nodesPerDepth(depth-1, node.getRight());
        }
    }
    
    
    
    
    /** Method firstWord() to find alphabetically first word of the dictionary.
     *  @return  resulted word from the help-method as a String
     */
     
    public String firstWord(){
        return (firstWord(root));
    }
    
    /** Method firstWord() to find alphabetically first word of the dictionary recursively - help-method for the one above.
     *  @param node   tree node 
     *  @return       first word(which is element in the tree node) as a String
     */
     
    private String firstWord(TreeNodes node){
    
        while(node.getLeft() != null){
            node = node.getLeft();
        }
        
        return(node.getKey());
        
    }
    
    
    
    
    /** Method lastWord() to find alphabetically last word of the dictionary.
     *  @return  resulted word from the help-method as a String
     */
     
    public String lastWord(){
        return (lastWord(root));
    }
        
    /** Method lastWord() to find alphabetically first word of the dictionary recursively - help-method for the one above.
     *  @param node   tree node 
     *  @return       last word(which is element in the tree node) as a String
     */
     
    private String lastWord(TreeNodes node){
    
        if(node.getRight() == null){
            return node.getKey();
        }
        
        return lastWord(node.getRight());
    }
    
    
    
    
    
    
    /** Method countAllNodes() to count how many nodes BST contains.
     *  Optional method that has been used for checking if the deleteData() method is executing properly.
     *  @param node      tree node
     *  @param counter   an int variable that counts all the nodes in the tree
     *  @return          total amount of nodes as an int
     */
     
    public int countAllNodes(TreeNodes node){
    
        if(node == null){
            return(0);
            
        }else{
            int counter = 1;
            counter += countAllNodes(node.getLeft());
            counter += countAllNodes(node.getRight());
            return counter;
        }
    }
   
  
  
  
    /** Method countLeaves() to count the number of leaves for a node in BST.
     *  @param node   tree node
     *  @return       total amount of leaves as an int 
     */
     
    public int countLeaves(TreeNodes node){
    
        if(node == null){
            return 0;
            
        }else if(node.getLeft() == null && node.getRight() == null){
            return 1;
            
        }else{
            return countLeaves(node.getLeft()) + countLeaves(node.getRight());
        }
    }
 
 
 
 
    /** Method sumNodeDepth() to count internal path length. 
     *  @param node   tree node 
     *  @param depth  tree layer
     *  @return       internal path length as an int
     */
     
    public int sumNodeDepth(TreeNodes node, int depth){
    
        if(node == null){
            return 0; //tree is empty
            
        }else if(node.getLeft() == null && node.getRight() == null){
            return depth-1; //if root
            
        }else{ //node is not a leaf node 
            return sumNodeDepth(node.getLeft(), depth+1) + sumNodeDepth(node.getRight(), depth+1);
        }
    }
    
    
    
    
    
    /** Method averageNodesDepth() to count average depth of all the nodes.
     *  @return   average node depth as a double
     */
     
    public double averageNodesDepth(){
        return(((double)sumNodeDepth(root,0))/countLeaves(root));
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