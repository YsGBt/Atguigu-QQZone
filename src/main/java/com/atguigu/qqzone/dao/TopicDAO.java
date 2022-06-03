package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import java.sql.Connection;
import java.util.List;

public interface TopicDAO {

  // 获取指定用户的所有日志
  List<Topic> getTopicList(Connection conn, UserBasic userBasic);

  // 添加日志
  boolean addTopic(Connection conn, Topic topic);

  // 删除日志
  boolean deleteTopic(Connection conn, Topic topic);

  boolean deleteTopic(Connection conn, Integer id);

  // 获取特定日志信息
  Topic getTopicById(Connection conn, Integer id);
}
