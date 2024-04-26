<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="帖子标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入帖子标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="posttime">
        <el-date-picker clearable
          v-model="queryParams.posttime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后更新时间" prop="lastupdatetime">
        <el-date-picker clearable
          v-model="queryParams.lastupdatetime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="回复数量" prop="replycount">
        <el-input
          v-model="queryParams.replycount"
          placeholder="请输入回复数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="浏览量" prop="views">
        <el-input
          v-model="queryParams.views"
          placeholder="请输入浏览量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ysps:posts:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ysps:posts:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ysps:posts:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:posts:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="postsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="帖子id" align="center" prop="postid" />
      <el-table-column label="用户" align="center" prop="username" />
      <el-table-column label="帖子标题" align="center" prop="title" />
      <el-table-column label="帖子内容" align="center" prop="content" />
      <el-table-column label="发布时间" align="center" prop="posttime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.posttime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后更新时间" align="center" prop="lastupdatetime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastupdatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="回复数量" align="center" prop="replycount" />
      <el-table-column label="浏览量" align="center" prop="views" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:posts:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:posts:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改论坛对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item label="帖子标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入帖子标题" />
        </el-form-item>
        <el-form-item label="帖子内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="发布时间" prop="posttime">
          <el-date-picker clearable
            v-model="form.posttime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后更新时间" prop="lastupdatetime">
          <el-date-picker clearable
            v-model="form.lastupdatetime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="回复数量" prop="replycount">
          <el-input v-model="form.replycount" placeholder="请输入回复数量" />
        </el-form-item>
        <el-form-item label="浏览量" prop="views">
          <el-input v-model="form.views" placeholder="请输入浏览量" />
        </el-form-item>
        <el-divider content-position="center">存储用户对帖子的回复，包括回复内容和时间信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddYspsReplies">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteYspsReplies">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="yspsRepliesList" :row-class-name="rowYspsRepliesIndex" @selection-change="handleYspsRepliesSelectionChange" ref="yspsReplies">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="回帖的用户" prop="username" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.username" placeholder="请输入回帖的用户" />
            </template>
          </el-table-column>
          <el-table-column label="回复时间" prop="replytime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.replytime" type="date" value-format="yyyy-MM-dd" placeholder="请选择回复时间" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPosts, getPosts, delPosts, addPosts, updatePosts } from "@/api/ysps/posts";

export default {
  name: "Posts",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedYspsReplies: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 论坛表格数据
      postsList: [],
      // 存储用户对帖子的回复，包括回复内容和时间表格数据
      yspsRepliesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        title: null,
        content: null,
        posttime: null,
        lastupdatetime: null,
        replycount: null,
        views: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "帖子标题不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "帖子内容不能为空", trigger: "blur" }
        ],
        posttime: [
          { required: true, message: "发布时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询论坛列表 */
    getList() {
      this.loading = true;
      listPosts(this.queryParams).then(response => {
        this.postsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        postid: null,
        username: null,
        title: null,
        content: null,
        posttime: null,
        lastupdatetime: null,
        replycount: null,
        mediacontent: null,
        views: null
      };
      this.yspsRepliesList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.postid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加论坛";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const postid = row.postid || this.ids
      getPosts(postid).then(response => {
        this.form = response.data;
        this.yspsRepliesList = response.data.yspsRepliesList;
        this.open = true;
        this.title = "修改论坛";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.yspsRepliesList = this.yspsRepliesList;
          if (this.form.postid != null) {
            updatePosts(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPosts(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const postids = row.postid || this.ids;
      this.$modal.confirm('是否确认删除论坛编号为"' + postids + '"的数据项？').then(function() {
        return delPosts(postids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 存储用户对帖子的回复，包括回复内容和时间序号 */
    rowYspsRepliesIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 存储用户对帖子的回复，包括回复内容和时间添加按钮操作 */
    handleAddYspsReplies() {
      let obj = {};
      obj.username = "";
      obj.content = "";
      obj.replytime = "";
      this.yspsRepliesList.push(obj);
    },
    /** 存储用户对帖子的回复，包括回复内容和时间删除按钮操作 */
    handleDeleteYspsReplies() {
      if (this.checkedYspsReplies.length == 0) {
        this.$modal.msgError("请先选择要删除的存储用户对帖子的回复，包括回复内容和时间数据");
      } else {
        const yspsRepliesList = this.yspsRepliesList;
        const checkedYspsReplies = this.checkedYspsReplies;
        this.yspsRepliesList = yspsRepliesList.filter(function(item) {
          return checkedYspsReplies.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleYspsRepliesSelectionChange(selection) {
      this.checkedYspsReplies = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/posts/export', {
        ...this.queryParams
      }, `posts_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
