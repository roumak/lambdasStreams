package org.thisrc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lazylambdas {


    public static int databaseSyncCall(){
        System.out.println("calling database for sync");
        return 40;
    }

    private static void simpleLazylambdas() {
        long var =50;

        if(var> 100 && databaseSyncCall() > var){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }

    public static void main(String[] args) {

    }


    private static void doNotDoItLazyLambdas() {
        List<Integer> list = IntStream
                .range(1,11)
                .boxed()
                .collect(Collectors.toList());

        int factor=2;
        Stream<Integer> stream = list.stream().map(e-> e * factor);

        stream.forEach(System.out::println);
    }


}
