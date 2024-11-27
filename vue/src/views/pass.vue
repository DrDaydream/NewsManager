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
    <el-table :data="tableData" v-if="this.form.state==1" style="width: 100%" border stripe>
      <el-table-column fixed prop="newsID" label="id"  sortable />
      <el-table-column prop="newsTitle" label="新闻标题"  />
      <el-table-column prop="newsType" label="新闻类别"  />
      <el-table-column prop="newsContent" label="新闻内容"  />
      <el-table-column prop="readCount" label="阅读量"  />
      <el-table-column prop="authorName" label="作者名称"  />
      <el-table-column prop="creatorID" label="作者ID"  />
      <el-table-column prop="createTime" label="时间"  />
      <el-table-column prop="state" label="状态" >
        <template #default="scope">
          <span v-if="scope.row.state === 1">未审核</span>
          <span v-if="scope.row.state === 2">已审核</span>
          <span v-if="scope.row.state === 3">已删除</span>
          <span v-if="scope.row.state === 4">不合法</span>
        </template>

      </el-table-column>
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button  type="text" @click="handleEdit(scope.row)"
          >编辑</el-button
          >
          <el-popconfirm title="Are you sure to delete this?" @confirm="handleDelete(scope.row.newsID)">
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
          <el-form-item label="新闻id">
            <el-input v-model="form.newsID" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="新闻标题">
            <el-input v-model="form.newsTitle" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="新闻类型">
            <el-input v-model="form.newsType" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="新闻内容">
            <el-input v-model="form.newsContent" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="图片a-url">
            <el-input v-model="form.coverImagefilea" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="阅读量:0">
            <el-input v-model="form.readCount" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="作者姓名">
            <el-input v-model="form.authorName" style="width: 80%" ></el-input>
          </el-form-item>
          <el-form-item label="作者id">
            <el-input v-model="form.creatorID" style="width: 80%" ></el-input>
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
            <el-input v-if="userdet.state===2" v-model="form.state" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="图片b--url">
            <el-input v-model="form.coverImagefileb" style="width: 80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="图片c--url">
            <el-input v-model="form.coverImagefilec" style="width: 80%" disabled></el-input>
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
  name: 'Pass',

  components: {

  },
  data(){

    return {
      userdet:JSON.parse(sessionStorage.getItem("userdet")),
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
      if(this.form.newsID){
        //update
        request.put("/news",this.form).then(res => {
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
        request.post("/news/add",this.form).then(res => {
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
    handleDelete(newsID){
      console.log(newsID)
      request.delete("/news/"+newsID).then(res => {
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