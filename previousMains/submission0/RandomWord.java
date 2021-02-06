package com.interested;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {


    public static void main(String[] args) {


        // use Knuth's algorithm to get a random words

        String out = null;
        int i = 1;

        while(!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if(StdRandom.bernoulli(1.0/i)) {
                out = input;
            }
            i++;

        }
        StdOut.println(out);
    }



}
