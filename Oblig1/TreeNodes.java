import java.util.*;


/** Class TreeNodes that includes information about the nodes of the binary search tree. */

public class TreeNodes{
    TreeNodes leftNode, rightNode;
    String data;
   
   //constructor
   TreeNodes(String d, TreeNodes leftNode, TreeNodes rightNode) {
        leftNode = rightNode = null;
        data = d;
    }
    
    public String getKey() { return data; }
    public TreeNodes getLeft() { return leftNode; }
    public TreeNodes getRight() { return rightNode; }
 

    // mutators (change fields)
    public void setKey(String theData) { data = theData; }
    public void setLeft(TreeNodes newL) { leftNode = newL; }
    public void setRight(TreeNodes newR) { rightNode = newR; }
} 
