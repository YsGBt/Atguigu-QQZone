package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.bean.Topic;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;
import java.util.List;
import javax.servlet.http.HttpSession;

public class UserController {

  private UserBasicService userBasicService = null;
  private TopicService topicService = null;

  public String login(String loginId, String pwd, HttpSession session) {
    // 1. 登录验证
    UserBasic userBasic = userBasicService.login(loginId, pwd);

    if (userBasic != null) {
      List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
      List<Topic> topicList = topicService.getTopicList(userBasic);

      userBasic.setFriendList(friendList);
      userBasic.setTopicList(topicList);

      session.setAttribute("userBasic", userBasic);

      return "index";
    } else {
      return "login";
    }
  }
}
