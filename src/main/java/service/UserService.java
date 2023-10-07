package service;

import javaBean.User;

public interface UserService {
    User login(String username, String password);//登录
    void register(String username, String password);//注册
}
