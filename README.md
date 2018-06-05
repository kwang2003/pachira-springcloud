## 1.整体架构
### 1.1.项目概述
项目基于SpringCloud技术栈，封装实现了搭建企业级应用的最佳实践，可以作为项目开发的脚手架使用，不用每次从头搭建，该项目事项了前后台分离技术进行开发，后台只提供rest接口，前端可以根据实际的需要采用reactjs/angular/vue或其他开发技术进行接口对接。
### 1.2.开发技术
|名称|版本|描述
|:-|:-|:-
|JDK|1.8|正确设置JAVA_HOME、CLASSPATH、PATH变量
|Maven|3.5.2|正确设置M2_HOME变量，把mvn加入到PATH下
|MySQL|5.7|
|Spring Boot|2.0.2.RELEASE|oauth2项目中使用的是1.5.12.RELEASE，因为2.x版本与某个class不兼容
|Spring Cloud|Edgware.SR3
|mybatis|3.4.6|使用mybatis-spring框架封装通用操作
|swagger2|3.8.0|基于注解生成API文档
|lombok|1.16.0|自动生成getter/setter/toString等方法
|guava|18.0|

### 1.3.系统架构
![https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/B20D652116F34B42B6D3C3915EBBF8A6/77086](https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/B20D652116F34B42B6D3C3915EBBF8A6/77086)

![https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/1FF86B52A0CA4D549EE962F7D8A43C95/77164](https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/1FF86B52A0CA4D549EE962F7D8A43C95/77164)
### 1.4.系统模块
|模块名|模块说明|备注
|:-|:-|:-
|pachira-party|用户、权限、组织、客户端应用（client）管理功能|
|pachira-oauth2|基于Spring Cloud OAuth2实现的Oauth2服务端|调用pachira-party模块中的用户、client信息实现身份认证
|pachira-scheduler|基于quartz,zookeeper实现的分布式任务调度框架|支持手动编辑任务/多个节点自动分配

![https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/868DFCDAC35040B491A2988A724C9B86/77239](https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/868DFCDAC35040B491A2988A724C9B86/77239)
### 1.5.项目初始化
- 确保Eclipse正确安装配置了[lombok](https://www.projectlombok.org/download)插件，并按照[配置步骤](https://www.projectlombok.org/setup/eclipse)进行设置(**推荐在所有项目中使用lombok**)
- 将数据库脚本导入到数据库中(脚本的名字即对应的数据库名)
    - pachira-scheduler任务调度服务模块的脚本[pachira_scheduler.sql](etc/sql/pachira_scheduler.sql) 
    - pachira-party用户权限管理模块的脚本[pachira_party.sql](etc/sql/pahira_party.sql)
- 修改程序连接的数据库地址及用户名密码
    - pachira-scheduler模块中的[application.properties](pachira-scheduler/src/main/resources/application.properties)文件
    - pachiar-party模块中的[MybatisConfig.java](pachira-party/pachira-party-server/src/main/java/com/pachiraframework/party/config/MybatisConfig.java)文件
### 1.6.运行项目
按照如下步骤和地址启动并验证微服务的启动
- pachira-scheduler模块
    - 启动类[com.pachiraframework.scheduler.SchedulerApplication](pachira-scheduler/src/main/java/com/pachiraframework/scheduler/SchedulerApplication.java)
    - 访问链接 [http://localhost:8082/job/search](http://localhost:8082/job/search)
- pachiar-party模块
    - 启动类[com.pachiraframework.party.PachiraPartyApplication](pachira-party/pachira-party-server/src/main/java/com/pachiraframework/party/PachiraPartyApplication.java)
    - 访问链接 [http://localhost:8080/v1/party/1](http://localhost:8080/v1/party/1)
- pachira-oauth2模块
    - 启动类 [com.pachiraframework.oauth2.PachiraOauth2Application](pachira-oauth2/src/main/java/com/pachiraframework/oauth2/PachiraOauth2Application.java) 
    - 访问链接(POST请求) [http://localhost:8081/oauth/token?username=admin&password=123456&grant_type=password&scope=app&client_id=app1&client_secret=aaaaaaaa](http://localhost:8081/oauth/token?username=admin&password=123456&grant_type=password&scope=app&client_id=app1&client_secret=aaaaaaaa)
## 2.pachira-party模块架构设计
### 2.1 模块定位
该模块负责微服务框架中关于人员组织架构以及用户登录、权限控制模块的管理。
### 2.2 核心模型
|模型名称|中文|备注|
|:-|:-|:-
|Party|团体|可以是人员，也可以是组织|
|Person|人员|团体的一个具体形式，描述现实世界中关于人的属性
|PartyGroup|组织|团体的另一个具体形式，可以是一个团队、一个家庭，某个公司、或政府部门
|UserLogin|用户登录帐号|登录帐号密码的组合体，帐号可以是属于某个Person人员，也可以是某个PartyGroup组织
|UserLoginHistory|用户登录历史|登录的历史记录表
|PartyType|团体类型|用于描述系统中的团体类型，类型有父子结构
|Client|OAuth2接入客户端信息|使用OAuth2必须分配一个client接入方应用相关的信息

目前系统中已经提供的PartyType(团体类型)如下
![https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/073EFB26A8FA4FDA8364199E99C8A590/78022](https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/073EFB26A8FA4FDA8364199E99C8A590/78022)
### 2.3 数据库设计
![https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/8BAE4D077D0E4579B8B07806411D7994/77982](https://note.youdao.com/yws/public/resource/e5bb1aa758439bbedce6c5dd9a73a81c/xmlnote/8BAE4D077D0E4579B8B07806411D7994/77982)
## 3.pachira-oauth2模块架构设计
待完善
## 4.pachira-scheduler模块架构设计
待完善
## 5.pachira-message模块架构设计
待完善