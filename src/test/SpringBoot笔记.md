# SpringBoot

##1.Spring Boot概念

 Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化Spring应用的 初始搭建以及开发过程。该框架 使用了特定的方式来进行配置，从而使开发人员不 再需要定义样板化的配置。通过这种方式，Spring Boot致力于在 蓬勃发展的快速应 用开发领域(rapid application development)成为领导者。用我的话来理解，就是spring boot其实 不是什么新的框架，它默认配置了很多框架的使用方式，就像maven整合了所有的jar包，spring boot整合了所有的框 架。 

**springboot(微框架) = springmvc(控制器) + spring(项目管理)**    

## 2.SpringBoot的特点

- 创建了**独立的**应用程序
- 嵌入的Tomcat，无需部署WAR文件    
- 简化Maven配置   
- 自动配置Spring    
- 没有XML配置    

  # 3.SpringBoot约束	

- src/main/java

  - com.baizhi   主包

    ​	entity     子包

    ​       dao

    ​       service

    ​      Controller

    ​      入口类：**注意：**入口类一定要配置在主包下与子包同级

    ​    Application.class

- src/main/resources

  ​	sprngboot配置

  ​        application.yml

  ​	application.yaml

  ​        application.properties

  ​      **注意：**名字一定要叫  applocation

- src/test/java

- src/test/resources


##4.使用maven搭建第一个SpringBoot项目

### 1.创建maven项目

### 2.导入jar包

**2.1导入父级项目依赖**

~~~xml
  <!--父级项目依赖-->
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.5.8.RELEASE</version>
  </parent>
~~~

**2.2导入jar包**

~~~xml
    <!--web支持的jar springboot的启动器-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--测试支持的jar-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <!-- 只在test测试里面运行 -->
      <scope>test</scope>
    </dependency>
~~~

###配置入口类

~~~java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //参数：入口类类对象，主函数的参数
        SpringApplication.run(Application.class,args);
    }
}
~~~

**@SpringBootApplication**

~~~java
1.@SpringBootApplication=@Configuration+@EnableAutoConfiguration+@ComponentScan
//当前的注解是一个组合注解
@Configuration  //作用:声明当前类是一个配置类
@ComponentScan  //作用:组件扫描   把一些加有@service  @controller @component  @repository注解的对象交由工厂管理
@EnableAutoConfiguration    //开启自动配置 datasource  sqlsessionFactorybean  mapperScanner 
2.@RestController //作用:组合注解 @Controller @ResponseBody  声明当前类为控制器,并且类中所用方法返回json  
~~~



### 3.springBoot配置

**SpringBoot默认启动没有项目名**

~~~yaml
server:
  port: 9898    #修改端口号
  context-path: /springboot   #配置项目名
~~~

## 4.修改启动banner

**注意:名字必须叫banner.txt**

~~~java
${AnsiColor.BRIGHT_YELLOW}
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
~~~

## 5.配置文件的拆分

**1.创建多个配置文件**

application.yml     主配置文件

~~~yaml
spring:
  profiles:
    active: prod
~~~

application-dev.yml    生产环境

~~~~yaml
server:
  context-path: /springbootDev
~~~~

application-prod.yml  开发环境

~~~~yaml
server:
  context-path: /springbootProd
~~~~

##6.springBoot与第三方技术的集成

###6.1springBoot与mybatis集成

**1.导入jar包**

~~~xml
<!--整合mybatis-->
<!--mybatis和springboot的整合包-->
<dependency>
<groupId>org.mybatis.spring.boot</groupId>
<artifactId>mybatis-spring-boot-starter</artifactId>
<version>1.0.0</version>
</dependency>

<!--数据源的jar包-->
<dependency>
<groupId>commons-dbcp</groupId>
<artifactId>commons-dbcp</artifactId>
<version>1.4</version>
</dependency>
<!--数据库驱动-->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>5.1.38</version>
</dependency>
<!--mybatis的核心jar-->
<dependency>
<groupId>org.mybatis</groupId>
<artifactId>mybatis</artifactId>
<version>3.2.8</version>
</dependency>
~~~

**2.配置相关参数**

~~~yaml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/cmfz
    username: root
    password: root
mybatis:
  mapper-locations: classpath:com/baizhi/mapper/*Mapper.xml
  type-aliases-package: com.baizhi.entity
~~~

**3.在入口类上加入注解扫描DAO**

~~~java
@MapperScan("com.baizhi.dao")
~~~

###6.2springBoot集成jsp

- 1.导入相关jar包

  ~~~XML
  <!-- 给内嵌tomcat提供jsp解析功能的jar-->
  <dependency>
      <groupId>org.apache.tomcat.embed</groupId>
      <artifactId>tomcat-embed-jasper</artifactId>
  </dependency>
  <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
  </dependency>
  ~~~

- 2.导入启动插件

  ~~~xml
  <!--插件启动-->
  <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
  </plugin>
  ~~~

**配置jsp页面解析**

~~~yaml
spring:
  mvc:
    view:
      prefix: /
      suffix: .jsp
~~~

**springboot配置jsp热部署**

~~~yaml
server:
  jsp-servlet:
    init-parameters:
      development: true
~~~

## 7.lombok    

###7.1.概念 

使java代码变的更加优雅,以注解的方式代替之前的冗长代码,底层采用字节码技术生成相应方法

### 7.2.使用lombok    

- 1.下载lombok相关插件    

  File ----> Settings ----> Plugins ----> 搜索lombok ----> download    

- 2.下载lombok的jar    

~~~xml
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.4</version>
    <scope>provided</scope>
</dependency>
~~~

- 常用注解

  ~~~xml
  @Data 注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、
  toString 方法
  @Setter ：注解在属性上；为属性提供 setting 方法
  @Getter ：注解在属性上；为属性提供 getting 方法
  @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
  @NoArgsConstructor ：注解在类上；为类提供一个无参的构造方法
  @AllArgsConstructor ：注解在类上；为类提供一个全参的构造方法
  @NonNull : 如果给参数加个这个注解 参数为null会抛出空指针异常
  @Value : 注解和@Data类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法.
  ~~~

  