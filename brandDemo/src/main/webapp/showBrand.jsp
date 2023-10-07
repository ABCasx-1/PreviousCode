<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>大全</title>
    <style>
        .el-table .warning-row {
            background: oldlace;
        }

        .el-table .message-row {
            background: #f5f2f2;
        }

        .el-table .success-row {
            background: #f0f9eb;
        }
    </style>
</head>
<body>
<div id="overview">
    <el-form :inline="true" :model="formInline" class="formInline" ref="formInline">
        <el-form-item>
            <template>
                <el-avatar>董</el-avatar>
            </template>
            <el-divider direction="vertical"></el-divider>
        </el-form-item>

        <el-form-item label="当前状态">
            <el-select v-model="formInline.status" prop="status" placeholder="请选择状态">
                <el-option label="启用" value="1"></el-option>
                <el-option label="禁用" value="0"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="品牌名称" prop="brandName">
            <el-input v-model="formInline.brandName" placeholder="请输入关键词"></el-input>
        </el-form-item>
        <el-form-item label="企业名称" prop="companyName">
            <el-input v-model="formInline.companyName" placeholder="请输入关键词"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit()" icon="el-icon-search"></el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="resetForm('formInline')" icon="el-icon-help">重置</el-button>
        </el-form-item>
        <el-form-item>
            <el-button @click="reSearch()" v-if="flag==='1'" type="warning" plain>← 返回</el-button>
        </el-form-item>
    </el-form>
    <!--    新增的表单格式为  brandName companyName  ordered description status-->
    <el-button type="primary" @click="dialogVisible = true"><i class="el-icon-plus"></i>新增品牌</el-button>
    <el-dialog :visible.sync="dialogVisible" @close="resetForm('ruleForm')">
        <el-form title="新增品牌" :rules="rules" ref="ruleForm" label-width="100px"
                 :model="ruleForm">
            <el-form-item label="品牌名称" prop="brandName">
                <el-input v-model="ruleForm.brandName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="企业名称" prop="companyName">
                <el-input v-model="ruleForm.companyName"></el-input>
            </el-form-item>
            <el-form-item label="销量" prop="ordered">
                <el-input v-model="ruleForm.ordered" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
                <el-input v-model="ruleForm.description" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="状态" prop="status">
                <el-switch v-model="ruleForm.status"
                           active-color="#13ce66"
                           inactive-color="grey"
                           active-value="1"
                           inactive-value="0"
                >
                </el-switch>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
    <el-button type="danger" icon="el-icon-delete" @click="deleteData()">删除选中</el-button>
    <el-button type="info" @click="toggleSelection()"><i class="el-icon-plus"></i>取消选择</el-button>
    <el-divider><i class="el-icon-apple"></i></el-divider>
    <!--    表单  -->
    <template>
        <el-table
                ref="multipleTable"
                :data="tableData"
                style="width: 100%"
                :row-class-name="tableRowClassName"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
            >
            </el-table-column>
            <el-table-column
                    label="序号"
                    type="index"
                    prop="index"
                    align="center"

            >
            </el-table-column>
            <el-table-column
                    prop="brandName"
                    label="品牌名称"
                    align="center"
            >
            </el-table-column>
            <el-table-column
                    prop="companyName"
                    label="企业名称"
                    align="center"
            >
            </el-table-column>
            <el-table-column
                    prop="ordered"
                    label="销量"
                    align="center"
            >
            </el-table-column>
            <el-table-column
                    prop="description"
                    label="描述"
                    align="center"
            >
            </el-table-column>
            <el-table-column
                    prop="status"
                    label="当前状态"
                    align="center"
            >
            </el-table-column>
            <el-table-column
                    prop="operation"
                    align="center"
                    label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" plain @click="edit()"><i class="el-icon-edit"></i></el-button>
                    <el-button type="danger" plain @click="deleteById(scope.$index, scope.row)"><i
                            class="el-icon-delete"></i></el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>
    <br>
    <!--    分页      -->
    <div class="block">
        <span class="demonstration"></span>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[2, 5, 10, 15]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalCount"
                background
                layout="prev, pager, next">
        </el-pagination>
    </div>
