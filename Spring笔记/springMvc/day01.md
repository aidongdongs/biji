## springmvc

## 入门案例

## 配置步骤

1. **配置spring配置类**

   ```java
   package com.show.config;
   
   import org.springframework.context.annotation.ComponentScan;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   @ComponentScan("com.show")
   public class SpringMVCConfig  {
   }
   
   ```

2. **配置tomcat启动加载Springmvc容器 因该是DisoacherServlet**

   ```java
   package com.show.config;
   //定义一个servlet 容器启动配置类，在里面加载spring 配置
   import org.springframework.web.context.WebApplicationContext;
   import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
   import  org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
   public class SpringTomcat_init extends AbstractDispatcherServletInitializer {
   //    加载springmvc 容器
       protected WebApplicationContext createServletApplicationContext() {
           AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
   //       注册加载spring配置文件
           context.register(com.show.config.SpringMVCConfig.class);
           return context;
       }
   //      设置哪些请求归属spring 处理
       protected String[] getServletMappings() {
           return new String[]{"/"};
       }
   //    加载spring  容器配置
       protected WebApplicationContext createRootApplicationContext() {
           return null;
       }
   }
   ```

3. 编写处理内容

   ```java
   package com.show.controller;
   
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.ResponseBody;
   
   @Controller
   public class UserController {
       @RequestMapping("/save")  //设置访问路劲
   //    设置当前操作的返回值类型
       @ResponseBody
       public String save(){
           System.out.println("user save....");
           return "{'module':'springmvc'}";
       }
   }
   
   ```



### 入门案例工作流程分析

**启动服务器初始化过程**

1. 服务器启动，执行ServletContainersInitConfig类，初始化容器
2. 执行createServletAppliactionContext方法，创建了webApplicationContext对象
3. 加载SpringMvcConfig
4. 执行@CompoentScan加载对应的bean
5. 加载UserController，每个@RequsetMpping的名称对应一个具体的方法
6. 执行getServletMapping方法，定义所有请求都通过springMVC 

**单次请求过程**

1. 发送请求localhost/save
2. web容器发现所有请求都经过springmvc，将请求交给springmvc处理
3. 解析请求路劲/save
4. 有/save匹配执行对应的方法save（）
5. 执行save（）
6. 检查到有@ResponseBody直接将save（）方法的返回值作为响应体返回给请求方



### controller加载控制与业务加载控制

- SpringMVC相关bean （表现出）
- Spring控制的bean
  - 业务bean（service）
  - 功能bean（DataSoure）

**因为spring管理的bean和springmvc管理的bean其实加载完成后使用其实不是在同一个空间中 ，spring是加载web容器的，springmvc是在web容器中再次开辟出一个容器来加载自己管理的bean，如果不加以控制可能会出现访问出问题，，  spring是父容器，springmvc是子容器 ，子容器可以访问父容器的bean  但 是，父容器不能访问子容器里面的bean！， springmvc管请求时，是只在自己的空间中查找的：   SpringMVC在匹配Controller与url的映射关系时只会在自己的上下文中查找Controller进行请求的处理。所有，为了省下空间，or避免访问错误，分开扫描是最好的**

**SpringMVC相关bean加载控制**

- SpringMVC加载的bean对应的包均在com.show.controller包内

**Spring相关bean加载控制**

1. Spring加载的bean设置扫描范围为com.show 然后排除controller内的bean

   ```java
   @Configuration
   @PropertySource("")
   @EnableAspectJAutoProxy
   
   @ComponentScan( value = {"com.show"},
           excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class)
   )
   public class SpringConfig {
   }
   ```

2. Spring加载bean设置扫描范围，例如service包，dao包等

   ```java
   @Configuration
   @PropertySource("")
   @EnableAspectJAutoProxy
   
   @ComponentScan({"com.show.service","com.show.dao"})
   public class SpringConf
   ```

   

3. 不区分环境，spring和springmvc的bean都加载到一个容器中

@**ComponentScan**

```java
/*
	名称：@**ComponentScan**
	类型：类注解
	范例：
	@ComponentScan( value = {"com.show"},
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class)
)
public class SpringConfig {
}
属性 excludeFilter：排除扫描路劲中加载的bean，需要指定类别（type）与具体项（classes）
属性 includeFilter：加载指定的bean，需要指定类别（type）与具体项（classes）

	
*/
```

**简化spring加加载web容器和springmvc容器**

