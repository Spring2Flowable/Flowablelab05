

------

***环境：***

**[tomcat8]()**

**[jdk1.8+]()**

[**flowable6.4.0**]()

------

由于只集成了flowable-modeler,此模块需要调用到flowable-idm模块，因此先要在本队部署flowable-idm。

## 一、 部署

　[参考官网](https://www.flowable.org/docs/userguide/index.html#getting.started.rest)

-   [下载Flowable6.4.0软件包](https://github.com/flowable/flowable-engine/releases/download/flowable-6.4.0/tomcat-flowable-6.4.0.zip)

- 解压
- 将flowable-idm.war放在tomcat的webapp目录下

## 二、启动tomcat

```
linux系统：./startup.sh 
win系统：./startup.bat 
```



## 三、运行demo

- 浏览器访问

```
http://localhost:9999/demo/index.html
```

### 
