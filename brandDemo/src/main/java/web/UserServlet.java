package web;

import com.alibaba.fastjson.JSON;
import javaBean.Brand;
import javaBean.User;
import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserServiceImpl userService = new UserServiceImpl();

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        User user1 = JSON.parseObject(params, User.class);
        User user = userService.login(user1.getUsername(), user1.getPassword());
        System.out.println("查询到的用户为 " + user);
        if (user != null) {
            //登陆成功 写数据
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("success");
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为Brand对象
        User user1 = JSON.parseObject(params, User.class);
        User exits = userService.search(user1.getUsername());
        if (exits == null) {
            userService.register(user1.getUsername(), user1.getPassword());
        }
        else{
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write("fail");
        }
    }
}
