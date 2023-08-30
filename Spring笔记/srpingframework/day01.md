### 导读

###### 为什么要学

- Spring技术是javaEE开发必备技能，企业开发技术选型命中率》90
- 专业角度
  - **简化开发**，降低企业级开发的复杂性
  - **框架整合**，高效整合其他技术，提高企业级应用开发与运行效率

###### 学什么？

- 简化开发

- **IOC**

- **AOP**

  - 事务处理

- 框架整合

  - MyBatis
  - MyBatis-plus
  - Strtu
  - ......

  ######  怎么学

  - 学习Spring框架设计思想
  - 学习基础操作，思考操作和思想思想间的联系

### 初认Spring

- 了解Spring家族
- 了解Spring发展史

### Spring  Framewoek 系统架构

- Spring FrameWork 是Spring 生态圈中最基础的项目，是其他项目的根基

-  一：Core Container:核心容器 此容器是用来装对象的

- 二：AOP

- ```powershell
  AOP：面向切面编程
  和面向对象编程一样是一种编程思想
  具体aop做什么的：它可以在不惊动原始程序的基础上增强功能
  ```

- 二：Aspects

- ```powershell
  Aspects ： aop 思想实现
  Aspects 其实就是做的好的aop依赖包，因为技术好被Spring收入技术栈
  ```

- 三：data Access ：数据访问

- ```powershell
  做数据层相关技术
  ```

  

- 三：data  Access/Integration 数据集成

- ```powershell
  Spring内部已经提供了数据访问层的技术
  Access 是内部自己提供的数据访问层技术
  Integration 可以自己使用其他的数据访问层的框架，集成使用，也就是Spring的框架整合
  
  ```

- 三:Transactions事务 **重点学习**

- 四:web 靠开发

- 五：Test 单元测试与集成测试

### Spring Framework 学习路线

- ###### 第一部分 ：核心容器

- 基本概念 IOC  DI

- 容器的基本操作

- ###### 第二部分：整合

- 整合数据层技术MyBatis

- ###### 第三部分：AOP

- 核心概念

- AOP基础操作

- AOP实用开发

- ######  第四部分：事务

- 事务实用开发

### 核心概念 

##### IOC

- 书写代码现状

  - 耦合度太高了

- 解决方案

  - 在使用对象时，在程序中不要主动使用new 产生对象，转为用外部提供对象

- ##### IOC（Inversion of Control ）控制反转

  - 对象的创建控制权由程序转移到外部，这种思想
  - 使用对象时，由主动new产生对象转换为由**外部**提供对象，此过程中对象创建控制权由程序转移到**外部**，此思想成为i控制反转
  - Spring技术对IOC思想进行了实现
    - Spring提供了一个容器，称为**IOC容器**，用来充当IOC思想中的**外部**
    - IOC容器负责对象的创建，初始化等一系列工作，被用来创建或管理的对象在IOC容器中被称为**Bean**

```java
/*
自己总结
业务层在写代码的时候会使用数据实现层的对象来调用方法获取数据
但是，如果我想要换一个数据层的事项方法，就需要重新在业务层重新编写代码
，业务层如果只是需要换数据层的方法实现的话，就只需要更改一下new的数据层的对象就好了，
者一行太繁杂了，需要重新编译代码，上线，部署
代码的耦合度太高了
在使用对象时，在程序中不要主动使用new 产生对象，转为用外部提供对象
所有Spring 提供了s思想，你不是就要一个对象吗，所以，对象就用Spring的核心容器来管理就好  
*/

```

#### DI（Dependency Injection）依赖注入

- 在容器中建立的Bean与Baen之间依赖关系的整个过程，称为依赖注入

- ```java
  /*
  在业务层创建Servlet层的对象 而Serlvet层中由DAO层的对象
  如果IOC容器也存在DAO层的对象，就会一起打包好称为一个Bean给你使用，这种行为被称为依赖注入
  */
  ```


#### 目标：

充分解耦

- 使用IOC容器管理Bean（IOC）
- 在IOC容器内将由依赖关系的Bean进行关系绑定(DI)

最终效果

- 使用对象时不仅可以直接从IOC容器中获取，并且取到的bean已经绑定所有的依赖关系	





### IOC入门案例思路分析

1. 管理什么？（Service与Dao）

