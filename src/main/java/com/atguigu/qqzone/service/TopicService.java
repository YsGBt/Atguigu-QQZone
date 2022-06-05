package com.atguigu.qqzone.service;

import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import java.util.List;

public interface TopicService {

  // 查询特定用户的日志列表
  List<Topic> getTopicList(UserBasic userBasic);

}
