## AOP简介

- AOP面向切面编程，一种编程范式，指导开发者如何组织程序结构
- OOP面向对象编程
- 作用：在不惊动原始程序设置的基础上对其功能进行增加
- Spring理念：无入侵式、无侵入式

### AOP核心概念

- 连接点（joinPoint）：在程序执行过程中的任意位置，粒度为执行方法，抛出异常，设置变量等
- 切入点（pointcut）：匹配连接的式子
  - 在springAop中，一个切入点只描述一个具体方法，也可以匹配多个方法
    - 一个具体方法：com.show.dao包下的BookDao接口中无形参无返回值的save方法
    - 匹配多个方法：所以save方法,所有的get开头的方法，所以以Dao结尾的接口的任意方法，所以带一个参数的方法
- 通知（Advice）：在切入点处执行操作，也就是共性共能
- 通知类：定义通知的类
- 切面（Asoect）：描述通知与切入点的对应关系

- 方法--》连接点、
- 要追加功能的方法--》切入点
- 抽离出入的功能叫--》通知
- 绑定通知和切入点 --》切面



### 连接点

```java
    //连接点
    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("save ......");
    }

    //连接点
    @Override
    public void update() {
        System.out.println("update......");
    }
/*
连接点就是具体的某一个方法可以被 在通知类中使用@Pointcut注解进行路径锁定的
比如 ：    @Pointcut("execution(void com.show.dao.impl.BookDaoImpl.update())")
*/
```

### 通知类：定义通知的类

```java
@Configuration
@ComponentScan("com.show")
@EnableAspectJAutoProxy //告示spring有用注解开发的aop 开启@Aspect注解
public class SpringConfig {
    
}


@Component //让spring扫描到
@Aspect // 告示spring这个类是做Aop的 

public class MyAdvice {
    
}
```

### 定义切入点

```java
 @Pointcut("execution(void com.show.dao.impl.BookDaoImpl.update())") //绑定具体的方法
    private void pt(){} //定义私有的 无返回值的 无参数的 方法
//定义一个pt方法作为标识 使用@Pointcut注解进行匹配方法
```

### 通知（公共功能）

```java
//通知
    @Before("pt()") //使用Before 将此方法的内容通过 已经绑定好的pt方法 绑定到连接点 update方法
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
```

### 注意

```java
 @Pointcut("execution(void com.show.dao.impl.BookDaoImpl.update())") //绑定具体的方法
//绑定具体方法是通过接口进行绑定的，直接对实现类进行绑定是无法增加功能的
```



### AOP的工作流程

1.  Spring容器启动
2. 读取所有切面配置中的切入点
3. 初始化bean，判定bean对应的类中的方法是否匹配到任意切入点
   - 匹配失败：创建对象
   - 匹配成功： 创建原始对象（目标对象）的代理对象
4. 获取bean执行方法
   - 获取bean，调用方法并执行，完成操作
   - 获取的bean是代理对象时，根据代理对象的运行模式运行原始方法与增强的内容，完成操作

### AOP核心概念

- 目标对象（Target）：原始功能去掉共性功能对应的类产生的对象，这种对象是无法直接完成最终工作的
- 代理（proxy）：目标对象无法直接完成工作，需要对其进行功能回填，通过原始对象的代理对象实现



### AOP切入点表达式

- 语法格式
- 通配符
- 书写技巧

**切入点**：要进行增强的方法

**切入点表达式**：要进行增强的方法的描述方式

1. 描述方式1：执行com.show.dao包下的BookDao接口中的无参的update方法

   ```java
   @execution (void com.show.dao.BookDao.update())
   ```

2. 描述方式2：执行com.show.dao.imp;包下的BookDaoImpl类中的无参的update方法（）

   ```
   @execution (void com.show.dao.impl.BookdaoImpl.update())
   ```

   **切入点表达式标准规则**：动作关键词（访问修饰符 返回值 包名.类名/接口名.方法名(参数) 异常名）

   ```java
   @execution (public User com.show.dao.BookDao.update(int) IoEecutepiton)
   /*
   1:动作关键词：描述切入点的行为，例如execution表示执行切入点
   2：访问修饰符:public private 等，可以省略
   3：返回值
   4：报名
   5：类/接口名
   6：方法名
   7：参数
   8：异常名 可以省略
   */
   ```

   ### 通配符

   - *：单个独立的任意符号，可以独立出现，也可以作为前缀或者后缀的匹配符出现

     ```java
     execution（public * com.show.*。UserService.find(*)）
         // 匹配 com.show包下的任意一个包下的 UserService.find（带一个任意类型参数）方法
     ```
     
   - ..：多个连续的任意符号，可以独立出现，常用于简化包名与参数的书写
   
     ```java
     execution（public USer com..UserService.find(..))
         //匹配com包下的任意包中的UserSerive类or接口中所有方法名称为Find的方法 find方法中的参数为任意类型任意数量
     ```
   
   - +：专用于匹配子类类型
   
     ```
     execution (* *..*Service+.*(..))
     ```
   
     

### Aop切入点表达式书写技巧

- 所有代码按标准规范开发，否则以下技巧全部失效
- 描述切入点**通常描述接口**，而不描述实现类
- 访问修饰符针对接口开发均采用public描述（**可省略访问修饰符描述**）
- 返回值类型对于增删改类使用精准类型加速匹配，对于查询类使用*统配快速描述
- **包名**书写**尽量不使用..匹配**，效率过低，常用*做单个包描述匹配，或精准匹配
- **接口名**/类名书写名称与模块相关的 **采用*匹配**，例如UserService书写成*Service，绑定业务层接口名

### AOP的通知类型

