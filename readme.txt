//////////
1. 熟悉QQZone业务需求
  1) 用户登陆
  2) 登陆成功，显示主界面。
     左侧显示好友列表
     上端显示欢迎词
     如果不是自己的空间，显示超链接: 返回自己的空间
     下端显示日志列表
  3) 查看日志详情:
     - 日志本身的信息 (作者头像、昵称、日志的标题、日志内容、日志的日期)
     - 回复列表 (回复者头像、昵称、回复内容、回复日期)
     - 主人回复信息
  4) 删除日志，删除特定回复，删除特定主人回复
  5) 添加日志，添加回复，添加主人回复
  6) 点击左侧好友链接，进入好友的空间查看日志

//////////
2. 数据库设计
   1) 抽取实体: 用户登陆信息、用户详情信息、日志、回帖、主人回复
   2) 分析其中的属性:
      - 用户登陆信息: 账号、密码、头像、昵称
      - 用户详情信息: 真实姓名、星座、血型、邮箱、手机号...
      - 日志: 标题、内容、日期、作者
      - 回帖: 内容、日期、作者、日志
      - 主人回复: 内容、日期、作者、回帖
   3) 分析实体之间的关系
      - 用户登陆信息 : 用户详情信息 1:1
      - 用户 : 日志 1 : N
      - 日志 : 回帖 1: N
      - 回帖 : 主人回复 1 : 1
      - 用户 : 好友 N : N

//////////
3. 数据库的范式:
   1) 第一范式: 列不可再分
   2) 第二范式: 一张表只表达一层含义 (只描述一件事情)
   3) 第三范式: 表中的每一列和主建都是直接依赖关系，而不是间接依赖

   // 父类: java.util.Date 年月日时分秒毫秒
   // 子类: java.sql.Date 年月日
   // 子类: java.sql.Time 时分秒

//////////
4. 数据库设计的范式和数据库的查询性能很多时候是相悖的，我们需要根据实际的业务情况做一个选择:
   - 查询频次不高的情况下，我们更倾向于提高数据库的设计范式，从而提高存储效率
   - 查询频次较高的情况下，我们更倾向于牺牲数据库的规范度，降低数据库设计的范式，允许特定的冗余，从而提高查询的性能

//////////
5. QQZone 登录功能实现出现的四个错误:
  1) JDBC链接URL没修改，用的还是fruitdb
  2) fid as id
  3) rsmd.getColumnName() 和 rsmd.getColumnLable()
  4) Can not set com.atguigu.qqzone.bean.UserBasic field com.atguigu.qqzone.bean.Topic.author to java.lang.Integer
  5) left.html页面没有样式，同时数据也不展示。原因是: 我们是直接去请求的静态页面资源，那么并没有执行super.processTemplate(),
     也就是thymeleaf没有起作用
     解决方法:
     - 新增PageController，添加page方法:
     public String page(String page) {
         return page; // frames/left
     }
     目的是执行super.processTemplate()方法，让thymeleaf生效

//////////
6. top.html显示登录者昵称，判断是否是自己的空间
   1) 显示登录者昵称: ${session.userBasic.nickName}
   2) 判断是否是自己的空间: ${session.userBasic.id != session.friend.id}
      如果不是期望的效果，首先考虑将两者的id都显示出来

//////////
7. 点击左侧的好友链接，进入好友空间
   1) 根据id获取指定userBasic信息，查询这个userBasic的topicList，然后覆盖friend对应的value
   2) main页面应该显示friend中的topicList，而不是userBasic中的topicList
   3) 跳转后，在左侧(left)显示整个index页面
      - 问题: 在left页面显示整个index布局
      - 解决: 给超链接添加target属性: target="_top" 保证在顶层窗口显示整个index页面
   4) top.html页面需要修改: "欢迎进入${session.friend}"
      top.html页面的返回自己空间的超链接需要修改:
       <a th:href="@{/user.do(operate='friend', id=${session.userBasic.id})}" target="_top">

