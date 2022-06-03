package com.atguigu.qqzone.dao.impl;

import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.UserBasicDAO;
import java.sql.Connection;
import java.util.List;

public class UserBasicDAOImpl implements UserBasicDAO {

  @Override
  public UserBasic getUserBasic(Connection conn, String loginId, String pwd) {
    return null;
  }

  @Override
  public List<UserBasic> getUserBasicList(Connection conn, UserBasic userBasic) {
    return null;
  }
}
