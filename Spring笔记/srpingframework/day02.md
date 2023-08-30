## 数据源对象管理

第三方资源配置管理

### 管理第三方bena案例

```xml
<!--   管理第三方的bean 管理druid 数据库连接池的bean
        druid 数据库连接对象需要四个参数 在管理bean的时候使用setter注入
-->
    <bean id="dataSou" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="jdbc:mysql://localhost:3306/student"></property>
        <property name="username" value="root"></property>
        <property name="password" value="123456"></property>
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
    </bean>
```

```java
       DataSource dataSou  = (DataSource) ctx.getBean("dataSou");
        System.out.println(dataSou);
```

### 加载properties 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd
">
<!--    1:开启context命名空间
                xmlns="http://www.springframework.org/schema/beans"   自带配置文件
               xmlns:context="http://www.springframework.org/schema/context"  //自己添加的进行修改就可以了
                [http:] 这些信息是配置文件默认的 将其中的benas改成 context 就可以了
                http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd

        2:使用context命名空间加载properties文件
        <context:property-placeholder/>标签中的localhost属性是用来选全那个properties文件
         <context:property-placeholder  location="jdbc.properties"></context:property-placeholder>

         3:使用${}读取配置文件
-->
    <context:property-placeholder  location="jdbc.properties"></context:property-placeholder>
    <bean id="dataSou" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${uri}"></property>
        <property name="username" value="${user}"></property>
        <property name="password" value="${password}"></property>
        <property name="driverClassName" value="${Driver}"></property>
    </bean>
</beans>
```

**避免读取的系统变量**

```xml
<!--
system-properties-mode="NEVER"
此属性表示不加载系统变量
--> 
<context:property-placeholder  location="classpath*:*.properties" system-properties-mode="NEVER"></context:property-placeholder>
```

可以读取多个properties文件 格式1

```xml
<context:property-placeholder  location="jdbc.properties,jdbc2.properties"></context:property-placeholder>
```

可以读取多个properties文件 格式2

```xml
    <context:property-placeholder  location="*.properties"></context:property-placeholder>
```

**规范的读取自己工程中的properties文件**

```xml
 <context:property-placeholder  location="classpath:*.properties"></context:property-placeholder>
```

**规范的读取所有工程properties文件**

```xml
 <context:property-placeholder  location="classpath*:*.properties"></context:property-placeholder>
```

###  容器

- 创建容器
- 获取bean
- 容器类层次结构
- BeanFactory

### 创建容器的两种方式

- 使用相对路劲
- 使用绝对路径

```java
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("相对路劲");
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("绝对路径");
```

### 获取bean

- java语法强转
- spring转换
- spring寻找对应bean

```java
   //1:强制转换
        DataSource dataSou  = (DataSource) ctx.getBean("dataSou");
        //2:让spring帮我们转换
        DataSource dataSource = ctx.getBean("dataSou",DataSource.class);
        //3:自动装配 直接把类型给spring 让spring在容器中自己找 注意:此方法bean在容器必须是唯一的
        DataSource dataSource1 = ctx.getBean(DataSource.class);
        System.out.println(dataSource1);
```

### 容器总结

![](C:\Users\aidong\Desktop\spring笔记\img\bean配置图片.PNG)





### 注解开发

- 注解开发定义bean
-  纯注解开发

### 使用@Component注解定义bean

```java
//定义一个BookDaoImpl类型的bean  只能通过类型来获取bean   此种方法要注意bean必须在ioc容器中必须唯一
@Component
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("bookdao,....");
    }
}

//定义一个BookDaoImpl类型的bean id名字为bookDao 可以通过类型or id获取bean 
@Component（"bookDao"）
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("bookdao,....");
    }
}

	//获取bean的两种方式
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appliactionContext.xml");
       BookDaoImpl bookDao =  context.getBean(BookDaoImpl.class);
        BookDaoImpl bookDao1 = (BookDaoImpl) context.getBean("bookDao");
        bookDao.save();
        bookDao1.save();
    }

