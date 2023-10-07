/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-06-24 13:56:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class temp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>登录</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"login\">\r\n");
      out.write("    <h2 align=\"center\">登录</h2>\r\n");
      out.write("    <el-form :model=\"ruleForm\" status-icon :rules=\"rules\" ref=\"ruleForm\" class=\"demo-ruleForm\">\r\n");
      out.write("        <el-row :gutter=\"20\">\r\n");
      out.write("            <el-col :span=\"8\" :offset=\"8\">\r\n");
      out.write("                <el-form-item label=\"账号\" prop=\"username\">\r\n");
      out.write("                    <el-input v-model.number=\"ruleForm.username\"></el-input>\r\n");
      out.write("                </el-form-item>\r\n");
      out.write("            </el-col>\r\n");
      out.write("        </el-row>\r\n");
      out.write("        <el-row :gutter=\"20\">\r\n");
      out.write("            <el-col :span=\"8\" :offset=\"8\">\r\n");
      out.write("                <el-form-item label=\"密码\" prop=\"password\">\r\n");
      out.write("                    <el-input type=\"password\" v-model=\"ruleForm.password\" autocomplete=\"off\"></el-input>\r\n");
      out.write("                </el-form-item>\r\n");
      out.write("            </el-col>\r\n");
      out.write("        </el-row>\r\n");
      out.write("        <el-row :gutter=\"20\">\r\n");
      out.write("            <el-col :span=\"8\" :offset=\"8\">\r\n");
      out.write("                <el-form-item label=\"确认密码\" prop=\"checkPass\">\r\n");
      out.write("                    <el-input type=\"password\" v-model=\"ruleForm.checkPass\" autocomplete=\"off\"></el-input>\r\n");
      out.write("                </el-form-item>\r\n");
      out.write("            </el-col>\r\n");
      out.write("        </el-row>\r\n");
      out.write("        <el-row :gutter=\"20\">\r\n");
      out.write("            <el-col :span=\"8\" :offset=\"8\">\r\n");
      out.write("                <el-form-item>\r\n");
      out.write("                    <el-button type=\"primary\" @click=\"submitForm('ruleForm')\">登录</el-button>\r\n");
      out.write("                    <el-button @click=\"resetForm('ruleForm')\">重置</el-button>\r\n");
      out.write("                </el-form-item>\r\n");
      out.write("            </el-col>\r\n");
      out.write("        </el-row>\r\n");
      out.write("    </el-form>\r\n");
      out.write("</div>\r\n");
      out.write("<script src=\"js/vue.js\"></script>\r\n");
      out.write("<script src=\"js/axios-0.18.0.js\"></script>\r\n");
      out.write("<script src=\"element-ui/lib/index.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"element-ui/lib/theme-chalk/index.css\">\r\n");
      out.write("<script>\r\n");
      out.write("    new Vue({\r\n");
      out.write("        el: \"#login\",\r\n");
      out.write("        data() {\r\n");
      out.write("            let validatePass = (rule, value, callback) => {\r\n");
      out.write("                if (value === '') {\r\n");
      out.write("                    callback(new Error('请输入密码'));\r\n");
      out.write("                } else {\r\n");
      out.write("                    if (this.ruleForm.checkPass !== '') {\r\n");
      out.write("                        this.$refs.ruleForm.validateField('checkPass');\r\n");
      out.write("                    }\r\n");
      out.write("                    callback();\r\n");
      out.write("                }\r\n");
      out.write("            };\r\n");
      out.write("            let validatePass2 = (rule, value, callback) => {\r\n");
      out.write("                if (value === '') {\r\n");
      out.write("                    callback(new Error('请再次输入密码'));\r\n");
      out.write("                } else if (value !== this.ruleForm.password) {\r\n");
      out.write("                    callback(new Error('两次输入密码不一致!'));\r\n");
      out.write("                } else {\r\n");
      out.write("                    callback();\r\n");
      out.write("                }\r\n");
      out.write("            };\r\n");
      out.write("            return {\r\n");
      out.write("                flag:\"0\",\r\n");
      out.write("                ruleForm: {\r\n");
      out.write("                    password: '',\r\n");
      out.write("                    checkPass: '',\r\n");
      out.write("                    username: ''\r\n");
      out.write("                },\r\n");
      out.write("                rules: {\r\n");
      out.write("                    password: [\r\n");
      out.write("                        {validator: validatePass, trigger: 'blur'}\r\n");
      out.write("                    ],\r\n");
      out.write("                    checkPass: [\r\n");
      out.write("                        {validator: validatePass2, trigger: 'blur'}\r\n");
      out.write("                    ]\r\n");
      out.write("                }\r\n");
      out.write("            };\r\n");
      out.write("        },\r\n");
      out.write("        methods: {//登录处\r\n");
      out.write("            submitForm(formName) {\r\n");
      out.write("                this.$refs[formName].validate((valid) => {\r\n");
      out.write("                    if (valid) {\r\n");
      out.write("                        axios({\r\n");
      out.write("                            method: \"post\",\r\n");
      out.write("                            url: \"http://localhost:8080/brandDemo/user/login\",\r\n");
      out.write("                            data: this.ruleForm\r\n");
      out.write("                        }).then(resp => {\r\n");
      out.write("                            console.log(resp.data)\r\n");
      out.write("                            if (resp.data.toString() === \"success\") {\r\n");
      out.write("                                console.log(\"true\")\r\n");
      out.write("                                location.href = \"showBrand.jsp\"\r\n");
      out.write("                            }\r\n");
      out.write("                            else{\r\n");
      out.write("                                alert(\"账号或密码错误！\")\r\n");
      out.write("                            }\r\n");
      out.write("                        })\r\n");
      out.write("                    } else {\r\n");
      out.write("                        alert('表单填写错误！');\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }\r\n");
      out.write("                });\r\n");
      out.write("            },\r\n");
      out.write("            resetForm(formName) {\r\n");
      out.write("                this.$refs[formName].resetFields();\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("    })\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
