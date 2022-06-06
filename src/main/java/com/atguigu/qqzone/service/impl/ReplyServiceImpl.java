package com.atguigu.qqzone.service.impl;

import com.atguigu.myssm.util.ConnUtil;
import com.atguigu.qqzone.bean.HostReply;
import com.atguigu.qqzone.bean.Reply;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReplyServiceImpl implements ReplyService {

  private ReplyDAO replyDAO = null;
  // 此处引入的是其他Bean对于的Service接口，而不是DAO接口
  // 其他Bean对于的业务逻辑是封装在Service层的，我需要调用别人的业务逻辑方法，而不是深入考虑人家内部的细节
  private HostReplyService hostReplyService = null;

  private UserBasicService userBasicService = null;

  @Override
  public List<Reply> getReplyListByTopicId(Integer topicId) {
    try {
      Connection connection = ConnUtil.getConnection();
      Topic topic = new Topic();
      topic.setId(topicId);
      List<Reply> replies = replyDAO.getReplyList(connection, topic);

      for (int i = 0; i < replies.size(); ++i) {
        Reply reply = replies.get(i);

        // 将关联的HostReply设置进去
        HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
        reply.setHostReply(hostReply);

        // 将关联的Author设置进去
        UserBasic author = userBasicService.getUserBasicById(reply.getAuthorId());
        reply.setAuthor(author);
      }

      return replies;
    } catch (SQLException e) {
      throw new RuntimeException("ReplyService Failure: getReplyListByTopicId");
    }
  }

  @Override
  public void addReply(Reply reply) {
    try {
      Connection connection = ConnUtil.getConnection();
      replyDAO.addReply(connection, reply);
    } catch (SQLException e) {
      throw new RuntimeException("ReplyService Failure: addReply");
    }
  }

  @Override
  public void delReply(Integer id) {
    try {
      Connection connection = ConnUtil.getConnection();
      // 1. 根据id获取到reply
      Reply reply = replyDAO.getReply(connection, id);
      if (reply != null) {
        // 2. 如果reply有关联的hostReply，则先删除hostReply
        hostReplyService.delHostReply(id);
        // 3. 删除reply
        replyDAO.deleteReply(connection, id);
      }
    } catch (SQLException e) {
      throw new RuntimeException("ReplyService Failure: delReply");
    }
  }

  @Override
  public void delReplyListByTopicId(Integer topicId) {
    try {
      Connection connection = ConnUtil.getConnection();
      Topic topic = new Topic();
      topic.setId(topicId);
      List<Reply> replyList = replyDAO.getReplyList(connection, topic);
      if (replyList != null) {
        for (Reply reply : replyList) {
          delReply(reply.getId());
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("ReplyService Failure: delReplyListByTopicId");
    }
  }
}