```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
             http://www.springframework.org/schema/context
             http://www.springframework.org/schema/context/spring-context-4.0.xsd
              ">
        <context:component-scan base-package="com.show"/>
</beans>
<!--
        <context:component-scan base-package="com.show"/>
		进行包扫描，要扫描ioc容器才知道使用哪些使用容器注册了bean
-->
```

### @Component的衍生注解

**为了让我们开发人员对bean的分类更加好识别**

1. @Controller  用于定义表现层的bean

2. @Service     用于定义业务层的bean

3. @Repository   用于定义数据层的bean

   

### 纯注解开发

- spring3.0升级了纯注解开发模式，使用了java类替代了配置文件，开启了spring快速开发赛道
- java类代替了Spring核心配置文件

### 配置类代替配置文件

**@Configuration用于设定当前类为配置类**

**@ComponentScan注解用于设定扫描路劲，此注解只能添加一次，多个数据请用数组格式**

```
@ComponentScan({("com.show.dao","com.show.serive"})
```

创建一个config包，包下创建一个SpringConfig类使用@Configuration注解让这个类代替了配置文件

使用@ComponentScan("com.show") 注解代替了    <context:component-scan base-package="com.show"/> 包扫苗

```java
package com.show.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.show")
public class SpringConfig {
}

```

```java
//纯注解开发 获取bean的方法是一样的 但是需要修改实现类由原来的    ClassPathXmlApplicationContext 修改为AnnotationConfigApplicationContext。
//AnnotationConfigApplicationContext 的构造函数需要加载Spring配置文件类
public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(com.show.config.SpringConfig.class);
       	BookDaoImpl bookDao =  context.getBean(BookDaoImpl.class);
        BookDaoImpl bookDao1 = (BookDaoImpl) context.getBean("bookDao");
        bookDao.save();
        bookDao1.save();
    }
```

### 注解：管理bean生命周期

**管理bean是否是单列**

使用注解@Scope注解 ： 两属性

- singleton 单例
- prototype 非单例

```java
@Component("bookDao")
@Scope("prototype")  //设置为非单例bean
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("bookdao,....");
    }
}

```

**管理bean创建和销毁时**

随便定义两个个方法

使用@PostConstruct注解 定义一个方法为初始化方法

使用@PreDestroy注解 定义一个方法为销毁时方法

```java
@Component("bookDao")
@Scope("prototype")  //设置为非单例bean
public class BookDaoImpl implements BookDao {
    public void save() {
        System.out.println("bookdao,....");
    }
    @PostConstruct
    public void init(){
        System.out.println("init");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
```



### 注解：依赖注入引用类型

自动装配：spring注解开发是为了加速开发，对原始功能做了阉割，做了最快最好用的方法

**如果在依赖注入时，有两个类型一样的bean可以使用按名称注入的 语法如下：**

```java
   @Autowired 
   @Qualifier("beanName")
    private BookDaoImpl bookDao;
//注意@Qualifier无法单独使用，需要依赖@Autowired注解
```

**使用@Autowired注解开启自动装配模式（按类型）**

```java
@Component
public class StudentDaoImpl implements StudentDao {
   @Autowired //自动装配，不需要setter方法
    private BookDaoImpl bookDao;
    public void save() {
        System.out.println("Student save .....");
        bookDao.save();
    }
}
//当ioc容器中存在BookDaoImpl类型的bean且是唯一的时候系统会通过类型自动装配
```

**注意**

- 注意：自动装配基于反射设计创建对象并暴力反射对应属性为私有属性初始化数据。因此无需提供setter方法
- 注意：自动装配建议使用无参构造方法创建对象（默认），如果不提供对应构造方法，请提供唯一构造方法

### 注解：依赖注入简单类型

使用@Value进行简单类型依赖注入

**现在是直接注入**

```java
   @Value("aidongdong")
   private String name;
```

