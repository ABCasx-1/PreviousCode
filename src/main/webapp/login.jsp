<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setCharacterEncoding("utf-8"); %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
<div id="loginDiv" style="height: 350px">
    <form action="/user/login" method="post" id="form">
        <h1 id="loginMsg">登录</h1>
        <div id="errorMsg">${login_msg}${register_msg}</div>
        <p>账号:<label for="username"></label><input id="username" name="username" type="text"
                                                         value="${cookie.username.value}"></p>
        <p>密码:<label for="password"></label><input id="password" name="password" type="password"
                                                         value="${cookie.password.value}"></p>
        <p>记住我:<label for="remember"></label><input id="remember" name="remember" type="checkbox" value="1"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登录">
            <input type="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">点击去注册</a>
        </div>
    </form>
</div>
</body>
</html>