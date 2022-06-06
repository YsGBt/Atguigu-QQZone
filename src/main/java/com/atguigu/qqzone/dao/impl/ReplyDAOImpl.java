package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.bean.Reply;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.dao.ReplyDAO;
import java.sql.Connection;
import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

  @Override
  public List<Reply> getReplyList(Connection conn, Topic topic) {
    String sql = "select id, content, replyDate, author authorId, topic topicId from t_reply where topic = ?";
    List<Reply> replies = getBeanList(conn, sql, topic.getId());
    return replies;
  }

  @Override
  public boolean addReply(Connection conn, Reply reply) {
    String sql = "insert into t_reply(content, replyDate, author, topic) values(?,?,?,?)";
    int count = update(conn, sql, reply.getContent(), reply.getReplyDate(), reply.getAuthorId(), reply.getTopicId());
    return count == 1;
  }

  @Override
  public boolean deleteReply(Connection conn, Reply reply) {
    return deleteReply(conn, reply.getId());
  }

  @Override
  public boolean deleteReply(Connection conn, Integer id) {
    String sql = "delete from t_reply where id = ?";
    int count = update(conn, sql, id);
    return count == 1;
  }

  @Override
  public Reply getReply(Connection conn, Integer id) {
    String sql = "select id, content, replyDate, author authorId, topic topicId from t_reply where id = ?";
    Reply reply = getBean(conn, sql, id);
    return reply;
  }
}
