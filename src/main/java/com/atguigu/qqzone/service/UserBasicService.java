package com.atguigu.qqzone.service;

import com.atguigu.qqzone.bean.UserBasic;
import java.util.List;

public interface UserBasicService {

  UserBasic login(String loginId, String pwd);

  List<UserBasic> getFriendList(UserBasic userBasic);
}