**使用@Value进行读取properties文件 **

需要在配置文件类SpringConfigf类中添加注解PropertySource（{”文件名称1“，”文件名称2“}）

```java
@Configuration //定义为配置文件
@ComponentScan("com.show") //进行定义bean的扫描
@PropertySource("jdbc.properties") //进行对properties文件的扫描，取出格式为 @Value("${name}")
public class SpringConfig {
}
```

获取properties文件中的数据

```java
   @Value("${name}")
   private String name;
```





### 注解：管理第三方bean；

1. 再config包下创建JdbcConfig类 创建对象进行管理

```java
package com.show.config;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
public class JdbcConfig {
    //1：管理第三bean由于不能直接写代码在第三方上，只能自己先创建出来再进行管理
    //2：定义一个方法获得要管理的对象
    @Bean //6：将返回的bean交给ioc阿荣旗管理
    public DataSource dataSource(){
        //3：创建连接对象
        DruidDataSource dataSource = new DruidDataSource();
        //4：给连接对象赋初始值
        dataSource.setUsername("root");     dataSource.setUrl("jdbc:mysql://localhost:3306/student");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //5：将初始化好的对象返沪
        return dataSource;
    }
}

```

在jdbcConfig配置文件中创建好了要管理的第三方bean 然后在SpringConfig进行用@Import（{JdbcConfig.class,.....}）进行导入

### 第三方bean管理时依赖其他bean怎么办

**简单类型**

```JAVA
public class JdbcConfig {
    //简单类型
    @Value("root")
    private String name;
    @Value("jdbc:mysql://localhost:3306/student")
    private String password;
    @Value("123456")
    private String url;
    @Value("com.mysql.jdbc.Driver")
    private String driver;

    //1：管理第三bean由于不能直接写代码在第三方上，只能自己先创建出来再进行管理
    //2：定义一个方法获得要管理的对象
    @Bean //6：将返回的bean交给ioc阿荣旗管理
    public DataSource dataSource(){
        //3：创建连接对象
        DruidDataSource dataSource = new DruidDataSource();
        //4：给连接对象赋初始值
        dataSource.setUsername(name);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        //5：将初始化好的对象返沪
        return dataSource;
    }
}
```

**引用类型**

```java
 public DataSource dataSource(BookDaoImpl bookDao){  //引用类型注入，直接接将需要的对象放在形参类别即可，但是必须是ioc容器中存在的
        bookDao.save();
        }
```

### 使用spring整合mybatis

1. 在config包下创建MybatisConfig配置类

   ```java
   package com.show.config;
   
   import org.apache.ibatis.session.SqlSessionFactory;
   import org.mybatis.spring.SqlSessionFactoryBean;
   import org.mybatis.spring.mapper.MapperScannerConfigurer;
   import org.springframework.context.annotation.Bean;
   
   import javax.sql.DataSource;
   public class MybatisConfig {
       //这个对象表示Mybatis的配置文件
       @Bean //创建sqlSessionFactoryBean
       public SqlSessionFactoryBean sqlSessionFactoryBean (DataSource dataSource){ //DataSource 对象是整合好了的JDBC对象直接使用spring的第三放管理bena的依赖注入就可以了
           SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
           sqlSessionFactoryBean.setTypeAliasesPackage("com.show.dao"); //
           sqlSessionFactoryBean.setDataSource(dataSource); //将prototype文件数据转换为DataSource对象
           return sqlSessionFactoryBean;
       }
       //这个对象表示mapper
       @Bean
       public MapperScannerConfigurer mapperScannerConfigurer(){
           MapperScannerConfigurer msc = new MapperScannerConfigurer();
           msc.setBasePackage("com.show.dao"); //为什么无法直接找到映射的mapper.xml文件，但是注解就可以
    //告示mapper你的映射文件在那个位置        msc.setBasePackage("");
           return msc;
       }
   
   }
   
   ```

   
