package org.thisrc;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/*
      * What are lambdas and why do we need lambdas
      *. Functional Interfaces
      *. DRYing code with lambdas
      *. Anonymous Inner class
      *. lazy lambdas
      *. Side-effects and pure functions
      *. Higher order functions
      *. Composition
      *. Streams
     */

public class Main {

    public static void main(String[] args) {

    }

    private static void greetSomebody( Greeter greeter, String s) {
        System.out.println(greeter.greets(s));
    }

}
interface Greeter {
    String greets(String s);

}


//    public void function1( Data data){
//        ............
//        ............
//        2 lines of code unique
//        to function1
//        ............
//        .............
//        .............
//    }

//    public void function2( Data data){
//        ............
//        ............
//        val =few many
//        lines of code
//        unique to
//        function2
//        ............
//        .............
//        .............
//
//    }

//    linesOfCode =  some unique code to f2
//    or
//    linesOfCode =  some unique code to f1
//
//
//    public void function( Data data, LinesOfCodeInterface linesOfCode ){
//        ........
//        .......
//        .......
//        linesOfCode
//        ............
//        .............
//        .............
//
//    }