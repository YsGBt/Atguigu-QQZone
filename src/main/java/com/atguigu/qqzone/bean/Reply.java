package com.atguigu.qqzone.bean;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {

  //实现serializable接口。
  private static final long serialVersionUID = 73L;

  private Integer id;
  private String content;
  private Date replyDate;

  private Integer authorId;

  private Integer topicId;

  private UserBasic author; // N:1
  private Topic topic; // N:1

  private HostReply hostReply; // 1:1

  public Reply() {
  }
  public Reply(String content, Date replyDate, Integer authorId, Integer topicId) {
    this.content = content;
    this.replyDate = replyDate;
    this.authorId = authorId;
    this.topicId = topicId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getReplyDate() {
    return replyDate;
  }

  public void setReplyDate(Date replyDate) {
    this.replyDate = replyDate;
  }

  public UserBasic getAuthor() {
    return author;
  }

  public void setAuthor(UserBasic author) {
    this.author = author;
  }

  public Topic getTopic() {
    return topic;
  }

  public void setTopic(Topic topic) {
    this.topic = topic;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public Integer getTopicId() {
    return topicId;
  }

  public void setTopicId(Integer topicId) {
    this.topicId = topicId;
  }

  public HostReply getHostReply() {
    return hostReply;
  }

  public void setHostReply(HostReply hostReply) {
    this.hostReply = hostReply;
  }

  @Override
  public String toString() {
    return "Reply{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", replyDate=" + replyDate +
        ", author=" + author +
        ", topic=" + topic +
        '}';
  }
}
