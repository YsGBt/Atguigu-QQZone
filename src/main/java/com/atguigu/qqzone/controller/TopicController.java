package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import java.util.List;
import javax.servlet.http.HttpSession;

public class TopicController {

  private TopicService topicService = null;

  public String topicDetail(Integer id, HttpSession session) {
    Topic topic = topicService.getTopicById(id);
    session.setAttribute("topic", topic);
    return "frames/detail";
  }

  public String delTopic(Integer topicId) {
    topicService.delTopic(topicId);
    return "redirect:topic.do?operate=getTopicList";
  }

  public String getTopicList(HttpSession session) {
    // 从session中获取当前用户信息
    UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
    // 再次查询当前用户关联的所有日志
    List<Topic> topics = topicService.getTopicList(userBasic);
    // 设置一下关联的日志列表(因为之前session中关联的friend的topicList和此刻数据库中不一致)
    userBasic.setTopicList(topics);
    // 重新覆盖一下friend中的信息(为什么不覆盖userBasic中？因为main.html页面迭代的是friend这个key中的数据)
    session.setAttribute("friend", userBasic);
    return "frames/main";
  }
}
