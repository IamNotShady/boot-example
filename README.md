
### SpringBoot-Web
---

#### 集成的技术如下：
* Spring
* SpringMvc
* Mybatis
* Druid
* FastJson
* Thymeleaf
* Pagehelper
* 通用mapper
* 热部署
* Redis
* 日志切片处理
* Shiro

#### Docker部署
1. 构建镜像
    
    `进入项目根目录执行 docker build -t spring-boot-web:latest -f ./Dockerfile ./`
 
2. 生成并运行容器

    `docker run --name spring-boot-web -p 8080:8080 spring-boot-web:latest`
`