2. 如何将被管理的对象告知IOC容器（配置）

3. 被管路的对象交给IOC容器，如何获取到IOC容器？（接口）

4. IOC容器得到后，如何从容器中获取Bean？（接口方法）

5. 使用Spring导入哪些坐标？（pom.xml）

   ```xml
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-context</artifactId>
         <version>5.1.5.RELEASE</version>
       </dependency>
   ```

   

5. 编写配置文件

6. ```xml
   <!--创建配置文件appliactionContext.xml-->
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   <!--   1： 导入Spring 坐标
       <dependency>
         <groupId>org.springframework</groupId>
         <artifactId>spring-context</artifactId>
         <version>5.1.5.RELEASE</version>
       </dependency>
       -->
   <!--2: 配置bean
   class 属性写具体实现类
   id取名字-->
       <!--id不能重名-->
       <bean id="bookDao1" class="com.show.dao.impl.BookDaoImpl"></bean>
       <bean id="bookService1" class="com.show.service.impl.BookServiceImpl"></bean>
   </beans>
   ```
   
8. 获取bean 对象

9. ```java
   package com.show;
   
   import com.show.dao.BookDao;
   import com.show.service.BookService;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;
   
   public class App {
       public static void main(String[] args) {
           //1: 获取AOC容器
           ApplicationContext ctx = new ClassPathXmlApplicationContext("appliactionContext.xml");
           //2:获取对象  getbean方法的参数为配置bean的id
           BookDao bookDao1 = (BookDao) ctx.getBean("bookDao1");
           bookDao1.save();
           BookService bookService1 = (BookService) ctx.getBean("bookService1");
           bookService1.save();
       }
   }
    
   ```

### DI入门案例

1. 基于IOC管理bean
2. Service中使用new形式创建DAO对象是否保留？（否）
3. Service中需要的DAO对象如何进入到Service中？（提供方法）
4. Service与Dao间的关系如何描述？（配置）

```xml
<!--在xml文件中配置属性 等于是在xml中创建bookService1对象调用对象的bookDao属性的set方法如何将id=bookDao1的bean赋值给对象的bookDao属性-->
<bean id="bookDao1" class="com.show.dao.impl.BookDaoImpl"></bean>
    <bean id="bookService1" class="com.show.service.impl.BookServiceImpl">
<!--        配置Service和dao的关系
            property表示配置当前并的属性
            name 属性表示配置哪一个具体的属性
            ref表示参照哪一个bean
-->
        <property name="bookDao" ref="bookDao1"></property>
    </bean>
```

```java
package com.show.service.impl;

import com.show.dao.BookDao;
import com.show.dao.impl.BookDaoImpl;
import com.show.service.BookService;

public class BookServiceImpl implements BookService {
    //删除业务层中使用new 的方式创建对象
    BookDao bookDao ;
    //提供对于的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    public void save() {
        System.out.println("book service save");
        bookDao.save();
    }
}
```

### Bean配置

- bean基础配置

| 类别     | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| 名称     | bean                                                         |
| 类型     | 标签                                                         |
| 所属     | beans标签                                                    |
| 功能     | 定义spring核心容器管理的对象                                 |
| 格式     | <beans>   <bean/> or<bean><bean>  </beans>                   |
| 属性列表 | id：bean的id ，使用容器可以通过id值获取对应的bean，在一个容器中id唯一 <br />class：bean的类型，即配置的bean的全名路径 |
| 范例     | <bean id="bookDao1" class="com.show.dao.impl.BookDaoImpl"></bean> |

- bean别名配置

| 类别 | 描述                                                         |
| ---- | ------------------------------------------------------------ |
| 名称 | name                                                         |
| 类型 | 属性                                                         |
| 所属 | bean标签                                                     |
| 功能 | 定义bean别名，可以定义多个，使用逗号（，）分号(;)空格（）分割 |
| 范例 | <bean id="bookDao1" name="bookDao3;bookDao2" class="com.show.dao.impl.BookDaoImpl"></bean> |

**注意**获取bean无论是通过id 或是 name 获取 ，如果无法获取到会抛出异常，NoSuchBeanfininitionExection

- bean作用范围配置  	

