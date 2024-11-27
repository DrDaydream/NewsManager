<template>
  <div>
    <el-card>
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="用户id">
          <el-input v-model="form.adminid" style="width: 80%" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.nickName" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="头像url">
          <el-input v-model="form.avatarUrl" style="width: 80%"></el-input>
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
  name: "adperson",
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
      request.put("/admin",this.form).then(res => {
        console.log(res)
        if(res.code ==='0'){
          this.$message({
            type:"success",
            message:"Success!"
          })
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