<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<div id="login">
    <h2 align="center">登录</h2>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" class="demo-ruleForm">
        <el-row :gutter="20">
            <el-col :span="8" :offset="8">
                <el-form-item label="账号" prop="username">
                    <el-input v-model.number="ruleForm.username"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="8" :offset="8">
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="8" :offset="8">
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="8" :offset="8">
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</div>
<script src="js/vue.js"></script>
<script src="js/axios-0.18.0.js"></script>
<script src="element-ui/lib/index.js"></script>
<link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
<script>
    new Vue({
        el: "#login",
        data() {
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            let validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                flag:"0",
                ruleForm: {
                    password: '',
                    checkPass: '',
                    username: ''
                },
                rules: {
                    password: [
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    checkPass: [
                        {validator: validatePass2, trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {//登录处
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios({
                            method: "post",
                            url: "http://localhost:8080/brandDemo/user/login",
                            data: this.ruleForm
                        }).then(resp => {
                            console.log(resp.data)
                            if (resp.data.toString() === "success") {
                                console.log("true")
                                location.href = "showBrand.jsp"
                            }
                            else{
                                alert("账号或密码错误！")
                            }
                        })
                    } else {
                        alert('表单填写错误！');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    })
</script>
</body>
</html>