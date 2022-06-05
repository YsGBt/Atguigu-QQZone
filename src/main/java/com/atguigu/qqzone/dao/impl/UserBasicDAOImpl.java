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
  public UserBasic getUserBasic(Connection conn, Integer id) {
    String sql = "select id, loginId, nickName, pwd, headImg from t_user_basic where id = ?";
    UserBasic userBasic = getBean(conn, sql, id);
    return userBasic;
  }

  @Override
  public List<UserBasic> getUserBasicList(Connection conn, UserBasic userBasic) {
    String sql = "select t_user_basic.id, t_user_basic.loginId, t_user_basic.nickName, t_user_basic.pwd, t_user_basic.headImg from \n"
        + "t_user_basic inner join t_friend on t_user_basic.id = t_friend.fid where t_friend.uid = ?;";
//    String sql = "select id, loginId, nickName, pwd, headImg from t_user_basic where id in(select fid from t_friend where uid = ?)";
    // 单表查询，只能获得id，所以需要之后在调用时再次访问数据库并根据id补全list
//    String sql = "select fid id from t_friend where uid = ?";
    List<UserBasic> userBasics = getBeanList(conn, sql, userBasic.getId());
    return userBasics;
  }
}
