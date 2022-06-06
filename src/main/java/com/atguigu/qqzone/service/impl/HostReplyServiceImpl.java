package com.atguigu.qqzone.service.impl;

import com.atguigu.myssm.util.ConnUtil;
import com.atguigu.qqzone.bean.HostReply;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.service.HostReplyService;
import java.sql.Connection;
import java.sql.SQLException;

public class HostReplyServiceImpl implements HostReplyService {

  private HostReplyDAO hostReplyDAO = null;

  @Override
  public HostReply getHostReplyByReplyId(Integer replyId) {
    try {
      Connection connection = ConnUtil.getConnection();
      HostReply hostReply = hostReplyDAO.getHostReplyByReplyId(connection, replyId);
      return hostReply;
    } catch (SQLException e) {
      throw new RuntimeException("HostReplyServiceImpl Failure: getHostReplyByReplyId");
    }
  }

  @Override
  public void delHostReply(Integer replyId) {
    try {
      Connection connection = ConnUtil.getConnection();
      hostReplyDAO.delHostReply(connection, replyId);
    } catch (SQLException e) {
      throw new RuntimeException("HostReplyServiceImpl Failure: delHostReply");
    }
  }

  @Override
  public void addHostReply(HostReply hostReply) {
    try {
      Connection connection = ConnUtil.getConnection();
      hostReplyDAO.addHostReply(connection, hostReply);
    } catch (SQLException e) {
      throw new RuntimeException("HostReplyServiceImpl Failure: addHostReply");
    }
  }
}
