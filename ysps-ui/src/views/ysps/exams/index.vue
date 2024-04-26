<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="考试科目" prop="subject">
        <el-input
          v-model="queryParams.subject"
          placeholder="请输入考试科目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试类型" prop="examtype">
        <el-select v-model="queryParams.examtype" placeholder="请选择考试类型" clearable>
          <el-option
            v-for="dict in dict.type.examtype"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="考试形式" prop="examformat">
        <el-select v-model="queryParams.examformat" placeholder="请选择考试形式" clearable>
          <el-option
            v-for="dict in dict.type.examformat"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="参考人员" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入参考人员"
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
          v-hasPermi="['ysps:exams:add']"
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
          v-hasPermi="['ysps:exams:edit']"
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
          v-hasPermi="['ysps:exams:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:exams:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="examsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考试id" align="center" prop="examid" />
      <el-table-column label="考试科目" align="center" prop="subject" />
      <el-table-column label="考试时间" align="center" prop="examtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.examtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考试地点" align="center" prop="examlocation" />
      <el-table-column label="考试类型" align="center" prop="examtype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.examtype" :value="scope.row.examtype"/>
        </template>
      </el-table-column>
      <el-table-column label="考试形式" align="center" prop="examformat">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.examformat" :value="scope.row.examformat"/>
        </template>
      </el-table-column>
      <el-table-column label="考试成绩" align="center" prop="grade" />
      <el-table-column label="参考人员" align="center" prop="username" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:exams:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:exams:remove']"
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

    <!-- 添加或修改考试信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考试科目" prop="subject">
          <el-input v-model="form.subject" placeholder="请输入考试科目" />
        </el-form-item>
        <el-form-item label="考试时间" prop="examtime">
          <el-date-picker clearable
            v-model="form.examtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择考试时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="考试地点" prop="examlocation">
          <el-input v-model="form.examlocation" placeholder="请输入考试地点" />
        </el-form-item>
        <el-form-item label="考试类型" prop="examtype">
          <el-select v-model="form.examtype" placeholder="请选择考试类型">
            <el-option
              v-for="dict in dict.type.examtype"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="考试形式" prop="examformat">
          <el-select v-model="form.examformat" placeholder="请选择考试形式">
            <el-option
              v-for="dict in dict.type.examformat"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="考试成绩" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入考试成绩" />
        </el-form-item>
        <el-form-item label="参考人员" prop="username">
          <el-input v-model="form.username" placeholder="请输入参考人员" />
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
import { listExams, getExams, delExams, addExams, updateExams } from "@/api/ysps/exams";

export default {
  name: "Exams",
  dicts: ['examtype', 'examformat'],
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
      // 考试信息表格数据
      examsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subject: null,
        examtype: null,
        examformat: null,
        username: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        subject: [
          { required: true, message: "考试科目不能为空", trigger: "blur" }
        ],
        examtime: [
          { required: true, message: "考试时间不能为空", trigger: "blur" }
        ],
        examlocation: [
          { required: true, message: "考试地点不能为空", trigger: "blur" }
        ],
        examtype: [
          { required: true, message: "考试类型不能为空", trigger: "change" }
        ],
        examformat: [
          { required: true, message: "考试形式不能为空", trigger: "change" }
        ],
        username: [
          { required: true, message: "参考人员不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询考试信息列表 */
    getList() {
      this.loading = true;
      listExams(this.queryParams).then(response => {
        this.examsList = response.rows;
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
        examid: null,
        subject: null,
        examtime: null,
        examlocation: null,
        examtype: null,
        examformat: null,
        grade: null,
        username: null
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
      this.ids = selection.map(item => item.examid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加考试信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const examid = row.examid || this.ids
      getExams(examid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改考试信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.examid != null) {
            updateExams(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExams(this.form).then(response => {
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
      const examids = row.examid || this.ids;
      this.$modal.confirm('是否确认删除考试信息编号为"' + examids + '"的数据项？').then(function() {
        return delExams(examids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/exams/export', {
        ...this.queryParams
      }, `exams_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
