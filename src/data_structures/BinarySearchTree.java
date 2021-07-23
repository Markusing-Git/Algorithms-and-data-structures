package data_structures;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A BST or Binary Search tree is a data-structure where every key is unique. The tree is either empty or has a root
 * node with subtrees to the left (lesser keys) or subtrees to the right (higher keys). Searching in a BST is O(log n)
 * and adding a item is O(1).
 *
 * @author mIngemarsson
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<E>{
        private E data;
        private Node<E> left, right;
        private Node(E d){
            data = d;
            left = right = null;
        }
        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<E> root;
    private E deletedData;

    /**
     * Constructor method, initiates tree with a null root.
     */
    public BinarySearchTree(){
        root=null;
    }

    /**
     * This method adds an item to the tree.
     * @param data item to be added
     * @return true if successful, false if item already exists.
     */
    public boolean add(E data){
        if(root==null){
            root = new Node<E>(data);
            return true;
        }else
            return add(data,root);
    }
    private boolean add(E data, Node<E> node){
        if(data.compareTo(node.data)==0) {
            return false;
        }else if(data.compareTo(node.data)<0) {
            if (node.left == null) {
                node.left = new Node<E>(data);
                return true;
            }else{
                return add(data, node.left);
            }
        }else{
            if (node.right == null) {
                node.right = new Node<E>(data);
                return true;
            }else{
                return add(data, node.right);
            }
        }
    }


    /**
     * This method will search the tree for a given item.
     * @param target item to be searched for, must be a comparable item.
     * @return return item if successful, null if unsuccessful.
     */
    public E find(E target){
        return find(target, root);
    }
    private E find(E target, Node<E> node){
        if(node==null)
            return null;
        if(target.compareTo(node.data)==0)
            return node.data;
        if(target.compareTo(node.data)<0)
            return find(target,node.left);
        return find(target,node.right);
    }

    /**
     * This method will find the largest key in the tree.
     * @return max item or null if empty.
     */
    public E max(){
        if(root==null)
            return null;
        else
            return max(root, root.data);
    }
    private E max(Node<E> node, E max){
        if(node!=null) {
            return max(node.right, node.data);
        }else{
            return max;
        }
    }

    /**
     * This method will remove a given item from the tree.
     * @param target item to be removed.
     * @return removed item if successful, null if unsuccessful.
     */
    public E delete(E target){
        root = delete(target,root);
        return deletedData;
    }
    private Node<E> delete(E target, Node<E> node){
        if(node==null) {
            deletedData = null;
            return null;
        }else{
            if(target.compareTo(node.data)<0){
                node.left = delete(target,node.left);
                return node;
            }else if(target.compareTo(node.data)>0){
                node.right = delete(target, node.right);
                return node;
            }else{
                deletedData = node.data;
                if(node.left==null){
                    return node.right;
                }else if(node.right==null){
                    return node.left;
                }else{
                    Node<E> nodeToMove=node.right, parentNodeToMove=node;
                    if(nodeToMove.left==null){
                        nodeToMove.left=node.left;
                        return nodeToMove;
                    }
                    while(nodeToMove.left!=null){
                        parentNodeToMove=nodeToMove;
                        nodeToMove=nodeToMove.left;
                    }
                    parentNodeToMove.left=nodeToMove.right;
                    node.data= nodeToMove.data;
                    return node;
                }
            }
        }
    }

    /**
     * This method returns the number of nodes with no children.
     * @return the number of leaves.
     */
    public int numberOfLeaves(){
        return numberOfLeaves(root);
    }
    private int numberOfLeaves(Node<E> node){
        if(node==null)
            return 0;
        if(node.left==null && node.right==null)
            return 1;
        else
            return numberOfLeaves(node.left) + numberOfLeaves(node.right);
    }

    /**
     * This method returns the number of nodes in the entire tree.
     * Callstack may substantially grow if the tree is large.
     * @return the number of nodes.
     */
    public int numberOfNodes() { return numberOfNodes(root);}
    private int numberOfNodes(Node<E> node){
        if(node==null)
            return 0;
        else
            return  1 + numberOfNodes(node.left) + numberOfNodes(node.right);
    }

    /**
     * This method returns the number of levels (depth/height) of the tree.
     * @return height of the tree.
     */
    public int height() {return height(root);}
    private int height(Node<E>node){
        if(node==null)
            return 0;
        int left = height(node.left);
        int right = height(node.right);
        return 1 + Math.max(left, right);
    }

    /**
     * This method will print out the tree in it's natural form on the console.
     */
    public void printTree(){
        Queue<State> q = new LinkedList<>();
        State<E> t = new State(root, 1);
        int currentLvl = 1;
        while(t!=null){
            if(t.level>currentLvl) {
                System.out.println();
                currentLvl=t.level;
            }
            System.out.print(t.node + " ");
            if (t.node != null) {
                q.offer(new State(t.node.left, t.level + 1));
                q.offer(new State(t.node.right, t.level + 1));
            }
            t = q.poll();
        };
    }

    /**
     * This method will return a string representation of the tree in order (from left to right).
     * @return String representation of the tree.
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        inOrder(root,sb);
        return sb.toString();
    }

    //helper methods and classes ***************************************************************************************
    private static class State<E>{
        public Node<E> node;
        public int level;
        public State(Node<E> node, int level){
            this.node = node;
            this.level = level;
        }
    }

    private void inOrder(Node<E> node, StringBuilder sb){
        if(node!=null){
            inOrder(node.left, sb);
            sb.append(": "+node.toString());
            inOrder(node.right, sb);
        }
    }
}