//////////
8. 日志详情页面实现
   1) 已知topic的id，需要根据topic的id获取特定topic
   2) 获取这个topic关联的所有回复
   3) 如果某个回复有主人回复，需要查询出来
   - 在TopicController中获取指定的topic
   - 具体这个topic中关联多少个Reply，由ReplyService内部实现
   4) 获取到的topic中的author只有id，那么需要在TopicService的getTopic方法中封装，在查询Topic本身信息时，
      同时调用UserBasicService中的获取UserBasic方法，给author属性赋值
   5) 同理，在Reply类中也有author，而且这个author也是只有id，那么我们也需要根据id查询得到author，最后设置关联

//////////
9. 添加回复

//////////
10. 删除回复
    1) 如果回复有关联的主人回复，需要先删除主人回复
    2) 删除回复
    3) Cannot delete or update a parent row: a foreign key constraint fails
       我们在删除回复表记录时，发现删除失败，原因是: 在主人回复表中仍然有记录引用待删除的回复
       如果需要删除主表数据，需要首先删除子表数据

//////////
11. 删除日志
    1) 删除日志，首先需要考虑是否有关联的回复
    2) 删除回复，首先需要考虑是否有关联的主人回复
    3) 另外，如果不是自己的空间，则不能删除日志

//////////
12. 日期和字符串之间的格式化
    // String -> java.util.Date
    String dateStr1 = "2021-12-30 12:59:59";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      Date date1 = sdf.parse(dateStr1);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    // Date -> String
    Date date2 = new Date();
    String dateStr2 = sdf.format(date2);

//////////
13. thymeleaf中使用#dates这个公共的内置对象
    ${#dates.format(topic.topicDate, 'yyyy-MM-dd HH:mm:ss')}

//////////
14. 系统启动时，我们访问的页面是: http://localhost:8080/Atguigu_QQZone_Web_exploded/page.do?operate=page&page=login
    为什么不是: http://localhost:8080/Atguigu_QQZone_Web_exploded/login.html
    答: 如果是后者，那么属于直接访问静态页面。那么页面上的thymeleaf表达式(标签)浏览器是不能识别的
        我们访问前者的目的其实就是要执行ViewBaseServlet中的processTemplate()

//////////
15. http://localhost:8080/Atguigu_QQZone_Web_exploded/page.do?operate=page&page=login 访问这个URL，执行的过程是什么样的?
    答: http://  localhost   :8080  /Atguigu_QQZone_Web_exploded   /page.do      ?operate=page&page=login
        协议     ServletIP    port    context root                 servlet path   query string
        1) DispatcherServlet -> urlPattern: *.do 拦截/page.do
        2) request.getServletPath() -> /page.do
        3) 解析处理字符串，将/page.do -> page
        4) 拿到page这个字符串，然后去IOC容器(BeanFactory) 中寻找 id=page 的bean对象 -> PageController.java
        5) 获取operate的值 -> page 因此得知，应该执行 PageController 中的 page() 方法
        6) PageController中的page方法定义如下:
           public String page(String page) {
              return page;
           }
        7) 在queryString: ?operate=page&page=login 中 获取请求参数，参数名是page，参数值是login
           因此page方法的参数page值会被赋上"login"
           然后return "login"
        8) 因为PageController的page方法是DispatcherServlet通过反射调用的
           method.invoke(...);
           因此，字符串"login"返回给DispatcherServlet
        9) DispatcherServlet接收到返回值，然后处理视图
           目前处理视图的方式有两种:
           1. 带前缀redirect:
           2. 不带前缀
           当前，返回"login"，不带前缀
           那么执行 super.processTemplate("login", req, resp);
        10) 此视ViewBaseServlet中的processTemplate方法会执行，效果是:
            在"login"这个字符串前面拼接"/" (其实就是配置文件中view-prefix配置的值)
            在"login"这个字符串后面拼接".html" (其实就是配置文件中view-suffix配置的值)
            最后进行服务器转发

