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
      <el-table-column fixed prop="reviewID" label="评论id"  sortable />
      <el-table-column prop="reviewID" label="新闻id"  />
      <el-table-column prop="parentreviewID" label="父评论id"  />
      <el-table-column prop="reviewContent" label="评论内容"  />
      <el-table-column prop="createTime" label="评论时间"  />
      <el-table-column prop="state" label="状态"  />
      <el-table-column prop="commenter" label="评论者id"  />
      <el-table-column prop="zan" label="点赞量"  />
      <el-table-column prop="cai" label="点踩量"  />
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button  type="text" @click="handleEdit(scope.row)"
          >编辑</el-button
          >
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.reviewID)">
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
          <el-form-item label="评论ID">
            <el-input v-model="form.reviewID" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="新闻ID">
            <el-input v-model="form.reviewID" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="父评论ID">
            <el-input v-model="form.parentreviewID" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="评论内容">
            <el-input v-model="form.reviewContent" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="评论时间" disabled="true">
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
          <el-form-item label="评论者id">
            <el-input v-model="form.commenter" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="点赞量">
            <el-input v-model="form.zan" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="点踩量">
            <el-input v-model="form.cai" style="width: 80%" disabled></el-input>
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
  name: 'Reviewdet',

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
      request.get("/news",{
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
      if(this.form.reviewID){
        //update
        request.put("/review",this.form).then(res => {
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
        request.post("/review/add",this.form).then(res => {
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
    handleDelete(reviewID){
      console.log(reviewID)
      request.delete("/review/"+reviewID).then(res => {
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