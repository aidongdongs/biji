## Feign 

### java 项目如何实现接口调用

1. **HttpClient**

   ```powershell
   #  HttpClient是Apache jakarta Common 下的子项目。用来提供高效的，最新的，功能丰富的支持 Http 协议的客户端编程工具包，并且它支持HTTP协议最新版本和建议，HttpClient相比于传统JDK自带的 URLConnection 提升了易用性和灵活性，使客户端发送HTTP请求变得容易，提高了开发效率
   ```

2. **OKHttp**

   ```powershell
   # 一个处理网络请求开源项目，是安卓最火的轻量级框架，由Square公司贡献，用于替换HttpUrlConnection 和Apache HttpClient。 OKHttp拥有简洁的API，高效的性能，并支持多种协议（HTTP/2和SPDy）
   ```

3. **HttpURLConnection**

   ```powershell
   # HttpURLConnection 是java标准类，它继承自URLConnection ，可用于向指定网站发送get请求post请求，HttpURLConnection 使用比较复杂，不想HTTPClient那样容易使用
   ```

4. **RestTemplate WebClient**

   ```powershell
   # RestTemplate 是 Spring 提供用于访问Rest服务的客户端，RestTemplate 提供了多种便捷访问远程Http服务的方法，能够大大的提高客户端编写的效率
   ```



### 什么是Feign

```powershell
# Feign 是 Netfilx开发生明式，模板化的Http客户端，其灵感来自Retorfit JAXRS-2.0 
# Feign 支持多种注解，类如Feign 自带的主角或者JAX-RS注解等

#Spring CLoud OpenFeign 对 Feign进行了增强，使其支持Spring MVC 注解，另外还整合了Ribbon 和 Nacos 从而使用Feign的使用更加方便
```



 **1： 优势**

```powershell
# Feign 可以作到 使用Http请求远程服务器时就像调用本地方法一样的体验 ，开发者完全感知不到这是远程方法，更感知不到这个是个Http请求，它想Dobbo 一样consumer直接调用接口调用provider ，而不需要通过常规的HttpClient 钩爪请求再解析返回数据，
```



### SpringcloudAlibaba整合 Feign

1：导入依赖

```xml
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
	</dependency>
```

2：编写调用接口+ @FeignClient注解

```java

// FeignClient 中 name 属性是指定要调用的服务名称
// FeignClient 中 path 属性是指定再controller类上的requestMapping的映射，会和接口上的路径进行拼接
@FeignClient(name = "stock-service" ,path = "/stock")
public interface StockFeignService {
    //声明需要调用的接口方法
    @GetMapping("/helloword")
     String hellWord();
}
```

3：在启动类上加上 @EnableFeignClients注解

```java
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients //开启feign 功能
public class OrderApplication   {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

```

4：直接调用

```java
public class OrderController {

    @Autowired
    private    StockFeignService stockFeignService;

    @GetMapping("/helloword")
    public String helloWord(){
        String s = stockFeignService.hellWord();
        System.out.println(s);
        return "helloword";
    }
}
```





### springcloudopenfiegn 自定义配置

Feign提供了很多的扩展配置，让用户更加灵活的使用

#### **1 日志配置**



**日志的等级**

1. **NONE**：【性能最佳，适用于生产】 不记录任何日志（默认住）
2. **BASIC**：【使用于生成环境追踪问题】仅记录请求方法，url，响应状态吗以及执行时间
3. **HEADERS**：记录BASIC级别的基础上，记录请求和响应的header
4. **FULL** ：【比较适合开发以及测试环境定位问题】：记录请求和响应的header和body和元数据





**全局配置**

有时候我们遇到了bug，比如接口调用失败，参数没收到等问题，或者是想看看调用性能，就需要配置Feign的日志了，一次让Feign把请求输出来

**定义一个配置类，指定日志级别**

```java
//全局配置 使用了@Configuration 会将配置的作用所有的服务提供方
//局部配置 如果只想针对某一个服务进行服务配置，就不要加@Configuration
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
```

**注意** 这样子设置之后是无法在控制台打印日志，因为Feign的日志都是debug级别的， **但是** springboot 的日志默认级别是 info 所以feign的日志被隐藏了 需要配置springboot的日志级别

我们不要直接将整个springboot项目的日志级别都配置成为debug，不然日志会非常的多，我们只需要将 feign接口所以在的包配置称为debug级别的日志即可

```yaml

logging:
  level:
    com.show.order.feign: debug
```

---



**局部配置**

**定义一个配置类，指定日志级别**

```java
public class FeignConfig {

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
```

 

在feign接口处指定要服务的日志级别

```java
// configuration 属性指定该配置类
@FeignClient(name = "stock-service" ,path = "/stock",configuration = {FeignConfig.class})
public interface StockFeignService {
    //声明需要调用的接口方法
    @GetMapping("/helloword")
     String hellWord();
}
```



**yml 配置 openfeign的日志级别 局部配置**

```yml
feign:
  client:
    config:
      stock-service: #服务名称 指定服务的日志级别
        logger-level: basic #日志级别
```

---





#### 2 契约配置



Spring Cloud 在 Feign的基础上做了扩展。使用Spring MVC 的注解来完成Feign的功能，原生的Feign是不支持Spring MVC 注解的 ， 如果你想在Spring Cloud 中使用原生的注解方式来定义客户端也是可以的，通过配置契约来改变这个配置，Spring Cloud 中默认的是 Spring MVC Contract

Spring Cloud 1 早期版本就是用原生的Feign 随者netfilx的停更替换成了Open Feign

**为什么需要契约配置？**

答：比方说，以前springcloud1.0的早期版本它就是用的原生的feign，随着netflix的停更才替换成了Open feign，Open feign在 Feign 的基础上做了扩展，使用 Spring MVC 的注解来完成Feign的功能，从而降低了学习的成本。假如说我有项目就是用的spring cloud 1.0早期的版本，我现在想做版本的升级，想升级为比较新的版本，我想用openFeign；但是我一升级的话，项目中的feign的接口当中的原生注解是不是都得改掉，在小的代码变更都会引起bug的出现。我们尽量能少改代码就改代码。我们用契约配置就可以符合我们的功能。我们既能升级我的spring cloud的版本，我又可以保留我原生的feign注解，从而降低修改代码产生的风险。



**yml配置**

```yaml
feign:
  client:
    config:
      stock-service: #服务名称
        logger-level: basic #日志级别
        contract: feign.Contract.Default # 设置默认的契约 （还原成原生的feign注解）
```



#### 3 超时时间配置

通过Oprions 可以配置连接超时时间 Oprtions 的第一个参数是连接超时时间 (ms) , 默认值是 2s , 第二个是请求处理超时是时间 (ms) 默认是 5s

**全局配置类配置**

```java
@Configuration
public class FeignConfig {
    @Bean
    public Requset.Options options(){
        return new Request.Options(5000,10000)
    }
}
```

**yml 中配置**

```yaml
feign:
  client:
    config:
      stock-service: #服务名称
        logger-level: basic #日志级别
        #连接超时时间 默认 2s
        connect-timeout: 5000
        # 请求处理超时时间 默认 5s
        read-timeout: 3000
```



#### 4 自定义拦截器

自定义拦截器实现认证逻辑

此拦截器的作用是 当我们消费端通过openfeign去调用服务端所被拦截的操作



**编写拦截器**  实现接口

```java
@Configuration
public class CustomFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("open feign 进入拦截器");
    }
}

```



**yml也可以实现 具体 自己查询 并没有 实现接口方便**