import Evgeny.Tyurin.Huffman.HuffmanTree;
import java.util.Scanner;

/*
    Robert Lafore "Data Structures And Algorithms in Java"
    Chapter 8 Exercise 5: Huffman tree demo
*/

public class Lafore8E5 {

    // Run point
    public static void main(String[] args) {
        String answer;
        do {
            // Read text to huffman it
            String text;
            Scanner con = new Scanner(System.in);
            do {
                System.out.print("Input text  : ");
                text = con.nextLine();
            } while (text.length() < 2);
            // Make a Huffman tree, print results
            HuffmanTree tree = new HuffmanTree(text);
            String huffmanCode = tree.encode(text);
            System.out.println("Code table  : " + tree.getCodeTable());
            System.out.println("Huffman code: " + huffmanCode);

            // Decode huffman code to verify tree and algorithms
            text = tree.decode(huffmanCode);
            System.out.println("Decoded text: " + text);

            // Ask user for another try
            System.out.println("");
            System.out.println("Input 'y' for another Huffman try");
            answer = con.nextLine();
        } while (answer.equals("y"));
    }

}
