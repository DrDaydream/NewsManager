<template>
 <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
   <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">后台管理</div>
   <div style="flex:1"></div>
   <div style="width: 100px"><el-dropdown>
    <span class="el-dropdown-link" style="text-align: center;padding-top: 20px">
      {{ userdet.nickName }}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
     <template #dropdown>
       <el-dropdown-menu>
         <el-dropdown-item @click="jump">个人信息</el-dropdown-item>
         <el-dropdown-item divided @click="$router.push('/Login')">退出系统</el-dropdown-item>
       </el-dropdown-menu>
     </template>
   </el-dropdown></div>
 </div>
</template>

<script>
export default {
  name: "Header",
  data(){

    return{

      userdet:JSON.parse(sessionStorage.getItem("userdet"))
    }


  },
  created() {


  },methods:{
   jump(){
     if(this.userdet.state==2){
        this.$router.push('/adperson')
     }else{
       this.$router.push('/Person')
     }
   }
  }
}
</script>

<style scoped>

</style>