package com.atguigu.qqzone.service;

import com.atguigu.qqzone.bean.HostReply;

public interface HostReplyService {

  HostReply getHostReplyByReplyId(Integer replyId);

  void delHostReply(Integer replyId);

  void addHostReply(HostReply hostReply);
}
