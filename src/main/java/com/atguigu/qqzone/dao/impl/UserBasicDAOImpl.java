package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.UserBasicDAO;
import java.sql.Connection;
import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {

  @Override
  public UserBasic getUserBasic(Connection conn, String loginId, String pwd) {
    String sql = "select id, loginId, nickName, pwd, headImg from t_user_basic where loginId = ? and pwd = ?";
    UserBasic userBasic = getBean(conn, sql, loginId, pwd);
    return userBasic;
  }

  @Override
  public List<UserBasic> getUserBasicList(Connection conn, UserBasic userBasic) {
//    String sql = "select id, loginId, nickName, pwd, headImg from t_user_basic where id in(select fid from t_friend where uid = ?)";
    String sql = "select fid id from t_friend where uid = ?";
    List<UserBasic> userBasics = getBeanList(conn, sql, userBasic.getId());
    return userBasics;
  }
}
