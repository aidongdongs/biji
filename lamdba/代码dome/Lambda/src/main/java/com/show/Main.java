package com.show;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class Main {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("线程中的方法启动了1");
//            }
//        }).start();
//
//        new Thread(()->{
//            System.out.println("线程中的方法启动了2");
//        }).start();

//
//        int i1 = calculateNum((left, right) -> left + right);
//        System.out.println(i1);
//
//        int i = calculateNum((a, b) -> {
//            return a + b;
//        });
//        System.out.println(i);


//        printNum(new IntPredicate() {
//            @Override
//            public boolean test(int value) {
//                return value%2==0;
//            }
//        });
//
//        printNum(value-> value%2==0);

//        Integer integer = typeCover(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        });
//
//        Integer integer1 = typeCover((String s) -> {
//            return Integer.valueOf(s);
//        });
//        System.out.println(integer1);

        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        foreachArr((value)->{
            System.out.println(value);
        });
    }

    public static void foreachArr(IntConsumer intConsumer){
        int [] arr = {1,213,141,23,523,423432,456235,1,312};
        for (int i = 0; i <arr.length ; i++) {
            intConsumer.accept(arr[i]);
        }
    }


    public static <R> R typeCover(Function<String,R> function){
        String str  = "12345";
        R result = function.apply(str);
        return result;
    }



    public static void printNum(IntPredicate predicate){
        int [] arr = {1,2,3,4,5,6,7,8,9,0};
        for (int i :arr
             ) {
            if (predicate.test(i)){
                System.out.println(i);
            }

        }
    }

    public static int calculateNum(IntBinaryOperator operator){
        int a =  10 ;
        int b = 20;
        return operator.applyAsInt(a,b);
    }


}
