<template>
<div id="Building">
  <div  style="width:500px;margin: 150px auto">
    <div style="font-size: 40px;text-align: center;font-family: 华文行楷;color: silver;padding: 40px">
      欢迎登录新闻后台管理系统
    </div>
    <el-form ref="form" :model="form" size="normal" :rules="rules"  >
      <el-form-item prop="nickName">
        <el-input v-model="form.nickName"  placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="form.password"  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="login">登 录</el-button>
        <el-button type="Default" @click="$router.push('/Register')">注 册</el-button>
        <el-form-item label="请选择登录类型" style="color: floralwhite">
          <el-radio-group v-model="indexChoose" style="color: floralwhite">
            <el-radio label="0" style="color: floralwhite">用 户</el-radio>
            <el-radio label="1" style="color: floralwhite">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form-item>

    </el-form>
  </div>
</div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Login",

  data(){

    return{
      indexChoose:'0',
      stateChoose:3,
      form:{},

    }
  },
  created() {

    sessionStorage.removeItem("userdet")

  },
  methods:{
    login(){

      this.$refs['form'].validate((valid) => {
        if (valid) {
            if(this.indexChoose=='0'){
           request.post("/user/Login",this.form).then(res => {
             if(res.code==='0') {

               this.$message({
                 type: "success",
                 message: "登录成功"
               })

               sessionStorage.setItem("userdet", JSON.stringify(res.data))
               //页面跳转-登陆成功后
               this.$router.push("/Person")
           }})}else{

              request.post("/admin/Login",this.form).then(res => {
                if(res.code==='0'){
                  this.$message({
                    type:"success",
                    message : "登录成功"

                  })

                  sessionStorage.setItem("userdet",JSON.stringify(res.data))

                  this.$router.push("/adperson")//页面跳转-登陆成功后
                }else{
                  this.$message({
                    type:"error",
                    message : res.msg
                  })
                }
              })
            }




        }
      })//满足验证规则才会发送请求

    },

  }
}
</script>

<style>
#Building{
  background: url("../assets/ImageIcon/Starguardian.jpg");
  width: 100%;
  height: 100vh;
  position:fixed;
  background-size:100% 100%;
  overflow: hidden;
}

</style>