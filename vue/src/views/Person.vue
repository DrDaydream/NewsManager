<template>
 <div>
   <el-card>
     <el-form ref="form" :model="form" label-width="120px">
       <el-form-item label="用户id">
         <el-input v-model="form.userID" style="width: 80%" disabled></el-input>
       </el-form-item>
       <el-form-item label="用户名">
         <el-input v-model="form.nickName" style="width: 80%"></el-input>
       </el-form-item>
       <el-form-item label="头像url">
         <el-input v-model="form.avatarUrl" style="width: 80%"></el-input>
       </el-form-item>
       <el-form-item label="性别">
         <el-radio v-model="form.gender" label="1">男</el-radio>
         <el-radio v-model="form.gender" label="2">女</el-radio>
         <el-radio v-model="form.gender" label="3">未知</el-radio>
       </el-form-item>
       <el-form-item label="省份">
         <el-input v-model="form.province" style="width: 80%"></el-input>
       </el-form-item>
       <el-form-item label="城市">
         <el-input v-model="form.city" style="width: 80%"></el-input>
       </el-form-item>
       <el-form-item label="国家">
         <el-input v-model="form.country" style="width: 80%"></el-input>
       </el-form-item>
       <el-form-item label="时间">
         <el-col :span="11">
           <el-date-picker
               v-model="form.createTime"
               type="datetime"
               value-format="YYYY-MM-DD HH:mm:ss"
               placeholder="Pick a date"
               style="width: 100%"
            disabled></el-date-picker>
         </el-col>
       </el-form-item>
       <el-form-item label="状态">
       <el-input v-model="form.state" style="width: 80%" disabled></el-input>
       </el-form-item>
       <el-form-item label="密码">
         <el-input v-model="form.password" style="width: 80%" type="password"></el-input>
       </el-form-item>

     </el-form>
     <div style="text-align: center">
       <el-button type="primary" @click="update">
         保存
       </el-button>
     </div>
   </el-card>

 </div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Person",
  data(){
    return{
      form:{}
    }
  },
  created() {
    let str = sessionStorage.getItem("userdet") || "{}"
    this.form=JSON.parse(str)
  },
  methods:{
    update(){
      request.put("/user",this.form).then(res => {
        console.log(res)
        if(res.code ==='0'){
          this.$message({
            type:"success",
            message:"Success!"
          })
          debugger
          sessionStorage.setItem("userdet",JSON.stringify(this.form))
        }else{this.$message({
          type:"error",
          message:res.msg
        })

        }
      })
    }
  }
}
</script>

<style scoped>

</style>