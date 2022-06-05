package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.util.JDBCUtil;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.TopicDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

class TopicDAOImplTest {

  private final TopicDAO topicDAO = new TopicDAOImpl();

  @Test
  void getTopicList() throws SQLException {
    Connection connection = JDBCUtil.getConnection();
    UserBasic userBasic = new UserBasic();
    userBasic.setId(1);
    List<Topic> topics = topicDAO.getTopicList(connection, userBasic);
    topics.forEach(System.out::println);
    connection.close();
  }

  @Test
  void addTopic() {
  }

  @Test
  void deleteTopic() {
  }

  @Test
  void testDeleteTopic() {
  }

  @Test
  void getTopicById() {
  }
}