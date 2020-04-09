# [spring data jpa 学习工程]
    本工程目的在于、搞清楚
        1.如何使用jpa和mysql进行交互 
        2.数据对象继承时与数据表的映射关系
        3.以及熟悉jpa提供的注解使用
        4.分页查询学习
        5.多表查询，级联查询
        6.数据库事务

### spring data jpa 使用的步骤
    1.添加配置文件（datasource.url, datasource.user, datasource.passwoard, datasource.driver, hibernate.hbm2ddl.auto）
    2.新建Entity对象 Person，设置属性@Id主键，@Column其余属性
    3.设置Repository接口，自定义接口方法：findById, readByAge等
    4.使用jpa生成的Repositery对象对数据表进行操作
    5.测试内容在： PersonRepositoryTest 

### spring data jpa entity数据对象继承和数据表映射操作：
    对于entity对象继承，@Inheritance注解 
        1.InheritanceType.SINGLE_TABLE （默认）会将子类和父类聚合到同一张表中，全部创建新的字段。 
        2.InheritanceType.TABLE_PER_CLASS 每个类创建一个表，每个表之间相互独立 
        3.InheritanceType.JOINED 每个类分别存放不同的表中，并构建外键进行关联管理
    对于多继承关系时 Inheritance.SINGLE_TABLE, Inheritance.JOINED俩种情况下，@DiscriminatorColumn, @DiscriminatorValue注解
        1.父类通过注解@DiscriminatorColumn(name=type).
          * 子类programmer @DiscriminatorValue(value="programmer") 
          * 子类Student @DiscriminatorValuer(value="student")
        2.会给person表中加一个字段为type, 而programmer数据type字段赋值为programmer, student中的type数据为student. 进行数据区分。
        3.测试内容在ProgrammerRepositoryTest, StudentRepositoryTest中

### pageRequest使用：
    通过pageRequest对象可以对数据进行分页，数据对象默认需要pageable对象进行分页，PageRequest对象是Pageable 的子类。可以完成分页查询数据的功能
    测试数据在PageableSqlTest中
   
### 级联查询
    @OneToOne, @OneToMany, @ManyToOne, @ManyToMany四种级联操作
    CascadeType字段取值：
    ·CascadeType.PERSIST （级联新建）
    ·CascadeType.REMOVE （级联删除）
    ·CascadeType.REFRESH （级联刷新）
    ·CascadeType.MERGE （级联更新)
    .CascadeType.ALL (上面的四种功能全部由)
    
    FetchType.LAZY (懒加载)
    FetchType.EARGE(急加载)
        * 获取懒加载数据时，数据请求必须和外部请求都在一个session中才能正常请求，否则会发生lazyloadException,
        所以为满足这种情况我们采用@Transactional注解，将懒加载请求和第一个请求都放在同一个session中来获取。
        
        * 懒加载使用场景，当不需要关联的数据时，采用懒加载可以减少不必要查询。优化性能。
    
    关联关系大体分为俩类：
        1.单向关联关系
            我们Comsuer中展示的是单向的一对多food
                1.@oneToMany会生成 comsuer表，food表， comsuer_foods表（存放俩张表的关联关系）
                2.采用@JoinColumn(value = "comsuer_id") 单向关系所以可以将comsuer_id作为外键放在food表中，
                这样数据库中：customer表，food表（中存在外键）
        
        2.双向关联关系