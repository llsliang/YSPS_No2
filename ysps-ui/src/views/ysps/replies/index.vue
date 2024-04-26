<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="帖子ID" prop="postid">
        <el-input
          v-model="queryParams.postid"
          placeholder="请输入帖子ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回帖的用户" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入回帖的用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复时间" prop="replytime">
        <el-date-picker clearable
          v-model="queryParams.replytime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择回复时间">
        </el-date-picker>
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
          v-hasPermi="['ysps:replies:add']"
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
          v-hasPermi="['ysps:replies:edit']"
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
          v-hasPermi="['ysps:replies:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:replies:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="repliesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评论id" align="center" prop="replyid" />
      <el-table-column label="帖子ID" align="center" prop="postid" />
      <el-table-column label="回帖的用户" align="center" prop="username" />
      <el-table-column label="回复内容" align="center" prop="content" />
      <el-table-column label="回复时间" align="center" prop="replytime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.replytime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:replies:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:replies:remove']"
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

    <!-- 添加或修改存储用户对帖子的回复，包括回复内容和时间对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="帖子ID" prop="postid">
          <el-input v-model="form.postid" placeholder="请输入帖子ID" />
        </el-form-item>
        <el-form-item label="回帖的用户" prop="username">
          <el-input v-model="form.username" placeholder="请输入回帖的用户" />
        </el-form-item>
        <el-form-item label="回复内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="回复时间" prop="replytime">
          <el-date-picker clearable
            v-model="form.replytime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择回复时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReplies, getReplies, delReplies, addReplies, updateReplies } from "@/api/ysps/replies";

export default {
  name: "Replies",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 存储用户对帖子的回复，包括回复内容和时间表格数据
      repliesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        postid: null,
        username: null,
        content: null,
        replytime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        postid: [
          { required: true, message: "帖子ID不能为空", trigger: "blur" }
        ],
        username: [
          { required: true, message: "回帖的用户不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "回复内容不能为空", trigger: "blur" }
        ],
        replytime: [
          { required: true, message: "回复时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询存储用户对帖子的回复，包括回复内容和时间列表 */
    getList() {
      this.loading = true;
      listReplies(this.queryParams).then(response => {
        this.repliesList = response.rows;
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
        replyid: null,
        postid: null,
        username: null,
        content: null,
        replytime: null
      };
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
      this.ids = selection.map(item => item.replyid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加存储用户对帖子的回复，包括回复内容和时间";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const replyid = row.replyid || this.ids
      getReplies(replyid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改存储用户对帖子的回复，包括回复内容和时间";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.replyid != null) {
            updateReplies(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReplies(this.form).then(response => {
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
      const replyids = row.replyid || this.ids;
      this.$modal.confirm('是否确认删除存储用户对帖子的回复，包括回复内容和时间编号为"' + replyids + '"的数据项？').then(function() {
        return delReplies(replyids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/replies/export', {
        ...this.queryParams
      }, `replies_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
