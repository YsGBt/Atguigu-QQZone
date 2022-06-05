package com.atguigu.qqzone.service.impl;

import com.atguigu.myssm.util.ConnUtil;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.service.TopicService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TopicServiceImpl implements TopicService {

  private TopicDAO topicDAO = null;

  @Override
  public List<Topic> getTopicList(UserBasic userBasic) {
    try {
      Connection conn = ConnUtil.getConnection();
      return topicDAO.getTopicList(conn, userBasic);
    } catch (SQLException e) {
      throw new RuntimeException("TopicService Failure: getTopicList");
    }
  }

}