| 类别 | 描述                                                         |
| ---- | ------------------------------------------------------------ |
| 名称 | scope                                                        |
| 类型 | 属性                                                         |
| 所属 | bean标签                                                     |
| 功能 | 定义bean的作用范围，可以范围入下<br />singleton：单例（默认）<br />prototype：菲单列 |
| 范例 |                                                              |

**bean作用范围说明**

- 为什么bean默认为单例？、
- 适合交给容器进行管理的bean
  - 表现层对象
  - 业务层对象
  - 数据层对象
  - 工具对象
- 不适合交给容器进行管理的bean
  - 封装实体的域对象

### bean实例化

**bean是如何创建出来的**

- bean本质上就是对象，创建bean使用构造方法完成

实例化bean的三种方式

- spring创建bean的时候是使用是构造方法来创建的，如果没由无参构造方法是会报错的 beanCreationException



### bean实例化方式二   

静态工厂生产的对象如何被spring管理

静态工厂产生对象，可能会需要配置一些参数，所有可能有些项目强制要求	

1. 先编写配置文件xml

   ```xml
   <!--    spring 管理静态工厂产生的对象
           class 定位静态工厂类
           factory 定位在静态工厂类中由哪一个方法进行产生对象给spring
       -->
       <bean id="orderBook" class="com.show.Factory.BookDaoFactory" factory-method="getBookDaoImpl"></bean>
   ```

2. 在 测试类中测试

   ```java
     //静态工厂获取对象
   //        BookDao bookDao = BookDaoImpl.getBookDaoImp();
   //        bookDao.save();
           //spring管理通过静态工厂生产的bean
           BookDao orderBook = (BookDao) ctx.getBean("orderBook");
           orderBook.save();
   ```

### bean实例化方式三

spring管理实例化工厂的bean

1.  修改xml配置文件

   ```xml
   <!--   方式三：spring管理实例化bean
           步骤1： 创建工厂bean       <bean class="com.show.Factory.BookDaoFactory" id="BookDaoFactory"></bean>
           步骤2：创建需要使用的bean 不需要class属性    <bean  factory-bean="BookDaoFactory"  factory-method="getBookDaoImpl"></bean>
           步骤3: factory-bean  依赖与工厂的bean
           步骤4：factory-method 使用工厂中产生对象的方法
           -->
   <!--
    总结：实例化工厂模式，我们需要先创建一个工厂的bean 然后再创建一个我使用的bean 我们使用的bean 不需要class属性
   使用factory-bean：指向工厂bean      factory-method：属性由工厂bean的哪一个方法来创建对象
   -->
       <bean class="com.show.Factory.BookDaoFactory" id="BookDaoFactory"></bean>
       <bean id="userDao"  factory-bean="BookDaoFactory"  factory-method="getBookDaoImpl"></bean>
   ```

2. 获取对象

   ```java
   //        //实例化共产产生对象
   //        //1:创建工厂对象
   //        BookDaoFactory bookDaoFactory  = new BookDaoFactory();
   //        //2：通过工厂对象获取实例化对象
   //        BookDaoImpl bookDaoImpl = bookDaoFactory.getBookDaoImpl();
   //        //3:调用方法
   //        bookDaoImpl.save();
           BookDao userDao = (BookDao) ctx.getBean("userDao");
           userDao.save();
   ```

### bean实例化方式三2

使用实例化工厂创建bean

```java
package com.show.Factory;

import com.show.dao.BookDao;
import com.show.dao.impl.BookDaoImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * 实例化对象工厂 只需要将需要创建实例化的bean 写在FactoryBean接口的泛型中
 * 然后实例其接口方法
 */
public class UserDaoFactoryBean implements FactoryBean<BookDao> {
   // 在getObject方法中 将需要的实例化对象 new 出出来进行返回
    //代替原始实例工厂中创建对象
    public BookDao getObject() throws Exception {
        return new BookDaoImpl();
    }

    //此方法控制实例化对象是否是单例的
    public boolean isSingleton() {
        return true;
    }
//    getObjectType中将需要创建对象的 Object.Calss 文件字节码返回
    public Class<?> getObjectType() {
        return BookDao.class;
    }
}

```

```xml
<!--    方式四 使用factoryBean
        使用了FactoryBean进行实例化工厂创建对象的话，在xml文件中的配置 和正常的bean用构造方法创建的配置一样
        只需要将class 属性指向 实例
-->
    <bean class="com.show.Factory.UserDaoFactoryBean" id="userDaoFactoryBean"></bean>
```



