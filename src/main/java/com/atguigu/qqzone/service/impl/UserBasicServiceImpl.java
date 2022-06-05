package com.atguigu.qqzone.service.impl;

import com.atguigu.myssm.util.ConnUtil;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.service.UserBasicService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {

  private UserBasicDAO userBasicDAO = null;

  @Override
  public UserBasic login(String loginId, String pwd) {
    try {
      // 1. 登录验证
      Connection conn = ConnUtil.getConnection();
      UserBasic userBasic = userBasicDAO.getUserBasic(conn, loginId, pwd);
      return userBasic;
    } catch (SQLException e) {
      throw new RuntimeException("UserBasicService Failure: login");
    }
  }

  @Override
  public List<UserBasic> getFriendList(UserBasic userBasic) {
    try {
      Connection conn = ConnUtil.getConnection();
      List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(conn, userBasic);
      // 使用多表查询后这里就不需要了
//      List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
//      for (int i = 0; i < userBasicList.size(); ++i) {
//        UserBasic friend = userBasicList.get(i);
//        friend = userBasicDAO.getUserBasic(conn, friend.getId());
//        friendList.add(friend);
//      }
//      return friendList;
      return userBasicList;
    } catch (SQLException e) {
      throw new RuntimeException("UserBasicService Failure: getFriendList");
    }
  }
}
