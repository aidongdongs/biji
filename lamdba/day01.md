# 函数式编程

## 1.概述

### 1.1为什么学？

### 1.2 函数式编程思想

#### 1.2.1 概念

面向对象思想需要关注用什么对象完成什么事情，而函数式编程思想就类似于数学中的函数，它主要关注的是对数据进行了什么操作

#### 1.2.2 优点

- 代码简介，快速开发
- 接近自然语言，易于理解
- 易于  **并发编程**



## 2.lambda表达式

### 2.1 概念

Lambda 是 JDK8 中的一个语法糖，可以看成是一种语法糖，他可以对某些匿是名内部类的写法进行简化，它是函数编程思想的一个重要体现。让我们不需要关注是什么对象，而是更关注我们对数据进行了什么操作

### 2.2  核心原则

可推导可省略

### 2.3  基本格式

```java
(参数列表) -> { 代码 }
```

#### 例子1

我们在创建线程并启动时可以使用匿名内部类

```java
   new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        }).start();
```

可以使用Lambda的格式进行修改，修改后

```java
   new Thread(()->{
            System.out.println("线程中的方法启动了2");
        }).start();
```



#### 例子2

现有方法定义如下，其中IntBinaryOperator是一个接口，先使用匿名内部类写发调用该方法

```java
   	 public static void main(String[] args) { 
         //匿名内部类写法
	int i1 = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i1);
		
		//lambda 表达式写法
        int i = calculateNum((a, b) -> {
            return a + b;
        });
        System.out.println(i);
    }
}

    public static int calculateNum(IntBinaryOperator operator){
        int a =  10 ;
        int b = 20;
        return operator.applyAsInt(a,b);
    }

```

#### 例子3

现有方法定义如下，其中InPredicte是一个接口，使用匿名内部类和lamdba表达式写

```java
public static void main (String [] args){
    	//匿名内部类写法
         printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value%2==0;
            }
        });
    
    	//lamdba写法
    printNum((value)-> {return value%2==0;});
    
    //lamdba 简化写法
          printNum(value-> value%2==0);
    
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
```



![](img/Snipaste_2023-07-12_18-24-11.png)



#### 例子4

```java
//匿名内部类写法
Integer integer = typeCover(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });

//lmadba表达式写法
Integer integer1 = typeCover((String s) -> {
            return Integer.valueOf(s);
        });
        System.out.println(integer1);
    }

public static <R> R typeCover(Function<String,R> function){
        String str  = "12345";
        R result = function.apply(str);
        return result;
    }
```



#### 例子5

```java
  foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        foreachArr((value)->{
            System.out.println(i);
        });
    }

    public static void foreachArr(IntConsumer intConsumer){
        int [] arr = {1,213,141,23,523,423432,456235,1,312};
        for (int i = 0; i <arr.length ; i++) {
            intConsumer.accept(arr[i]);
        }
    }

```



### 2.4  省略规则

- 参数类型可以省略
- 方法体只有一句代码时大括号和return和唯一一句代码的分号可以省略
- 方法只有一个参数时，小括号可以省略









## 3.Stream 流

### 3.1 概述

java8的Stream使用的函数式编程模式。如同它的名字一样，它可以被用来对集合或数组进行链装流式操作，

### 3.2 案例数据准备

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode //
public class Author {
    private Long id;

    private String name;

    private Integer age;

    private String intro;

    private List<Book> books;
}
```

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    private Long id;

    private String name;

    private String category;

    private Integer score;

    private String intro;
}

```

```java
    private static List<Author> getAuthor(){
        Author author = new Author(1l, "鞥第1", 33, "123123123123212",null);
        Author author1 = new Author(2l, "鞥第2", 33, "123123123123212",null);
        Author author2 = new Author(3l, "鞥3", 33, "123123123123212",null);
        Author author3 = new Author(4l, "鞥4", 33, "123123123123212",null);

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
```



### 3.3 快速入门



#### 3.3.1 需求

我们可以调用getAuthor方法获取的list集合，使用stream流进行打印

现需要打印年龄小于18的作家，注意要去重



#### 3.3.2 实现

```java
    public static void main(String[] args) {

        List<Author> author = getAuthor();
        author.stream()  //把集合转换为Stream流
                .distinct()   //去重函数
                .filter(item-> item.getAge()<18) //进行过滤 年龄《18 才获取
                .forEach(itme-> System.out.println(itme)); //打印数据
    }
```



### 3.4 Stream常用操作

#### 3.4.1 创建流

单列集合： 集合对象.stream()

```java
   List<Author> author = getAuthor();
   Stream<Author> stream = author.stream();
```

数组： Ayyays.sream(数组) 或者使用 Stream.of 创建

```java
      Integer [] arr = {1,1,2,3,4,5,6};
        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream1 = Stream.of(arr);
        stream.forEach(item->{
            System.out.println(item);
        });
        stream1.forEach(item->{
            System.out.println(item);
        });
```

双列集合：转换成单列集合后再创建

```java
//创建map集合 
Map<String,Integer> map  = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("蜡笔2新",19);
		//使用map集合的entrySet方法将map集合转换为一个set单列集合
        Set<Map.Entry <String, Integer>> entries = map.entrySet();
        entries.stream().forEach(item->{
            //获取key
            System.out.println(item.getKey());
            //获取value
            System.out.println(item.getValue());
            System.out.println(item.getClass());
        });
```

####  3.4.2 中间操作

##### filter

可以对流中的元素进行条件过滤，符合过滤条件的才能继续留在流中

例如

打印所有名字长度大于1的作家姓名

```java
  author.stream().filter(item->{
            return item.getName().length()>1;
        }).forEach(item->{
            System.out.println(item);
        });
```





#####  map

可以把对流中的元素进行计算或者转换

例如

打印所有作家的姓名

```java
  
//将Stream流中的对象进行转换
  author.stream().map(author1 -> author1.getName() ).forEach(name->{
            System.out.println(name);
        });
```

对作者集合中的数据做运算

```java
      author.stream().map(author1 ->author1.getAge() )
                .map(age->age+10).forEach(item-> System.out.println(item));
```





##### distinct

可以去除流中的重复元素

例如：

打印所有作家的姓名，并且要其中不能有重复的元素

```java
     author
              .stream() //转换流
              .map(author1 -> author1.getName()) //做映射
              .distinct() //去重
         	 . forEach(  //遍历
                      item-> System.out.println(item)
              );
```



**注意：是distinct 方法是依赖于Object中的equals方法来判断对象是否相同的，所有需要重写eqauls方法**
