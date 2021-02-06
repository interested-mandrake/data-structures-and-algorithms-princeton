package com.interested;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class HelloGoodbye {

    public static void main(String[] args) {
        String name1 = StdIn.readString();
        String name2 = StdIn.readString();
        StdOut.println("Hello " + name1 + " and " + name2 + ".");
        StdOut.println("Goodbye " + name2 + " and " + name1 + ".");
    }
}
