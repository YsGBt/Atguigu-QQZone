package com.atguigu.qqzone.service;

import com.atguigu.qqzone.bean.Reply;
import java.util.List;

public interface ReplyService {
  // 根据Topic的id获取关联的所有的回复
  List<Reply> getReplyListByTopicId(Integer topicId);

  // 添加回复
  void addReply(Reply reply);

  // 删除指定回复
  void delReply(Integer id);

  // 刪除指定的topic关联的所有回复
  void delReplyListByTopicId(Integer topicId);
}
