package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.bean.Reply;
import com.atguigu.qqzone.bean.Topic;
import java.sql.Connection;
import java.util.List;

public interface ReplyDAO {

  // 获取指定日志的回复列表
  List<Reply> getReplyList(Connection conn, Topic topic);

  // 添加回复
  boolean addReply(Connection conn, Reply reply);

  // 删除回复
  boolean deleteReply(Connection conn, Reply reply);

  boolean deleteReply(Connection conn, Integer id);

  // 根据id获取指定的Reply
  Reply getReply(Connection conn, Integer id);
}