### bean的生命周期

- 生命周期：从创建到销毁的整个过程
- bean生周期：bean从创建到销毁的整体过程
- bean生命周期控制：在bean创建后到销毁前做一些事情

```java
/*
 spring bean的执行过程
 初始化容器
 １：创建对象（内存分配）
 ２：执行构造方法
 ３：执行属性注入（set操作）
 使用bean
 1：执行业务操作
 关闭/销毁容器
 1：执行bean销毁方法
*/
```

**给bean绑定初始化是执行方法  和销毁时执行方法**

```xml
<!-- init-method 属性绑定初始化方法 方法名随便写  
	destroy-method 属性绑定销毁前方法 方法随便写
-->
<bean class="com.show.dao.impl.BookDaoImpl" id="dao" init-method="init" destroy-method="destory"></bean>


```

```java

//<!--使用spring定义的规则在实现初始化方法和销毁方法-->
//实现对应的接口 重写其方法，spring会自己调用其方法，就不需要进行编写配置文件了
public class BookDaoImpl implements BookDao, InitializingBean, DisposableBean {
    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() throws Exception {

    }
```



### IOC容器关闭的两种方式

```java

      //1: 获取AOC容器
		//ApplicationContext 接口是没有close（）； 和registerShutdownHook（）； 
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appliactionContext.xml");
        //注册关闭钩子,使用此方法是告诉java虚拟机在虚拟机关闭前，将IOC容器前关闭 所以使用此方法就可以执行到bean的destory
        ctx.registerShutdownHook();
		//暴力关闭
    	 ctx.close();
```



## 依赖注入

### 依赖注入的方式

思考：向一个类传递数据的方式有几种

- 普通方法（set 方法）
- 构造方法

思考：依赖注入描述了在容器中建立bean与bean之间依赖关系过程，如果bean运行需要的是数字或字符串呢？

- 引用类型
- 简单类型（基本数据类型与String）

依赖注入的方式

- setter注入
  - 简单类型
  - 引用类型
- 构造器注入
  - 简单类型
  - 引用类型

### **简单类型setter注入   简单注入使用value属性赋值  ** 

```java
  private  int number;
    private String dataBaseName;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

```

```xml
    <bean  class="com.show.dao.impl.BookDaoImpl" id="userDaoFactoryBean" >
     <property name="number" value="10"></property>
        <property name="dataBaseName" value="mysql"></property>
    </bean>
```

### 构造器注入方式

**使用constructor-arg 标签进行构造器注入， 简单数据类型 使用value 赋值  引用数据类型使用ref进行绑定**

**方式1  使用name属性进行依赖注入**

```xml
   <!--
     <constructor-arg name="dataBaseName" value="mysql"/>
		   <constructor-arg/> 是构造参数  
			name 属性对应着构造器中的Object obj 数据类型名字的obj  
			value 属性是给构造器简单属性进行赋值
   <constructor-arg name="number" value="10"></constructor-arg>
-->
<!--
//此种方式进行依赖注入的话，配置文件中的   <constructor-arg name="number" value="10"></constructor-arg> name属性需要和构造器中的形参名称一致，导致耦合性很高
-->
<bean class="com.show.dao.impl.StudentDaoImpl" id="studentDao"></bean>
    <bean  class="com.show.dao.impl.BookDaoImpl" id="userDaoFactoryBean" >
     <constructor-arg name="dataBaseName" value="mysql"/>
     <constructor-arg name="number" value="10"></constructor-arg>
    </bean>
```

**方式二：使用type属性进行依赖注入**

```xml
<!--
使用type属性 是根据构造器的形参列表的数据类型来进行匹配的，如果有两个一样的属性就不行了
-->    
<bean  class="com.show.dao.impl.BookDaoImpl" id="userDaoFactoryBean" >
        <constructor-arg type="java.lang.String" value="mysql"/>
        <constructor-arg type="int" value="10"></constructor-arg>
        <constructor-arg type="com.show.dao.StudentDao" ref="studentDao"/>
    </bean>
```

**方式三：使用index属性近行依赖注入**

