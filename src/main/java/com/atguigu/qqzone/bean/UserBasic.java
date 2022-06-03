package com.atguigu.qqzone.bean;

import java.io.Serializable;
import java.util.List;

public class UserBasic implements Serializable {

  //实现serializable接口。
  private static final long serialVersionUID = 73L;

  private Integer id;
  private String longinId;
  private String nickName;
  private String pwd;
  private String headImg;

  private UserDetail userDetail; // 1:1
  private List<Topic> topicList; // 1:N
  private List<UserBasic> friendList; // N:N

  public UserBasic() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLonginId() {
    return longinId;
  }

  public void setLonginId(String longinId) {
    this.longinId = longinId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }

  public UserDetail getUserDetail() {
    return userDetail;
  }

  public void setUserDetail(UserDetail userDetail) {
    this.userDetail = userDetail;
  }

  public List<Topic> getTopicList() {
    return topicList;
  }

  public void setTopicList(List<Topic> topicList) {
    this.topicList = topicList;
  }

  public List<UserBasic> getFriendList() {
    return friendList;
  }

  public void setFriendList(List<UserBasic> friendList) {
    this.friendList = friendList;
  }

  @Override
  public String toString() {
    return "UserBasic{" +
        "id=" + id +
        ", longinId='" + longinId + '\'' +
        ", nickName='" + nickName + '\'' +
        ", pwd='" + pwd + '\'' +
        ", headImg='" + headImg + '\'' +
        '}';
  }
}
