#[spring data jpa 学习工程]
本工程目的在于、搞清楚如何使用jpa和mysql进行交互 、数据对象继承时与数据表的映射关系、以及熟悉jpa提供的注解使用、分页查询学习、多表查询、数据库事务

---
### spring data jpa 使用的步骤
- 添加配置文件（datasource.url, datasource.user, datasource.passwoard, datasource.driver, hibernate.hbm2ddl.auto）
- 新建Entity对象 Person，设置属性@Id主键，@Column其余属性
- 设置Repository接口，自定义接口方法：findById, readByAge等
- 使用jpa生成的Repositery对象对数据表进行操作
- 测试内容在： PersonRepositoryTest 
---

### spring data jpa entity数据对象继承和数据表映射操作：
对于entity对象继承，@Inheritance注解 
- InheritanceType.SINGLE_TABLE （默认）会将子类和父类聚合到同一张表中，全部创建新的字段。 
- InheritanceType.TABLE_PER_CLASS 每个类创建一个表，每个表之间相互独立 
- InheritanceType.JOINED 每个类分别存放不同的表中，并构建外键进行关联管理
---
对于多继承关系时 Inheritance.SINGLE_TABLE, Inheritance.JOINED俩种情况下，@DiscriminatorColumn, @DiscriminatorValue注解
- 父类通过注解@DiscriminatorColumn(name=type).
    - 子类programmer @DiscriminatorValue(value="programmer") 
    - 子类Student @DiscriminatorValuer(value="student")
- 会给person表中加一个字段为type, 而programmer数据type字段赋值为programmer, student中的type数据为student. 进行数据区分。
- 测试内容在ProgrammerRepositoryTest, StudentRepositoryTest中
---

### pageRequest使用：
- 通过pageRequest对象可以对数据进行分页，数据对象默认需要pageable对象进行分页，PageRequest对象是Pageable 的子类。可以完成分页查询数据的功能
- 测试数据在PageableSqlTest中