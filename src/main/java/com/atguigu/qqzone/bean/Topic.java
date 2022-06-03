package com.atguigu.qqzone.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Topic implements Serializable {

  //实现serializable接口。
  private static final long serialVersionUID = 73L;

  private Integer id;
  private String title;
  private String content;
  private Date topicDate;
  private UserBasic author; // N:1

  private List<Reply> replyList; // 1:N

  public Topic() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getTopicDate() {
    return topicDate;
  }

  public void setTopicDate(Date topicDate) {
    this.topicDate = topicDate;
  }

  public UserBasic getAuthor() {
    return author;
  }

  public void setAuthor(UserBasic author) {
    this.author = author;
  }

  public List<Reply> getReplyList() {
    return replyList;
  }

  public void setReplyList(List<Reply> replyList) {
    this.replyList = replyList;
  }

  @Override
  public String toString() {
    return "Topic{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", topicDate=" + topicDate +
        ", author=" + author +
        '}';
  }
}