- AOP通知描述了抽取的共性功能，根据共性功能抽取的位置不同，最终运行代码时要将其加入到合理的位置
- AOP通知共分为了5中类型
  1. 前置通知
  2. 后置通知
  3. 环绕通知（**重点**）
  4. 返回后通知（了解）
  5. 抛出异常后通知（了解）



### Before 前置通知

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(int com.show.dao.BookDao.update())")
    public void pt(){}
    @Before("pt()")
    public void method(){
        System.out.println("前置通知");
    }
}
```

###   after后置通知

```java
@Component
@Aspect
public class MyAdvice {
    @After("pt()")
    public void after(){
        System.out.println("后置通知");
    }
 }
```

### around环绕通知

```java
@Component
@Aspect
public class MyAdvice {
    //连接点
    @Pointcut("execution(int com.show.dao.BookDao.update())")
    public void pt(){} 
    
   	//通知
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //ProceedingJoinPoint 对象中的 proceed（）方法执行原本方法代码，返回值是原方法的返回值
        System.out.println("前置");
        //表示对原始代码的操作
        Object proceed = pjp.proceed();
        System.out.println("后置");
        return 100;
    }
}
/* 
使用around通知需要ProceedingJoinPoint对象来进行调用原本方法的代码
使用ProceedingJoinPoint对象的proceed（）方法调用原代码
ProceedingJoinPoint.Proceed()的返回值就是原方法的返回值
如增强方法的原方法的返回值不是void 那么在增强的通知的返回值就用Object方便返回
*/
```

### AfterReturning 执行完成通知

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(int com.show.dao.BookDao.update())")
    public void pt(){}
    @AfterReturning("pt()")
    public void afterReturning( ){
        System.out.println("执行完成通知");
    }
}
/*
执行完AfterReturning 成通知和后置通知有什么区别
AfterReturning 通知在方法没有抛出异常信息执行完成方法才执行
Alter 通知只在方法执行的最后节点执行
*/
```

### afterThowing异常通知

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(int com.show.dao.BookDao.update())")
    public void pt(){}
    @AfterThrowing("pt()")
    public void afterThrowing(){
        System.out.println("抛出异常");
    }
```



### @Around注意事项

1. 环绕通知必须依赖形参ProceedingJoinPoint才能实现对原始方法的调用，进而实现原始方法调用前后同时添加通知
2. 通知中如果未使用ProceedingJoinPoint对原始方法进行调用，将跳过原始方法的执行
3. 对原始方法的调用可以不接受返回值，通知方法设置成void，如果接受返回值，必须设定为Object类型
4. ​    原始方法的返回值是void，通知方法设置成void，也可以设置成Object
5. 由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须抛出Throwable对象

### proceedingJoinPoint对象获取参数

- proceed 方法执行原始方法   返回原始方法返回值
- getSignatture（）获取签名信息 返回Signature对象
- Signature对象.getDeclaringType()方法  会显示原本方法的包路径
- Signature对象.getName()方法    会显示原本方法的方法名



### AOP通知获取数据

- 获取参数
- 获取返回值
- 获取异常

获取切入点方法参数	

- JoinPoint：适用于前置，后置，返回后，抛出异常通知
- ProceedingJoinPoint：适用于环绕通知

获取切入点方法返回值

- 返回后通知
- 环绕通知

获取切入点方法运行异常信息

- 抛出异常后通知
- 环绕通知

### 获取方法参数

**前置-后置**

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(String com.show.dao.BookDao.findName(..))")
    public void pt(){}
    @Before("pt()")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs(); //获取返回值
        System.out.println(Arrays.toString(args));
        System.out.println("before");
    }
    
    //前置和后置方法是一样的语法
```

**环绕**

```java
@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(String com.show.dao.BookDao.findName(..))")
    public void pt(){}
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Object proceed = pjp.proceed();
        System.out.println(Arrays.toString(args));
        return proceed;
    }
```

### 事务

```java
package com.show.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
public class JdbcConfig { //jdbc连接
        @Bean
        public DataSource dataSource(){
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/Bank?serverTimezone=UTC");
            return dataSource;
        }
    @Bean //事务管理器  
        public PlatformTransactionManager platformTransactionManager (DataSource dataSource){
            DataSourceTransactionManager manager = new DataSourceTransactionManager();
            manager.setDataSource(dataSource);  //此处的的DataSource要和mybatis注册使用的DataSource要是一致的
            return manager;
        }

}

```

**spring配置文件**

```java
package com.show.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.show")
@Import({JdbcConfig.class,MyBatisConfig.class})
@EnableTransactionManagement //开启注解事务管理器
public class SpringConfig {
}

```

**在接口层开启事务**

```java
@Component
public interface UserService {
    @Transactional //开启事务
    void trade(String name1,String name2,int money);
}
```

### spring事务角色

- 事务管理员：发起事务方，在Spring中通常代指业务开启事务的方法
- 事务协调员：加入事务方，在Spring中通常代指数据层的方法，也可以是业务层的方法

### 事务属性



| 属性                   | 作用                         | 示例                                       |
| ---------------------- | ---------------------------- | ------------------------------------------ |
| readOnly               | 设置是否为只读事务           | readOnly=true只读事务                      |
| timeout                | 设置事务超时时间             | timeout= -1 永不超时                       |
| rollbackFor            | 设置事务回滚异常（class）    | rollbackFor={NullPointException,class}     |
| rollbackForCllassName  | 设置事务回滚异常（String）   | 同上为字符串格式                           |
| noRollbackFor          | 设置事务不回滚异常（class）  | noRollbackFor({NullPotintException.class}) |
| noRollbackForClassName | 设置事务不回滚异常（String） | 同上为字符串格式                           |
| propagtion             | 设置事务转播行为             |                                            |

**事务不是遇到所有的异常都会回滚的！！**

- 受检查异常就默认不会回滚的 比如：IoException
- CheckedException。  
