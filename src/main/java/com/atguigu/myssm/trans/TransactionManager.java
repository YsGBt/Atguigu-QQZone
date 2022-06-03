package com.atguigu.myssm.trans;

import com.atguigu.myssm.util.ConnUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

  //开启事务
  public static void beginTrans() throws SQLException {
    ConnUtil.getConnection().setAutoCommit(false);
  }

  //提交事务
  public static void commit() throws SQLException {
    Connection conn = ConnUtil.getConnection();
    conn.commit();
    ConnUtil.closeConnection();
  }

  //回滚事务
  public static void rollback() throws SQLException {
    Connection conn = ConnUtil.getConnection();
    conn.rollback();
    ConnUtil.closeConnection();
  }
}
