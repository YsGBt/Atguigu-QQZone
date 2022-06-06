package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.bean.HostReply;
import com.atguigu.qqzone.service.HostReplyService;
import java.util.Date;

public class HostReplyController {

  private HostReplyService hostReplyService = null;

  public String addHostReply(String content, Integer topicId, Integer authorId, Integer replyId) {
    HostReply hostReply = new HostReply();
    hostReply.setContent(content);
    hostReply.setHostReplyDate(new Date());
    hostReply.setAuthorId(authorId);
    hostReply.setReplyId(replyId);
    hostReplyService.addHostReply(hostReply);
    return "redirect:topic.do?operate=topicDetail&id=" + topicId;
  }

}
