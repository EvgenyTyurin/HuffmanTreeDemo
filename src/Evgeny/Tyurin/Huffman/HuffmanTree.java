package Evgeny.Tyurin.Huffman;

import java.util.*;

/*
    Huffman tree implementation
*/

public class HuffmanTree {

    // Nodes of Huffman tree
    private ArrayList<HuffmanTreeNode> nodes = new ArrayList<>();

    // Stack structure that needed in tree patrol to
    // build Huffman tree code table
    private Stack<Character> patrolStack = new Stack<>();

    // Code table char:huffman code
    private HashMap<Character, String> codeTable = new HashMap<>();

    // Construct Huffman tree and code table by input text
    public HuffmanTree(String txt) throws RuntimeException {

        // Check input text
        if (txt == null || txt.length() < 2)
            throw new RuntimeException("Wrong text input.");

        // Convert text to map char:number of occurrences in text
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int charCounter = 0; charCounter < txt.length(); charCounter ++) {
            char c = txt.charAt(charCounter);
            int n = 0;
            if (charMap.containsKey(c))
                n = charMap.get(c);
            charMap.put(txt.charAt(charCounter), n + 1);
        }

        // Translate char map to priority queue of tree nodes
        PriorityQueue<HuffmanTreeNode> nodeQueue = new PriorityQueue<>();
        for (Character letter : charMap.keySet()) {
            nodeQueue.add(new HuffmanTreeNode(letter, charMap.get(letter)));
        }

        // Build a Huffman tree from nodes queue by Lafore algorithm
        while (nodeQueue.size() > 1) {
            HuffmanTreeNode leftNode = nodeQueue.remove();
            HuffmanTreeNode rightNode = nodeQueue.remove();
            HuffmanTreeNode newNode =
                    new HuffmanTreeNode(Character.MIN_VALUE, leftNode.getNumber() + rightNode.getNumber());
            newNode.setLeftChild(leftNode);
            newNode.setRightChild(rightNode);
            nodeQueue.add(newNode);
            nodes.add(leftNode);
            nodes.add(rightNode);
        }
        nodes.add(nodeQueue.remove());

        // Build code table by tree
        treePatrol(nodes.get(nodes.size() - 1));
    }

    // Converts text to Huffman code by code table
    public String encode(String text) {
        StringBuilder result = new StringBuilder();
        for (int charCounter = 0; charCounter < text.length(); charCounter ++) {
            char c = text.charAt(charCounter);
            result.append(codeTable.get(c));
        }
        return result.toString();
    }

    // Restore text by huffman code and tree
    public String decode(String huffmanText) {
        StringBuilder result = new StringBuilder();
        HuffmanTreeNode node = nodes.get(nodes.size() - 1);
        for (int textIdx = 0; textIdx < huffmanText.length(); textIdx++) {
            if (huffmanText.charAt(textIdx) == '0') {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
            if (node.getLetter() != Character.MIN_VALUE) {
                result.append(node.getLetter());
                node = nodes.get(nodes.size() - 1);
            }
        }
        return result.toString();
    }

    // Builds a code table (recursive method)
    private void treePatrol(HuffmanTreeNode node) {
        if (node == null) {
            return;
        }
        if (node.getLetter() != Character.MIN_VALUE) {
            Stack<Character> tmpStack = new Stack<>();
            tmpStack.addAll(patrolStack);
            Stack<Character> tmpStackReverse = new Stack<>();
            while (!tmpStack.empty()) {
                tmpStackReverse.push(tmpStack.pop());
            }
            StringBuilder stackStr = new StringBuilder();
            while (!tmpStackReverse.empty()) {
                stackStr.append(tmpStackReverse.pop());
            }
            codeTable.put(node.getLetter(), stackStr.toString());
        }
        patrolStack.push('0');
        treePatrol(node.getLeftChild());
        if (!patrolStack.empty())
            patrolStack.pop();
        patrolStack.push('1');
        treePatrol(node.getRightChild());
        if (!patrolStack.empty())
            patrolStack.pop();
    }

    public HashMap<Character, String> getCodeTable() {return codeTable;}

}
