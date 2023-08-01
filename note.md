# 笔记

## 1. RabbitMQ 新增用户（控制台方式）
   https://blog.51cto.com/gblfy/5653663
   
## 2. 常用命令
```shell
 .\rabbitmqctl.bat add_user hello hello
 .\rabbitmqctl.bat set_user_tags hello administrator 
 .\rabbitmqctl.bat add_vhost /hello
 .\rabbitmqctl.bat set_permissions -p /hello hello ".*" ".*" ".*"
```

## 3. TopicExchange的通配符
    # 匹配0个或多个词（词之间以.间隔）  
    * 匹配0个或多个词字符
见 [DeclareMore.java](DeclareMore) [ExchangeTopicDemo.java](ExchangeTopicDemo)