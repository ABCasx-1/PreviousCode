<%@ page contentType="text/html;charset=UTF-8" %>
<% response.setCharacterEncoding("utf-8"); %>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/brandDemo/registerServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <label for="username"></label><input name="username" type="text" id="username">
                    <br>
                    <%-- <span id="username_err" class="err_msg" >${register_msg}</span>--%>
                    <span id="username_err" class="err_msg" style="display: none;color:red ">用户名已存在~</span>
                </td>
            </tr>
            <tr>
                <td>密码</td>
                <td class="inputs">
                    <label for="password"></label><input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>
            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <label for="checkCode"></label><input name="checkCode" type="text" id="checkCode">
                    <img id="img" src="/brandDemo/checkCodeServlet" alt="load error">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>
<script src="js/axios-0.18.0.js"></script>
<script>
    function mutation(arr) {
        // 请把你的代码写在这里
        var a = arr[0].toLowerCase(); //第一个字符串 转小写
        var b = arr[1].toLowerCase(); //第二个字符串 转小写
        for(var i=0;i<b.length;i++){
            if(a.indexOf(b[i]) === -1){ //如果第一个字符串中没有包含第二个字符串的某个字符时，表明第一个字符串不包括第二个字符串的所有字符，返回false;
                return false;
            }
        }
        return true;

    }
    document.getElementById("username").onblur = function () {
        axios({
            method: "post",
            url: "http://localhost:8080/brandDemo/registerServlet",
            data: document.getElementById("username").value
        }).then(function (resp) {
           let a=resp.data.toString();
            if (a!=="true") {
                console.log("not have");
                document.getElementById("username_err").style.display = "none";
            } else {
                console.log("have");
                document.getElementById("username_err").style.display = "";
            }
        })
    }
    document.getElementById("changeImg").onclick = function () {
        document.getElementById("img").src = "/brandDemo/checkCodeServlet?" + new Date().getMilliseconds();
    }
</script>

</body>
</html>