package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.bean.Reply;
import com.atguigu.qqzone.bean.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import java.util.Date;
import javax.servlet.http.HttpSession;

public class ReplyController {

  private ReplyService replyService = null;

  public String addReply(String content, Integer topicId, HttpSession session) {
    UserBasic author = (UserBasic) session.getAttribute("userBasic");
    Reply reply = new Reply(content, new Date(), author.getId(), topicId);
    replyService.addReply(reply);

    // detail.html
    return "redirect:topic.do?operate=topicDetail&id=" + topicId;
  }

  public String delReply(Integer replyId, Integer topicId) {
    replyService.delReply(replyId);
    return "redirect:topic.do?operate=topicDetail&id=" + topicId;
  }
}
