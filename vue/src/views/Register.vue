<template>
<div id="Building">
  <div  style="width:500px;margin: 150px auto">
    <div style="font-size: 40px;text-align: center;font-family: 华文行楷;color: silver;padding: 40px">
      欢迎注册
    </div>
    <el-form ref="form" :model="form" size="normal" :rules="rules">
      <el-form-item prop="nickName">
        <el-input v-model="form.nickName"  placeholder="请输入用户名"></el-input>
      </el-form-item >
      <el-form-item prop="password">
        <el-input type="password" v-model="form.password"  placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item prop="confirm">
        <el-input type="password" v-model="form.confirm"  placeholder="请确认密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="register">注 册</el-button>

      </el-form-item>

    </el-form>
  </div>
</div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Register",
  data(){
    return{
      form:{},
      rules: {
        nickName: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },

        ],
        confirm: [
          {
            required: true,
            message: '请输入确认密码',
            trigger: 'blur',
          },
        ]
      }
    }
  },
  methods:{
    register(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if(this.form.password !== this.form.confirm){
            this.$message({
              type:"error",
              message:"两次密码不一致!"
            })
            return
          }
          request.post("/user/Register",this.form).then(res => {
            if(res.code==='0'){
              this.$message({
                type:"success",
                message : "注册成功"
              })
              this.$router.push("/")//页面跳转-登陆成功后
            }else{
              this.$message({
                type:"error",
                message : res.msg
              })
            }
          })
        }})

    }
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