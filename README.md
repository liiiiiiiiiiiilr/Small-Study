# 设备维修管理系统

[TOC]

## 项目的成员分工

|功能|人员|
|---|---|
|客户管理|解亚续|
|报修管理|宋慧博|
|维修管理|刘海鑫|
|结算管理|朱彦瑛|
|备件管理|李子豪|
|运营监督管理、权限管理|李睿|
|---|---|
|友好界面|待定|

## 日志工具
[日志工具](/docs/log.md)

## 开发过程中要用到的技术和工具
### springboot controller

springboot中的Controller大致与我们刚学的Servlet处于同样位置，学习了原生的Servlet那么Controller应该不会成为障碍。

springboot中的controller可以直接使用@RequestParam注解获取参数(而不需要通过request.getParameter())，用起来更方便一点。

### 数据库持久化JdbcTemplate
写相应的接口并实现，使用时通过添加@AutoWired自动注入。（见样例）

选择JdbcTemplate是因为它进行了程度比较合适的封装，操作数据库还是写SQL语句，而且也能省去不少代码。

### Thymeleaf

相当于jsp所起到的作用，将后台准备好的数据处理成网页。其基础语法只要会用el表达式显示一下值就能看懂。

选择的理由：

1. 基于标签处理，可以简单实现比较复杂操作。
2. 可以在断网情况下直接调试，直接在浏览器中显示，减少前端的工作量。
3. 使用jsp会导致最终工程不能打jar包

### maven

用于自动下载相关的依赖。

要求：

1. 配置maven环境
2. 在pom.xml中添加依赖
3. 通过`mvn clean`（清理）和`mvn install`命令生成目标jar包。

### git

待定。

### markdown

简单要求。

写文档要用，主要因为git不能对二进制文件有效管理，所以word文档基本就丢了……

### HTML css javascript
了解常见HTML标签用法，会引入现成的css、js文件。

### 前端框架layui
一个前端框架，见：[https://www.layui.com/doc/](https://www.layui.com/doc/)

## 数据库服务器
> 因为表结构要同步，我在自己服务器上建立了一个数据库，可以登录访问。

地址：`ntutn.top`

端口号：`3306`

用户名：`test`

密码：`test1234`

