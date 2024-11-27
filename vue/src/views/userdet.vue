<template>
  <div style="padding: 10px">
    <div style="margin: 10px">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
    <div>
      <el-input v-model="search" placeholder="Please input:" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin: 5px" @click="load">搜索</el-button>
    </div>
    <el-table :data="tableData" style="width: 100%" border stripe>
      <el-table-column fixed prop="userID" label="id"  sortable />
      <el-table-column prop="nickName" label="用户名"  />
      <el-table-column prop="gender" label="性别"  />
      <el-table-column prop="province" label="省份"  />
      <el-table-column prop="city" label="城市"  />
      <el-table-column prop="country" label="国家"  />
      <el-table-column prop="createTime" label="时间"  />
      <el-table-column prop="state" label="角色" >
        <template #default="scope">
          <span v-if="scope.row.state === 1">用户</span>
          <span v-if="scope.row.state === 2">封禁用户</span>
        </template>
      </el-table-column>>
      <el-table-column prop="password" label="密码" />
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button  type="text" @click="handleEdit(scope.row)"
          >编辑</el-button
          >
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.userID)">
            <template #reference>
              <el-button type="text" >删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[5, 10, 20]"
          :current-page="currentPage"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      >
      </el-pagination>
      <el-dialog
          v-model="dialogVisible"
          title="编辑信息"
          width="30%"
          :before-close="handleClose"
      >
        <el-form model="form" label-width="120px">
          <el-form-item label="用户id">
            <el-input v-model="form.userID" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="form.nickName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="用户头像">
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
              ></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态">
            <el-input v-model="form.state" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%"></el-input>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save">提交</el-button>
      </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>




import request from "../utils/request";

export default {
  name: 'Home',

  components: {

  },
  data(){

    return {
      form:{},
      dialogVisible:false,
      search:'',
      currentPage:1,
      pageSize:10,
      total:10,
      tableData:[

      ]
    };
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/user",{
        params:{
          currentPage:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      //  传入变量
      }).then(res => {
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
    add(){
      this.dialogVisible=true
      this.form={}//清空表单操作
    },
    //保存表单
    save(){
      if(this.form.userID){
        //update
        request.put("/user",this.form).then(res => {
          console.log(res)
          if(res.code==='0'){
            this.$message({
              type:"success",
              message : "更新成功"
            })}else{
            this.$message({
              type:"error",
              message : res.msg
            })
          }
          this.load()//刷新数据
          this.dialogVisible=false
        })
      }else{
        request.post("/user/add",this.form).then(res => {
          console.log(res)
          if(res.code==='0'){
          this.$message({
            type:"success",
            message : "新增成功"
          })}else{
            this.$message({
              type:"error",
              message : res.msg
            })
          }
        })
        this.load()
        this.dialogVisible=false;
      }

    },
    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row))
      this.dialogVisible=true
    },
    handleSizeChange(pageSize){
      //改变个数
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(currentPage){

      this.currentPage=currentPage

      this.load()
    },
    handleDelete(userID){
      console.log(userID)
      request.delete("/user/"+userID).then(res => {
        console.log(res)
        if(res.code==='0'){
          this.$message({
            type:"success",
            message : "删除成功"
          })}else{
          this.$message({
            type:"error",
            message : res.msg
          })
        }
        this.load()
      })
    }
  }
}
</script>
