package com.atguigu.qqzone.bean;

import java.io.Serializable;
import java.util.Date;

public class HostReply implements Serializable {

  //实现serializable接口。
  private static final long serialVersionUID = 73L;

  private Integer id;
  private String content;
  private Date hostReplyDate;

  private Integer authorId;

  private Integer replyId;

  private UserBasic author; // N:1
  private Reply reply; // 1:1

  public HostReply() {
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

  public Date getHostReplyDate() {
    return hostReplyDate;
  }

  public void setHostReplyDate(Date hostReplyDate) {
    this.hostReplyDate = hostReplyDate;
  }

  public UserBasic getAuthor() {
    return author;
  }

  public void setAuthor(UserBasic author) {
    this.author = author;
  }

  public Reply getReply() {
    return reply;
  }

  public void setReply(Reply reply) {
    this.reply = reply;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public Integer getReplyId() {
    return replyId;
  }

  public void setReplyId(Integer replyId) {
    this.replyId = replyId;
  }

  @Override
  public String toString() {
    return "HostReply{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", hostReplyDate=" + hostReplyDate +
        ", author=" + author +
        ", reply=" + reply +
        '}';
  }
}
