# Mybatis设计思路

### 1.首先思考为什么要使用Mybatis框架

因为mybatis框架是解决持久层问题的落地方案。这些问题其实等同于我们平时业务开发中的需求，所以我们可以针对这些问题进行ooa、ood、oop。

### 2.ooa分析

整理一下jdbc访问数据库过程中所遇到的问题:

- 数据库连接的配置是硬编码;
- 频繁创建、关闭数据库连接;
- 需要自己封装结果集;
- 存在大量非业务重复性代码

### 3.ood设计

将ooa分析中的4个问题按照srp设计原则进行划分：

#### 3.1 宏观划分职责

##### 3.1.1 使用者（即应用程序）

提供框架配置文件、提供sql语句、提供入参、提供返回实体类型

##### 3.1.2 框架

配置读取与解析，管理数据库连接，封装非业务性的流程代码并执行sql，结果封装

#### 3.2 对问题建模

##### 3.2.1 使用者

- 入参 -> Parameter；
- 返回实体类型 -> <T> T

##### 3.2.2 框架

- 读取配置 -> Resource；
- 解析配置 -> XmlConfigParse；
- 配置文件 -> Configuration；
- 管理数据库连接 -> DataSource；
- 执行sql -> StatementHandler；
- 封装结果 -> ResultHandler

#### 3.3 对模型详细设计（指明其中的成员变量和方法）

##### 3.3.1 使用者

略

##### 3.3.2 框架

Resource  {

​	InputStream getResource(String path) ;

​	......

}

XmlConfigParse {

​	// 组合关系，同生命周期

​	private Configuration config = new Configuration();

​	Configuration parse(InputStream in);

​	......

}

Configuration {

​	// 对属性进行oo建模

​	private Environment env;

​	private Properties props;

​	......

}

DataSource {

​	// 直接使用第三方提供的 

​	......

}

StatementHandler {

​	<T> T executeQuery(String sql, Parameter param);

​	......

}

ResultHandler {

​	<T> T processResultSet(ResultSet resultSet, Class<T> classType);

​	......

}

#### 3.4 提供流程类

创建流程类，设计上面oo建模的实体的交互

1.对于Resource、XmlConfigParse、Configuration这些配置文件加载的交互过程，我们可以提供一个LoadService

2.对于DataSource、StatementHandler、ResultHandler这些执行sql的交互过程，我们可以提供一个Executor

3.步骤1和步骤2之间其实需要传递一些框架的配置，所以LoadService和Executor之间需要一个服务进行配置传递，我们可以提供TransferService

4.最后是对使用者暴露服务来访问数据，这个服务中应当封装Executor来对数据库进行真正的访问，我们可以提供一个SqlService

实际上LoadService就是SqlSessionFactoryBuilder、TransferService就是SqlSessionFactory、SqlService就是SqlSession，Mybatis也不是一上来就使用了设计模式，一定是先有上述分析，发现存在可以应用设计模式的场景，才使用设计模式

### 4.oop编程

对ood设计进行编程实现，在这个过程中遇到了新的问题，可以折回2、3步骤