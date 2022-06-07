package com.atguigu.myssm.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

public class ConnUtil {

  private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

  private static DataSource source;

  static {
    InputStream is = null;
    try {
      Properties pros = new Properties();
      is = ConnUtil.class.getClassLoader().getResourceAsStream(
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
    Connection conn = threadLocal.get();
    if (conn == null) {
      conn = source.getConnection();
      threadLocal.set(conn);
    }
    return threadLocal.get();
  }

  public static void closeConnection() throws SQLException {
    Connection conn = threadLocal.get();
    if (conn != null && !conn.isClosed()) {
      conn.close();
    }
    threadLocal.remove();
  }

  public static void closeDataSource() {
    ((DruidDataSource) source).close();
  }
}
