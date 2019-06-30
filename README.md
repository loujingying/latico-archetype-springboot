### 项目名称
    springboot的maven骨架
    
### 项目介绍 
    springboot混杂骨架，有配置springcloud但是默认不启动，支持多数据源，spring Data JPA和mybatis共存
    有些依赖包是latico-commons里面的公共组件，需要提前install进本地仓库

### 如何启动程序？
    1、因为默认配置了数据库连接，如果要启动，请修改配置文件的数据库随便连接到即可；
    2、如果确定程序不需要数据库连接，需要把配置文件中的数据源配置和源代码的dao包给删掉；
    3、运行Application.java;

### 注意事项
    1、因为IDEA的.gitignore文件在骨架生成的时候不会打包进去，所以需要新建项目后手工添加,可以直接把根目录git-example.gitignore重命名为.gitignore文件；
    2、如果源文件中有部分源代码文件是GBK有部分文件是UTF-8，会导致整个程序的日志打印乱码异常，
    处理办法就是把所有的源文件统一编码，IDEA的可以在settings→Editor→File Encodings中查看当前项目的文件编码；

### 在当前项目如何配置连接springcloud的eureka注册中心？
     1、在pom.xml，开启如下依赖即可（默认已注释）
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
     </dependency>
     2、目前springcloud2.0以上新版本不需要在Application.java启动EnableEurekaClient注解，当然也可以开启（当前默认已注释）
     3、修改连接注册中心的地址，修改：config/application-dev.yaml配置文件的
     eureka:
       client:
         serviceUrl:
     #      可以多个，用逗号分割，逗号跟实际内容之间不能有空格
           defaultZone: http://${spring.security.user.name}:${spring.security.user.password}@localhost:8000/eureka/
     

### 在当前项目如何配置连接springcloud的配中心？   
    1、在pom.xml，开启如下依赖即可（默认已注释）
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
    2、已经把配置中心的包加了查看bootstrap.yaml-example配置文件的使用    

### 如何改变程序的打包方式改变？
    1、目前默认的打包方式是assembly，同时对于springboot的合并包进行了分离，
    因为springboot的插件spring-boot-maven-plugin默认打包方式是会把所有的jar和配置文件打在一起，
    目前已经对spring-boot-maven-plugin插件进行了定制化配置，让它打包跟传统方式一样，这样方便增量升级（如果使用了docker，建议修改回springboot默认打包方式）；
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>${springboot.version}</version>
        <configuration>
            <!--指定启动类-->
            <!--<mainClass>${start-class}</mainClass>-->
            <layout>ZIP</layout>
            <includes>
                <include>
                    <groupId>nothing</groupId>
                    <artifactId>nothing</artifactId>
                </include>
            </includes>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <!--repackage：创建一个自动可执行的jar或war文件。它可以替换常规的artifact，或者用一个单独的classifier附属在maven构建的生命周期中。-->
                    <goal>repackage</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    
    2、如果是要打包到docker中运行，那么还是建议改回spring-boot-maven-plugin的默认打包方式，这样部署一个文件docker中更方便；
    
    默认打包方式如下：
     <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>

    单是因为我们的程序pom不是继承springboot的父依赖，所以这个打包插件需要参考springboot的父的打包配置，应该使用如下配置：
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>${springboot.version}</version>
        <executions>
            <execution>
                <id>repackage</id>
                <goals>
                    <!--repackage：创建一个自动可执行的jar或war文件。它可以替换常规的artifact，或者用一个单独的classifier附属在maven构建的生命周期中。-->
                    <goal>repackage</goal>
                </goals>
            </execution>
        </executions>
        <configuration>
            <!--指定启动类-->
            <!--<mainClass>${start-class}</mainClass>-->
        </configuration>
    </plugin>
      
### 打包后配置修改
    1、java路径和内存配置修改文件（根据需求修改）：javapath.cfg；
    
    2、程序名称和启动包名称配置文件（默认不需要修改）：programname.cfg；
    
    3、指定程序使用的配置文件（生产环境建议使用prod），在application.properties文件中，
        指定为：dev（开发环境）或 test（测试环境）、prod（生产环境），
    
    4、正常情况，可执行文件:start.bat、start.sh、show-psid.sh、stop.sh，不允许更改；

### 如何启动与关闭程序？
    1、windows下
        1.1、启动：双击启动start.bat (需要把bat格式文件调成可执行文件，默认一般都已经是)
        1.2、关闭：直接关闭bat窗口
    2、linux下
        2.1启动 ：
            执行命令：  sh ./start.sh
        2.2、查看进程ID
            执行命令： sh ./show-psid.sh
        2.3、停止进程
            执行命令： sh ./stop.sh    