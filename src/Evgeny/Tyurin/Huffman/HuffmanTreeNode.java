package Evgeny.Tyurin.Huffman;

/*
    Huffman tree node class
*/

class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {

    // Left child
    private HuffmanTreeNode leftChild;
    // Right child
    private HuffmanTreeNode rightChild;
    // Character
    private char letter;
    // Number of occurrences of letter in text
    private int number;

    // Construct a node with specified letter and
    // number of occurrences in text
    HuffmanTreeNode(char letter, int number) {
        this.letter = letter;
        this.number = number;
    }

    // Compare nodes by number of occurrences in text
    public int compareTo(HuffmanTreeNode anotherNode) {
        if (anotherNode == null)
            return 1;
        return Integer.compare(number, anotherNode.number);
    }

    void setLeftChild(HuffmanTreeNode node) {
        leftChild = node;}

    void setRightChild(HuffmanTreeNode node) {
        rightChild = node;}

    HuffmanTreeNode getLeftChild() {return leftChild;}

    HuffmanTreeNode getRightChild() {return rightChild;}

    int getNumber() {return number;}

    char getLetter() {return letter;}

}
