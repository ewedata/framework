Entity编码规范：

定义实体类和创建数据库表规则：
1.所有的实体类都继承BaseEntry基类
2.实体类和数据库表一一对应（即一个实体对应一张表）
3.数据库名 + "Entity" = 实体类名
4.实体类属性和表字段一一对应（如表issue_date对应实体issueDate,注意驼峰命名规则）

创建mapper.xml文件规则
1.所有的 Mybatis SQL 配置文件以-Mapper.xml结尾
