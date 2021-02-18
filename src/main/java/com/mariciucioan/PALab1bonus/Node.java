package com.mariciucioan.PALab1bonus;

import java.util.ArrayList;
import java.util.List;

/*
    ~~ Made by Mariciuc Ioan ~~
    ~~ Node class has the scope to generate a random rooted tree ~~
 */

public class Node {
    private Node parent = null;
    private final List<Node> children = new ArrayList<>();
    private final String data;

    static int totalNodes;
    static double probability;
    static double loweringValue;

    // Default constructor with a given probability to generate new node
    public Node(double prob) {
        probability = prob;
        loweringValue = 0;
        this.data = "node" + totalNodes++;
        generateRandomTree(prob);
    }

    // Enhanced constructor for a more "real" effect
    // It is lowering the probability to generate new node after each level from the rooted tree
    public Node(double prob, double loweringValueByForEachLevelOfGraph) {
        probability = prob;
        loweringValue = loweringValueByForEachLevelOfGraph;
        this.data = "node" + totalNodes++;
        generateRandomTree(prob);
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public void addChildren(double nextProb) {
        Node children = new Node(nextProb, loweringValue);
        children.setParent(this);
        this.children.add(children);
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    // Generates random tree using a given probability
    public void generateRandomTree(double probability) {
        try {
            probability -= loweringValue;

            while (Math.random() <= probability)
                this.addChildren(probability);
        } catch(StackOverflowError e) {
            System.out.println("The stack is full so the program will stop generating new nodes.");
            System.out.println("Try other probabilities to avoid this stopping occurring.");
            System.exit(0);
        }
    }

    // prints the tree
    public void printTree() {
        System.out.println((isLeaf() ? "-" : "+") + this.data);

        printChildren(this, "  ");
    }

    private void printChildren(Node parent, String space) {

        for (Node e : parent.getChildren()) {
            System.out.println(space + (e.isLeaf() ? "-" : "+") + e.data);
            printChildren(e, space + "  ");
        }
    }
}
