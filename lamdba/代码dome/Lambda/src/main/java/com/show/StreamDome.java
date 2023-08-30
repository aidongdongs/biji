package com.show;

import com.show.bean.Author;
import com.show.bean.Book;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDome {
    public static void main(String[] args) {
        test2();


        List<Author> author = getAuthor();
//
//        author.stream()  //把集合转换为Stream流
//                .distinct()   //去重函数
//                .filter(item-> item.getAge()<18) //进行过滤 年龄《18 才获取
//                .forEach(itme-> System.out.println(itme)); //打印数据

//        author.stream().filter(item->{
//            return item.getName().length()>1;
//        }).forEach(item->{
//            System.out.println(item);
//        });

        // 把author1值 转换为 author1中的name
//        author.stream().map(author1 -> author1.getName() ).forEach(name->{
//            System.out.println(name);
//        });
//        System.out.println("-------");
//
//        author.stream().map(author1 ->author1.getAge() )
//                .map(age->age+10).forEach(item-> System.out.println(item));

//         author
//              .stream()
//              .map(author1 -> author1.getName())
//              .distinct().
//              forEach(
//                      item-> System.out.println(item)
//              );

//        author.stream()
//                 .distinct()
//                .sorted((o1, o2) -> o1.getAge()-o2.getAge())
//                .forEach(item-> System.out.println(item.getAge()));

//        author.stream().distinct().sorted((o1, o2) -> o2.getAge()-o1.getAge())
//                .limit(2).forEach(item-> System.out.println(item.getAge()));

//        author.stream().distinct().sorted((o1, o2) -> o2.getAge()-o1.getAge()).skip(1).forEach(item-> System.out.println(item));\


        author.stream().flatMap(author1 -> author1.getBooks().stream()).forEach(item-> System.out.println(item));
    }


    public static void test2(){
        Map<String,Integer> map  = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("蜡笔2新",19);

        Set<Map.Entry <String, Integer>> entries = map.entrySet();
        entries.stream().forEach(item->{
        });


    }

    private static List<Author> getAuthor(){
        Author author = new Author(1l, "1", 16, "123123123123212",null);
        Author author1 = new Author(2l, "鞥第2", 43, "123123123123212",null);
        Author author2 = new Author(3l, "鞥3", 23, "123123123123212",null);
        Author author3 = new Author(4l, "鞥4", 53, "123123123123212",null);

        //书籍列表
        List<Book> books1  = new ArrayList<>();
        List<Book> books2  = new ArrayList<>();
        List<Book> books3  = new ArrayList<>();


        books1.add(new Book(1l,"asdasdasd","123123",88,"12312312"));
        books1.add(new Book(1l,"asdasdas1123d","123vsdf23",889,"12asd312312"));


        books2.add(new Book(2l,"asdasdasd","123123",88,"12312312"));
        books2.add(new Book(2l,"asdasdas1123d","123vsdf23",889,"12asd312312"));

        books3.add(new Book(3l,"asdasdasd","123123",88,"12312312"));
        books3.add(new Book(3l,"asdasdas1123d","123vsdf23",889,"12asd312312"));

        author.setBooks(books1);
        author1.setBooks(books2);
        author2.setBooks(books3);
        author3.setBooks(books3);

        List<Author> authors = new ArrayList<>(Arrays.asList(author,author1,author3,author2));
        return authors;
    }

    public static void test1(){
        Integer [] arr = {1,1,2,3,4,5,6};
        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream1 = Stream.of(arr);
        stream.forEach(item->{
            System.out.println(item);
        });
        stream1.forEach(item->{
            System.out.println(item);
        });

    }
}
