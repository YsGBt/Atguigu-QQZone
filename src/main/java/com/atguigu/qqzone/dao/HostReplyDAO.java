package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.bean.HostReply;
import java.sql.Connection;

public interface HostReplyDAO {

  // 根据replyId查询关联的HostReply实体
  HostReply getHostReplyByReplyId(Connection conn, Integer replyId);

  // 删除特定的HostReply
  boolean delHostReply(Connection conn, Integer replyId);

  // 添加特定的HostReply
  boolean addHostReply(Connection conn, HostReply hostReply);
}