```java
public class SpringTomcat_init extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{com.show.config.SpringMVCConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{com.show.config.SpringConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

### 请求与响应

1. 请求映射路劲
2. 请求参数
3. 日期类型参数传递
4. 响应json数据

#### 请求映射路劲

```java
/*
名称 ： @RequestMpping
类型： 方法注解 类注解
位置：springMvc控制器方法定义上方
作用：设置当前控制器方法请求路径，如果设置在类上同意设置当前控制器方法请求访问路劲前缀
范例：@Controller
@RequestMapping("/UserController")
public class UserController {
    @RequestMapping("/save")  //设置访问路劲
//    设置当前操作的返回值类型
    @ResponseBody
    public String save(String name){
        System.out.println("user save....");
        System.out.println(name);
        return  name;
    }
}
属性：value（默认）
请求访问路劲，或访问路径前缀
*/
```

#### 请求方式：

- get请求
- post请求

```java
//获取get or post 的参数直接将参数名称写在方法上，springmvc会自动匹配，如果想隐藏接口的字段名可以使用@RequestParam 注解给参数起别名
@Controller
@RequestMapping("/UserController")
public class UserController {
    @PostMapping("/save")  //设置访问路劲
//    设置当前操作的返回值类型
    @ResponseBody
    public String save(String name, int age){
        System.out.println("user save....");
        System.out.println(name);
        return  name+""+age;
    }
}


@Controller
@RequestMapping("/UserController")
public class UserController {
    @PostMapping("/save")  //设置访问路劲
//    设置当前操作的返回值类型
    @ResponseBody
    public String save(@RequestParam("userName") String name, int age){
        System.out.println("user save....");
        System.out.println(name);
        return  name+""+age;
    }
}
```

**获取请求参数时。出现乱码问题**

```java
    
/*
在springmvc配置类中 使用getServletFilters方法设置过滤器
*/
//配置字符过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter= new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        return new Filter[]{characterEncodingFilter};
        
```



#### **参数种类**

- 普通参数

  - url地址传参，地址参数名与形参变量名相同，定义形参即可接收参数
  - 请求参数名和形参名称不相同，使用@RequsetParam注解绑定关系

- pojo类型参数

  - 请求参数名与形参对象属性名相同，定义pojo类型形参即可接受参数

  - ```java
        @PostMapping("/postUser")
        @ResponseBody
        public String postUser(User user){
            return user.getName()+""+user.getName()+""+user.getAddress();
        }
    }
    //请求参数http://localhost:8080//springmvc_dome03_war_exploded/UserController/postUser/?name=zhangsan&address=beijing&age=12
    //当参数列表是pojo类型时，当你的参数和pojo的字段名相同spring会自动装配到pojo类型中去 写参数的方式也是key value的形式
    ```

    

- 嵌套pojo类型参数

- 请求参数名称与形参对象名相同，按照对象层次结构关系即可接收嵌套pojo属性参数

  ```java
  //当pojo嵌套怎么写入数据 直接引用类型名称.属性名称 = value   俩如：student.studentNo=10 
  public class User {
      private String name ;
      private int age;
      private String address;
      private Student student;
  }
  
      @PostMapping("/postUser")
      @ResponseBody
      public String postUser(User user){
          return user.getName()+""+user.getName()+""+user.getAddress()+user.getStudent().getStudentNo()+""+user.getStudent().getGradeId();
      }
  //url : http://localhost:8080//springmvc_dome03_war_exploded/UserController/postUser/?name=zhangsan&address=beijing&age=12&student.studentNo=123456&student.gradeId=10
  ```

- 数组类型参数

  - 请求参数名与形参对象属性名相同且请求参数为多个，定义数组类型形参即可接受参数

  - ```java
    //数组参数类型，直接使用相同的名称写入不同的参数spring会自动装配到数组中去的
    @PostMapping("/postArray")
        @ResponseBody
        public String postArray(String [] strings){
            return Arrays.toString(strings);
        }
        
        //url: http://localhost:8080//springmvc_dome03_war_exploded/UserController/postArray/?strings=10&strings=20&strings=20&strings=40
    ```

    

- 集合类型参数

  - 保存普通参数，请求参数名与形参集合对象名相同且求情参数为多个，@RequsetParam绑定参数关系

  - ```java
    //集合参数传递需要使用@RequestParam注解告知spring这是个集合，不如spring会当集合是一个pojo类进行创建对象进行传参，就会导致找不到构造方法，然后报错
    public String postList(@RequestParam List<String> list){
           return Arrays.toString(list.toArray());
        }
    //url : http://localhost:8080//springmvc_dome03_war_exploded/UserController/postList/?list=10&list=29&list=3048
    
    ```

- **@注解**

  ```java
  /*
  名称：RequesParam
  类型：形参注解
  位置：SpringMVC控制器方法形参定义前面
  作用：绑定请求参数与处理器方法形参间的关系
  范例：
      @PostMapping("/postList")
      @ResponseBody
          public String postList(@RequestParam List<String> list){
         return Arrays.toString(list.toArray());
      }
  参数：required：是否为必穿参数
  	 defaultValue：参数默认值
  */
  ```

  

#### 传递json数据

- json数组
- json对象（pojo）
- json对象数组（pojo）
