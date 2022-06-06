package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.bean.HostReply;
import com.atguigu.qqzone.dao.HostReplyDAO;
import java.sql.Connection;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

  @Override
  public HostReply getHostReplyByReplyId(Connection conn, Integer replyId) {
    String sql = "select id, content, hostReplyDate, author authorId, reply replyId from t_host_reply where reply = ?";
    HostReply hostReply = getBean(conn, sql, replyId);
    return hostReply;
  }

  @Override
  public boolean delHostReply(Connection conn, Integer replyId) {
    String sql = "delete from t_host_reply where reply = ?";
    int count = update(conn, sql, replyId);
    return count == 1;
  }

  @Override
  public boolean addHostReply(Connection conn, HostReply hostReply) {
    String sql = "insert into t_host_reply(content, hostReplyDate, author, reply) values(?,?,?,?)";
    int count = update(conn, sql, hostReply.getContent(), hostReply.getHostReplyDate(), hostReply.getAuthorId(), hostReply.getReplyId());
    return count == 1;
  }
}
