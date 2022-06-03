package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.bean.UserBasic;
import java.sql.Connection;
import java.util.List;

public interface UserBasicDAO {

  // 根据账号和密码获取特定用户信息
  UserBasic getUserBasic(Connection conn, String loginId, String pwd);

  // 获取指定用户的所有好友列表
  List<UserBasic> getUserBasicList(Connection conn, UserBasic userBasic);
}
