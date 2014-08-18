1: 全文搜索有两种方法: 1) 数据库的like搜索, 2) lucene搜索. 那么lucence好在那里呢? 
1. 在数据量比较大, 查询字段比较多的时候, 数据库的like查询效率比较低, 而lucene查询效率比较高.
2. lucene搜索到的结果相关度比较高, 而且会把相关度高的排到前面, 而数据库的like搜索只能查询到含有关键字的记录, 其内容的相关度比较低, 而且不能把相关度高的排到前面.
3. 采用lucene搜索能够进行高亮显示, 而数据库的like则做不到这一点(可以用js技术). 

1) 简历索引, 2) 查询索引.
分词的原理: 一元分词/ 二元分词/ 字典分词(最好, paoding) 
Lucene API / Compass API(对Lucene进行面向对象的封装) (Object Search Engine / Mapping) [OSEM]
类似于 
JDBC API / Hibernate API (ORM)

做软件, 强调面向对象封装
学习Compass很简单, 只要会Hibernate, 半小时就可以学会.

对于Hibernate:
 1) 设计实体, 并且完成实体的映射元数据(*.hibernate.xml/ @Entity) 


对于Compass:
只需要掌握5个注解就够了.
1) @Searchable --> 把该类定义为搜索实体, 该实体跟索引中的document进行映射, 而不是数据库中的字段.
@Searchable
public class Flight implements Serializable{
  @SearchableId --> 作用: 定义该属性为搜索实体的标识属性, 默认映射到document中的ID字段
  private Long id;
  @SearchProperty(name="c_name") --> 类似Hibernate中的@Column
  private String name;
}

@SearchableProperty(index, name, store):
1. Index.NOT_ANALYZED: 不分词,但建立索引.
2. Index.ANALYZED: 分词并建立索引, 默认值.

3. Store.YES: 保存到索引文件中去, 默认.
4. Store.NO: 不保存到索引文件中.
常用: @SearchableProperty(index=Index.NOT_ANALYZED, store=Stored.YES)

1.1: Compass对document的添加/删除功能:
Hibernate:
SessionFactory hibernate = new Configuration.configure().buildSessionFactory().
:configure(): 会在classpath中寻找hibernate.cfg.xml.
Session session = hibernate.openSession();
Transaction transaction = session.beginTransaction();

// 注解方式的Hibernate
SessionFactory hibernate = AnnotationConfiguration().addClass()...;

Compass:  (可以实现增量索引, 即现在添加的索引是在原来的基础上增加的, 而不是删除原来的. 如果没有这个功能, 则需要每天手动通过定时器去更新, 对于今天更新的数据, 必须在明天才能够看到)
Compass compass = new CompassConfiguration().configure().buildCompass();
configure(): 会在classpath下寻找compass.cfg.xml;
CompassSession session  = compass.openSession();
CompassTransacion transaction = session.beginTransaction();
session.save(entity); // 保存到索引document中.
// session.delete(entity)
session.commit();
session.close();
compass.close();

Compass 查询:
CompassHits hits = sessioin.find(keyword);
List<Product> products = new ArrayList<Product>();
int length = firstIndex + maxResult;
if(length > hits.length) 
  length = hist.length();
  for(int i=firstIndex; i<length; i++){
   Product product = (product) hist.data(i);
   product.setContent(hists.highlighter(i).fragment("content"));
   products.add(product);
  }
  qr.setResultList(products);
  qr.setTotalRecord(hits.length);
  hist.close();
 
 Compass 高级查询:
CompassQueryBuilder queryBuilder = session.queryBuilder();
// SQL: typeid=1 and (xxxx like ?) order by position desc
CompassHits hits = queyrBuilder.bool().addMust(queryBuilder.spanEq("typeid", typeid)).addMust(queryBuilder.queryString(keyword).toQuery())
.toQuery.addSort("position", SortPropertyType.FLOAT, SortDirection.REVERSE).hits;


// Compass编程式配置:
compass = new CompassConfiguration().setSetting(CompassEnvironment.CONNECTION, "file://indexfile") // 相对路径
// .setSetting(CompassEnvironment.CONNECTION, "ram://index") // 在内存中建立索引
// 字典分词(paoding) / 一元分词(默认)/ 二元分词/ 
.setSetting("compass.engine.analyzer.default.type","nte.paoding.analysis.analyzer.PaodingAnalyzer")
.setSetting("compas.engine.highlighter.default.formatter.simple.pre", "<font color='yellow'>")
.setSetting("compas.engine.highlighter.default.formatter.simple.post", "</font>")
.addScan("cn.itcast,bean").buildCompass();

paoding: 使用步骤
1. 将paoding-analysis.jar添加到classpath;
2. 将paoding的安装目录下的dic字典拷贝到src目录下;
3. 将按照目录下classes目录中的paoding-dic-home.properties拷贝到src目录下
  // 该文件只有一个属性: paoding.dic.home=classpath:dic
4. 如果遇到这种错误, 说dic不是一个目录, 原因是你的项目运行在一个带有空格和中文路径:
如: 你的项目路径/tomcat安装在Document and Settings目录下, 那么项目运行在一个带有空格的路径.