```xml
<!--
使用index属性进行依赖注入，是根据是构造器的形参列表的顺序来进行依赖注入
-->    
<bean class="com.show.dao.impl.StudentDaoImpl" id="studentDao"></bean>
    <bean  class="com.show.dao.impl.BookDaoImpl" id="userDaoFactoryBean" >
        <constructor-arg index="0" value="10"/>
        <constructor-arg index="1" value="mysql"></constructor-arg>
        <constructor-arg index="2" ref="studentDao"/>
    </bean>
```

**java构造器代码**

```java

public class BookDaoImpl implements BookDao {
    private  int number;
    
    private String dataBaseName;

    private StudentDao studentDao;
	
    public BookDaoImpl(int number, String dataBaseName, StudentDao studentDao) {
        this.number = number;
        this.dataBaseName = dataBaseName;
        this.studentDao = studentDao;
    }
}

```

### 	依赖注入的方式选择

1. 强制依赖使用构造器进行，使用setter注入有概率不进行注入导致null对象出现
2. 可选依赖使用setter注入进行，灵活性强
3. Spring框架提倡使用构造器，第三方框架内部大多数采用构造器注入的形式进行数据初始化，相对严谨
4. 如果有必要可以两种同时使用，使用构造器注入完成强制依赖注入，使用setter注入完成可选依赖注入
5. 实际开发中根据实际情况分析，如果受控对象没有提供setter方法就必须使用构造器注入
6. **自己开发模块推荐使用setter注入**



###  依赖主动装配

- Ioc容器根据bean所依赖的资源在容器中自动查找并注入到bean中的过程称为自动装配
- 自动装配的方式
  -  按类型（常用）
  - 按名称
  - 按构造方法
  - 不启用自动装配

**按类型自动装配**

```xml
<!--
自动装配是在容器中寻找，所以如果在ioc容器中没有这个bean 就无法找到，就无法装配
-->
<bean class="com.show.dao.impl.StudentDaoImpl" id="studentDao"></bean>
    <bean  class="com.show.dao.impl.BookDaoImpl" id="userDaoFactoryBean" autowire="byType" >
    </bean>
```

```java
//自动装配是需要提供setter方法的
public class BookDaoImpl implements BookDao {
    private StudentDao studentDao;
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
```

**自动装配注意事项**	

```powershell
 #注意事项1
 自动装配是需要提供setter方法的不然无法依赖注入
 #注意事项2
 自动依赖注入是要保证被注入的bean在xml文件中是唯一的
 否则会报NoUniqueBeanDefinitionException异常
  异常信息 No qualifying bean of type 'com.show.dao.StudentDao' available: expected single matching bean but found 2: 	studentDao,studentDa1
```

**依赖自动装配特征**

- 自动装配用于引用类型依赖注入，不能对简单类型进行操作
- 使用按类型装配时（byType）必须保障容器中相同类型的bean唯一，推荐使用
- 使用按名称装配时（byName）必须保障容器中具有指定名称的bean，因变量名与配置耦合，不推荐使用
- 自动装配优先级低于setter注入与构造器注入，同时出现时自动装配失效 

### 集合注入

- 数组
- list
- set
- map
- properties

​	java容器

```java
package com.show.dao.impl;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    private int [] array;
    private List<String> list;
    private Set<String> set;
    private Map<String,String> map;
    private Properties properties;

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void show(){
        System.out.println("遍历数组"+Arrays.toString(array));
        System.out.println("遍历list"+list);
        System.out.println("遍历set"+set);
        System.out.println("遍历map"+map);
        System.out.println("遍历propertims"+properties);
    }
}
```

**数据注入**

```xml
 <bean  id="test" class="com.show.dao.impl.Test">
        <property name="array">
            <array>
                <value>100</value>
                <value>100</value>
                <value>100</value>
            </array>
        </property>
        <property name="list">
            <list>
                <value>101</value>
                <value>101</value>
                <value>101</value>
            </list>
        </property>
        <property name="set">
            <set>
                <value>101</value>
                <value>102</value>
                <value>101</value>
            </set>
        </property>
        <property name="map">
            <map>
                <entry key="1" value="q1"></entry>
                <entry key="2" value="q2"></entry>
                <entry key="3" value="q3"></entry>
            </map>
        </property>
        <property name="properties">
            <props>
                <prop key="1">1213</prop>
                <prop key="2">1213</prop>
                <prop key="3">1213</prop>
            </props>
        </property>
    </bean>
```

  
