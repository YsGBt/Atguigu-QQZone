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

      // userBasic 保存的是登录者信息
      // friend 保存的是当前进入的是谁的空间
      session.setAttribute("userBasic", userBasic);
      session.setAttribute("friend", userBasic);

      return "index";
    } else {
      return "login";
    }
  }

  public String friend(Integer id, HttpSession session) {
    // 1. 根据id获取指定的用户信息
    UserBasic currentFriend = userBasicService.getUserBasicById(id);

    if (currentFriend != null) {
      List<Topic> topicList = topicService.getTopicList(currentFriend);

      currentFriend.setTopicList(topicList);

      session.setAttribute("friend", currentFriend);

      return "index";
    } else {
      return "error";
    }
  }
}
