package com.atguigu.qqzone.service.impl;

import com.atguigu.myssm.util.ConnUtil;
import com.atguigu.qqzone.bean.Reply;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {

  private TopicDAO topicDAO = null;
  // 此处引用的是replyService，而不是replyDAO
  private ReplyService replyService = null;

  private UserBasicService userBasicService = null;

  @Override
  public List<Topic> getTopicList(UserBasic userBasic) {
    try {
      Connection conn = ConnUtil.getConnection();
      return topicDAO.getTopicList(conn, userBasic);
    } catch (SQLException e) {
      throw new RuntimeException("TopicService Failure: getTopicList");
    }
  }

  @Override
  public Topic getTopic(Integer id) {
    try {
      Connection conn = ConnUtil.getConnection();
      Topic topic = topicDAO.getTopicById(conn, id);
      UserBasic author = userBasicService.getUserBasicById(topic.getAuthorId());
      topic.setAuthor(author);
      return topic;
    } catch (SQLException e) {
      throw new RuntimeException("TopicService Failure: getTopic");
    }
  }

  @Override
  public Topic getTopicById(Integer id) {
    Topic topic = getTopic(id);
    List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
    topic.setReplyList(replyList);
    return topic;
  }

  @Override
  public void delTopic(Integer id) {
    try {
      Connection connection = ConnUtil.getConnection();
      replyService.delReplyListByTopicId(id);
      topicDAO.deleteTopic(connection, id);
    } catch (SQLException e) {
      throw new RuntimeException("TopicService Failure: delTopic");
    }
  }
}