</div>
<script src="js/vue.js"></script>
<script src="js/axios-0.18.0.js"></script>
<script src="element-ui/lib/index.js"></script>
<link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
<script>
    new Vue({
        el: "#overview",
        data() {
            return {
                flag: "0",
                formInline: {
                    status: '',
                    companyName: '',
                    brandName: ''
                },
                pageSize: 5,
                totalCount: 100,
                currentPage: 1,
                multipleSelection: [],
                dialogVisible: false,
                ruleForm: {
                    id: "",
                    brandName: '',
                    companyName: '',
                    ordered: '',
                    status: '',
                    description: '',
                },
                rules: {
                    brandName: [{required: true, message: '请输入品牌名称', trigger: 'blur'},],
                    companyName: [{required: true, message: '请输入企业名称', trigger: 'blur'},],
                    ordered: [{required: true, message: '请输入销量(0-100之间的整数)', trigger: 'blur'}],
                },
                formLabelWidth: '120px',
                tableData: [],
                wantDeleteIds: [],
                multipleTable: [],
            }
        },
        mounted() {
            this.selectAll();
        },
        methods: {
            edit() {
                this.$message({
                    message: '此功能暂未开发~',
                    type: 'warning'
                });
            },
            reSearch() {
                this.currentPage = 1
                this.selectAll();
                this.flag = "0"
            },
            //分页
            handleSizeChange(val) {
                // 重新设置每页显示的条数
                this.pageSize = val;

            },
            handleCurrentChange(val) {
                // 重新设置当前页码
                this.currentPage = val;
                if (this.flag === "0") {
                    this.selectAll();
                } else this.onSubmit();
            },
            deleteById(index, row) {
                console.log(row.id);
                this.$confirm('此操作将删除本行数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    axios({
                        method: "post",
                        url: "http://localhost:8080/brandDemo/brand/deleteById",
                        data: row.id
                    }).then(function (resp) {
                        if (resp.data.toString() === "success") {
                            _this.$message({
                                message: '本行删除成功~',
                                type: 'success'
                            });
                            _this.selectAll();
                        }
                    })
                }).catch(() => {
                    //用户点击取消按钮
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            deleteData() {
                this.$confirm('此操作将删除选中数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let _this = this;
                    for (let i = 0; i < this.multipleSelection.length; i++) {
                        this.wantDeleteIds[i] = this.multipleSelection[i].id;
                    }
                    axios({
                        method: "post",
                        url: "http://localhost:8080/brandDemo/brand/deleteByIds",
                        data: this.wantDeleteIds
                    }).then(function (resp) {
                        if (resp.data.toString() === "success") {
                            _this.$message({
                                message: '删除成功~',
                                type: 'success'
                            });
                            _this.selectAll();
                        }
                    })
                }).catch(() => {
                    //用户点击取消按钮
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            selectAll() {
                axios({
                    method: "post",
                    url: "http://localhost:8080/brandDemo/brand/selectByPage?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize,
                    data: this.tableData
                }).then(resp => {
                    this.tableData = resp.data.rows;
                    //设置总记录数
                    this.totalCount = resp.data.totalCount;
                    //使用statusStr  ??
                    for (let i = 0; i < this.tableData.length; ++i) {
                        if (this.tableData[i].status === 1) {
                            this.tableData[i].status = "启用";
                        } else this.tableData[i].status = "禁用";
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            handleClose(done) {
                this.$confirm('确认取消添加？')
                        .then(_ => {
                            done();
                        })
                        .catch(_ => {
                        });
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log(this.ruleForm);
                        //提交数据
                        let _this = this;
                        axios({
                            method: "POST",
                            url: "http://localhost:8080/brandDemo/brand/add",
                            data: _this.ruleForm
                        }).then(function (resp) {
                            if (resp.data.toString() === "success") {
                                _this.dialogVisible = false;
                                _this.$message({
                                    message: '添加成功~',
                                    type: 'success'
                                });
                                _this.selectAll();
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            tableRowClassName({rowIndex}) {
                if (rowIndex % 3 === 0) {
                    return 'warning-row';
                } else if (rowIndex % 3 === 1) {
                    return 'success-row';
                }
                return 'message-row';
            },
            toggleSelection(rows) {
                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            onSubmit() {//页面上方的按条件检索
                console.log(this.formInline);
                let _this = this;
                axios({
                    method: "post",
                    url: "http://localhost:8080/brandDemo/brand/selectByPageAndCondition?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize,
                    data: this.formInline
                }).then(function (resp) {
                    _this.tableData = resp.data.rows;
                    console.log(_this.tableData)
                    //设置总记录数
                    _this.totalCount = resp.data.totalCount;
                    //使用statusStr  ??
                    for (let i = 0; i < _this.tableData.length; ++i) {
                        if (_this.tableData[i].status === 1) {
                            _this.tableData[i].status = "启用";
                        } else _this.tableData[i].status = "禁用";
                    }
                })
                this.flag = "1";
            }
        }
    })
</script>
</body>
</html>