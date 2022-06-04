package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.util.JDBCUtil;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.UserBasicDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserBasicDAOImplTest {

  private final UserBasicDAO userBasicDAO = new UserBasicDAOImpl();

  @Test
  void getUserBasic() throws SQLException {
    Connection connection = JDBCUtil.getConnection();
    UserBasic userBasic = userBasicDAO.getUserBasic(connection, "u002", "ok");
    System.out.println(userBasic);
    connection.close();
  }

  @Test
  void getUserBasicList() throws SQLException {
    Connection connection = JDBCUtil.getConnection();
    UserBasic userBasic = new UserBasic();
    userBasic.setId(1);
    List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(connection, userBasic);
    userBasicList.forEach(System.out::println);
    connection.close();
  }
}