# 日志工具
> 在程序中写日志是一件非常重要，但是很容易被开发人员忽视的地方。写好程序的日志可以帮助我们大大减轻后期维护压力。

之前学安卓开发时就感觉安卓中的`Log.i`系列函数非常舒服，于是就仿制了一个，即`LogUtils`，根据不同的日志等级输出日志。

## 数据库
jdbcTemplate方式连接数据库，需要一个`log`表：
```SQL
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `ltime` datetime(0) NULL DEFAULT NULL COMMENT '时间戳',
  `level` int(11) NULL DEFAULT NULL COMMENT '日志等级',
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `content` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '日志内容'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
```

## 配置
```
log.optputlevel=1    #只有等级高于1(DEBUG)的日志才会在控制台输出
log.remainingdays=30 #日志保留30天
```

在实际的运行环境中，在控制台上打印大量的日志意义不大，所以程序开发或调试时`log.outputlevel`设置为0或1，而运行时设置为3甚至4就好。

## 使用
1. 导入
```java
import cn.edu.upc.utils.LogUtils;
```
2. 使用
```java
LogUtils.d(this,"DEBUG日志");
```

## 说明
`LogUtils`包下有5个静态函数，`v`、`d`、`i`、`w`、`e`分别对应日志的VERBOSE、DEBUG、INFO、WARN、ERROR几个等级。调用后日志存入数据库，如果等级足够，同时在控制台输出。