//////////
16. 目前我们进行JavaWeb项目开发的"套路"是这样的:
    1. 拷贝myssm包
    2. 新建配置文件applicationContext.xml或者可以不叫这个名字，在web.xml中指定文件名
    3. 在web.xml文件中配置:
       1) 配置前缀和后缀，这样thymeleaf引擎就可以根据我们返回的字符串进行拼接，再跳转
         <context-param>
           <param-name>view-prefix</param-name>
           <param-value>/</param-value>
         </context-param>
         <context-param>
           <param-name>view-suffix</param-name>
           <param-value>.html</param-value>
         </context-param>
       2) 配置监听器要读取的参数，目的是加载IOC容器的配置文件(也就是applicationContext.xml)
         <context-param>
           <param-name>applicationContextLocation</param-name>
           <param-value>applicationContext.xml</param-value>
         </context-param>
    4. 开发具体的业务模块:
       1) 一个具体的业务模块纵向上有几个部分组成:
          - html 页面
          - POJO/Bean 类
          - DAO 接口和实现类
          - Service 接口和实现类
          - Controller 控制器组件
       2) 如果html页面有thymeleaf表达式，一定不能够直接方法，必须要经过PageController
       3) 在applicationContext.xml中配置 DAO, Service, Controller 三者之间的依赖关系
       4) DAO实现类中，继承BaseDAO，然后实现具体的接口，例如:
          public class UserDAOImpl extends BaseDAO<User> implements UserDAO{}
          需要注意BaseDAO后面的泛型不能写错
       5) Service是业务控制类，这一层我们只需要记住两点:
          - 业务逻辑我们都封装在service这一层，不要分散在Controller层，也不要出现在DAO层(我们需要保证DAO方法的单精度特性)
          - 当某一个业务功能需要使用其他模块的业务功能是，尽量调用别人的service，而不是深入到其他模块的DAO细节
       6) Controller类的编写规则:
          1. 在applicationContext.xml中配置Controller
            <bean id="user" class="com.atguigu.qqzone.controller.UserController">
              <property name="userBasicService" ref="userBasicService"/>
              <property name="topicService" ref="topicService"/>
            </bean>
          那么，用户在前端发请求时，对于的servlet path就是 /user.do ，其中"user"就是对于此处的bean的id值
          2. 在Controller中设计的方法名需要和operate的值一致
          public String login(String loginId, String pwd, HttpSession session) {
              return "index";
          }
          因此，我们的登录验证表单如下:
          <form th:action="@{/user.do}" method="post">
            <input type="hidden" name="operate" value="login"/>
          </form>
          3. 在表单中，组件的name属性和Controller中方法的参数名一致
          <input type="text" name="loginId"/>
          public String login(String loginId, String pwd, HttpSession session)
          4. 另外，需要注意的是: Controller中的方法中的参数不一定都是通过请求参数获取的
             if ("request".equals...) else is ("response".equals...) else if ("session".equals...) {
                直接赋值
             } else {
                此处才是从request的请求参数中获取
                request.getParameter("loginId");
             }
       7) DispatcherServlet中步骤大致分为:
          0. 从application作用域获取IOC容器
          1. 解析Servlet Path， 在IOC容器中寻找对于的Controller组件
          2. 准备operate指定的方法所要求的参数
          3. 调用operate指定的方法
          4. 接收到operate自定的方法的执行返回值，对返回值进行处理 - 视图处理
       8) 为什么DispatcherServlet能够从application作用域获取到IOC容器?
          ContextLoaderListener在容器启动时会执行初始化任务，而它的操作就是:
          1. 解析IOC的配置文件，创建一个一个的组件，并完成组件之间依赖关系的注入
          2. 将IOC容器保存到application作用域

//////////
17. 修改BaseDAO，让其支持properties文件以及druid数据连接池
    讲解了两种方式:
    1) 直接自己配置properties，然后读取，然后加载驱动
    2) 使用druid连接池技术，那么properties中的key是有要求的


