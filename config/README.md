工程配置目录

1、对于需要外部配置的配置文件存放在文件目录config这里，而对于不需要外部配置的配置文件，
存放在资源目录，已经配置好assembly打包的时候会拷贝springboot配置到外部，
assembly相关配置如下：
 <!-- 拷贝配置文件 -->
        <!-- 先从项目目录的config拷贝配置文件 -->
        <fileSet>
            <directory>${basedir}\config</directory>
            <outputDirectory>config</outputDirectory>
            <includes>
            </includes>
        </fileSet>
        <!-- 从项目目录的资源config拷贝配置文件 -->
        <fileSet>
            <directory>${basedir}\src\main\resources\config</directory>
            <outputDirectory>config</outputDirectory>
            <includes>
                <include>application*</include>
                <include>config.xml</include>
                <include>bootstrap.yaml</include>
            </includes>
        </fileSet>