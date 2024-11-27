<template>
<div>
  <el-menu
      style="width: 200px;min-height: calc(100vh - 50px)"
      default-active="user"
      router
      class="el-menu-vertical-demo"
  > <el-menu-item v-if="userdet.state===1" index="usnewdet" >我的新闻</el-menu-item>
    <el-menu-item v-if="userdet.state===2" index="userdet" >用户管理</el-menu-item>
    <el-menu-item index="reviewdet" >评论管理</el-menu-item>
    <el-menu-item index="newsdet" >数据管理</el-menu-item>
    <el-menu-item v-if="userdet.state===2" index="pass" >审核</el-menu-item>
  </el-menu>
</div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "asideCom",
  data(){
    return{
      userdet:{},
      path:this.$route.path
    }
  },
  created() {
    let userStr=sessionStorage.getItem("userdet") || "{}"
    this.userdet= JSON.parse(userStr)
    request.get("/user/"+this.userdet.userID)
        .then(res => {
      if(res.code==='0'){
        this.userdet=res.data
      }
    })
  }
}
</script>

<style scoped>

</style>