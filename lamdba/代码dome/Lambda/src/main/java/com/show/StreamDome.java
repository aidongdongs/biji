package com.show;

import com.show.bean.Author;
import com.show.bean.Book;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDome {


    public static void test2() {
        Map<String, Integer> map = new HashMap<>();
        map.put("蜡笔小新", 19);
        map.put("黑子", 17);
        map.put("蜡笔2新", 19);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.stream().forEach(item -> {
        });


    }

    private static List<Author> getAuthor() {
        Author author = new Author(1l, "1", 19, "123123123123212", null);
        Author author1 = new Author(2l, "鞥第2", 43, "123123123123212", null);
        Author author2 = new Author(3l, "鞥3", 23, "123123123123212", null);
        Author author3 = new Author(4l, "鞥4", 153, "123123123123212", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();


        books1.add(new Book(1l, "1", "123123", 88, "12312312"));
        books1.add(new Book(1l, "2", "123vsdf23", 889, "12asd312312"));


        books2.add(new Book(2l, "3", "123123", 88, "12312312"));
        books2.add(new Book(2l, "4", "123vsdf23", 889, "12asd312312"));

        books3.add(new Book(3l, "5", "哲学,爱情", 88, "12312312"));
        books3.add(new Book(3l, "6", "123vsdf23", 889, "12asd312312"));

        author.setBooks(books1);
        author1.setBooks(books2);
        author2.setBooks(books3);
        author3.setBooks(books3);

        List<Author> authors = new ArrayList<>(Arrays.asList(author, author1, author3, author2));

        return authors;
    }


    public static void test1() {
        Integer[] arr = {1, 1, 2, 3, 4, 5, 6};
        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream1 = Stream.of(arr);
        stream.forEach(item -> {
            System.out.println(item);
        });
        stream1.forEach(item -> {
            System.out.println(item);
        });

    }

    // 打印所有作家的姓名
    public static void test02() {
        List<Author> list = getAuthor();

        list
                .stream() // 转换流
                .map(item -> item.getName()) //将list中集合的每一个元素替换称为 list.getName
                .forEach(item -> { // 遍历，打印输出
                    System.out.println(item);
                });

    }

    public static void test03() {
        List<Author> author = getAuthor();
        author.stream()  // 转换流
                .map(item -> item.getName()) //对象映射
                .distinct() //去重
                .forEach(item -> { //  遍历
                    System.out.println(item);
                });
    }

    //对流中的元素按照年龄进行降序排序，且不要求不能有重复元素
    @Test
    public void test04() {
        List<Author> author = getAuthor();
        author.stream().map(item -> item.getAge()).distinct().sorted().forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    //        对流中的元素按照年龄进行降序排，并且要求不能有重复的元素，然后打印其中年龄最大的两个作家的姓名
    public void test05() {

        List<Author> author = getAuthor();
        author.stream()
                .distinct() // 去重
                .sorted((o1, o2) -> o2.getAge() - o1.getAge()) // 排序，自定义排序规则
                .limit(2) // 设置的流的长度
                .forEach(item -> {  // 遍历
                    System.out.println(item.getName());
                });
    }

    @Test
//    	打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄进行排序
    public void test06() {
        List<Author> author = getAuthor();
        author.stream() //转换流
                .distinct() // 去重
                .sorted((o1, o2) -> o2.getAge() - o1.getAge()) //  自定义规则按照年龄排序
                .skip(1) // 跳过第一条
                .forEach(item -> { //遍历
                    System.out.println(item.getName());
                });
    }

    @Test
//    打印所有书籍的名字，要求对重复的元素进行去重
    public void test07() {
        List<Author> author = getAuthor();
        author.stream() // 转换流
                .flatMap(item -> item.getBooks().stream())
                .distinct() // 去重
                .forEach(item -> System.out.println(item));
    }

    @Test
    //        打印现有数据的所有分类。要求对分类去重，不能出现这样的格式，：哲学，爱情
    public void test08() {
        List<Author> author = getAuthor();
        author.stream() // 转换流
                .flatMap(item -> item.getBooks().stream()) // 替换流 ，将 作者流替换成为书籍流
                .distinct() //去重
                // 再次替换，将book中的分类以 ,为分割转换为数组再转换称为流，然后替换书籍流，得到分类流
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct() // 去重
                .forEach(item -> { //遍历
                    System.out.println(item);
                });

    }

    @Test
    //	输出所有作家的名字
    public void test09() {
        List<Author> author = getAuthor();
        author.stream().forEach(e -> System.out.println(e.getName()));
    }

    @Test
//    打印这些作家所输出的书籍的数目，注意删除重复元素
    public void test10() {
        List<Author> author = getAuthor();

        long count = author
                .stream() //转换流
                .distinct() // 去重
                .flatMap(e -> e.getBooks().stream()) // 流替换
                .distinct() // 去重
                .count(); // 获取流总数
        System.out.println(count);

    }

    //    	分别获取这些作家所出书籍的最高分和最低分
    @Test
    public void test11() {
        List<Author> author = getAuthor();
        // 获取最大值
        Optional<Integer> max = author
                .stream()
                .distinct()
                .flatMap(item -> item.getBooks().stream())
                .map(item -> item.getScore())
                .distinct()
                .max((o1, o2) -> o1 - o2);
        System.out.println(max);
        // 获取最小值
        Optional<Integer> min = author
                .stream()
                .distinct()
                .flatMap(item -> item.getBooks().stream())
                .map(item -> item.getScore())
                .distinct()
                .min((o1, o2) -> o2 - o1);
        System.out.println(min);

    }

    //    	获取一个存放所有作者名字的list集合
    @Test
    public void test12() {
        List<Author> author = getAuthor();
        List<String> collect = author
                .stream() //转换流
                .map(item -> item.getName()) // 映射名字
                .collect(Collectors.toList()); // 流转换称为 list集合
        collect.forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
//    获取一个所有书名的set集合
    public void test13() {
        List<Author> author = getAuthor();
        Set<String> collect = author
                .stream() // 转换流
                .flatMap(item -> item.getBooks().stream()) //  书籍流替换
                .map(item -> item.getName()) // 书籍名 隐射 书籍对象
                .collect(Collectors.toSet()); // 转化为set 集合
        collect.forEach(item -> System.out.println(item));


    }

    @Test
    public void test14() {
        List<Author> author = getAuthor();
        Map<String, List<Book>> map = author
                .stream() // 转换流
                .collect(Collectors.toMap(author1 -> author1.getName(), ahthor2 -> ahthor2.getBooks())); // 流转map
        System.out.println(map);
    }

    @Test
//    判断是否有29岁以上的作家
    public void test15() {
        List<Author> author = getAuthor();
        boolean result = author
                .stream() // 转换流
                .anyMatch(item -> item.getAge() > 29); // 判断
        System.out.println(result);

    }

    @Test
//    判断所有作家是否都是成年人
    public void test16() {
        List<Author> author = getAuthor();
        boolean result = author.stream().allMatch(item -> item.getAge() > 18);
        System.out.println(result);
    }

    @Test
//    判断作家是否都没有超过100岁
    public void test17() {
        List<Author> author = getAuthor();
        boolean result = author.stream().noneMatch(item -> item.getAge() > 100);
        System.out.println(result);
    }

    @Test
//   获取任意一个大于18的作家，如果存在就输出他的名字
    public void test18() {
        List<Author> author = getAuthor();
        Optional<Author> any = author
                .stream() // 转换流
                .filter(e -> e.getAge() > 18) // 条件过滤
                .findAny(); // 随机获取
        // 判断是否为空
        any.ifPresent(e -> {
            System.out.println(e);
        });

    }

    @Test
//   获取任意一个大于18的作家，如果存在就输出他的名字
    public void test19() {
        List<Author> author = getAuthor();
        Optional<Author> first =
                author.stream() // 转换流
                        .sorted((o1, o2) -> o1.getAge() - o2.getAge()) // 按照年龄排序
                        .findFirst(); // 获取第一个元素
        first.ifPresent(e -> System.out.println(e)); // 打印
    }

    //    **使用reduce求所有作者的年龄的和**
    @Test
    public void test20() {

        List<Author> author = getAuthor();
        Integer reduce = author.stream()
                .distinct() // 去重
                .map(item -> item.getAge()) // 映射年里
                .reduce(0, (result, element) -> result + element);
        System.out.println(reduce);
    }

    //**使用reduce求所有作者中年龄最大的**
    @Test
    public void test21() {
        List<Author> author = getAuthor();
        Integer reduce = author
                .stream() // 转换流
                .map(item -> item.getAge()) // map 映射
                // 获取流中年龄最大的作者
                .reduce(Integer.MIN_VALUE, (result, element) -> {
                    System.out.println(result + "--" + element);
                    return result < element ? element : result;
                });
        System.out.println(reduce);
    }

    @Test
    public void test22() {
        List<Author> author = getAuthor();
        Integer reduce = author
                .stream() // 转换流
                .map(item -> item.getAge()) // map 映射
                // 获取流中年龄最小的作者
                .reduce(Integer.MAX_VALUE, (result, element) -> {
                    return result > element ? element : result;
                });
        System.out.println(reduce);

    }

    public Optional<Author> getAuthorObject(){
        ArrayList<Book> books3 = new ArrayList<>();
        books3.add(new Book(3l, "5", "哲学,爱情", 88, "12312312"));
        books3.add(new Book(3l, "6", "123vsdf23", 889, "12asd312312"));

        return  Optional.ofNullable(new Author(1l,"张三",120,"123",books3));
    }

    @Test
    public void test23() {
        Optional<Author> authorObject = getAuthorObject();
        Optional<List<Book>> books = authorObject.map(item -> item.getBooks());
        books.ifPresent(item-> System.out.println(item));

    }

}