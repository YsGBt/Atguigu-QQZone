package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.dao.TopicDAO;
import java.sql.Connection;
import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

  @Override
  public List<Topic> getTopicList(Connection conn, UserBasic userBasic) {
    String sql = "select id, title, content, topicDate, author authorId from t_topic where author = ?";
    List<Topic> topics = getBeanList(conn, sql, userBasic.getId());

    for (Topic topic : topics) {
      topic.setAuthor(userBasic);
    }

    return topics;
  }

  @Override
  public boolean addTopic(Connection conn, Topic topic) {
    return false;
  }

  @Override
  public boolean deleteTopic(Connection conn, Topic topic) {
    return deleteTopic(conn, topic.getId());
  }

  @Override
  public boolean deleteTopic(Connection conn, Integer id) {
    return false;
  }

  @Override
  public Topic getTopicById(Connection conn, Integer id) {
    return null;
  }
}
