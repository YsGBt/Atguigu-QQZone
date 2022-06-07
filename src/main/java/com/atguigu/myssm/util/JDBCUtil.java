package com.atguigu.myssm.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * 实现JDBC对数据库操作的工具类,仅在测试使用,web项目中使用的是ConnUtil
 */
public abstract class JDBCUtil {

  private static DataSource source;

  static {
    InputStream is = null;
    try {
      Properties pros = new Properties();
      // 这里用JDBCUtil.class.getClassLoader()才能保证在服务器端依旧可以找到配置文件
      is = JDBCUtil.class.getClassLoader().getResourceAsStream(
          "druid.properties");
      pros.load(is);
      source = DruidDataSourceFactory.createDataSource(pros);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 实现Druid连接池，获取连接
   *
   * @return 连接池中的连接对应的Connection对象
   * @throws SQLException
   */
  public static Connection getConnection() throws SQLException {
    Connection connection = source.getConnection();
    return connection;
  }

  public static void closeDataSource() {
    ((DruidDataSource) source).close();
  }
}
