<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工作地点" prop="jobLocation">
        <el-input
          v-model="queryParams.jobLocation"
          placeholder="请输入工作地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位类型" prop="jobType">
        <el-select v-model="queryParams.jobType" placeholder="请选择职位类型" clearable>
          <el-option
            v-for="dict in dict.type.job_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="薪资范围" prop="salaryRange">
        <el-input
          v-model="queryParams.salaryRange"
          placeholder="请输入薪资范围"
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
          v-hasPermi="['ysps:recruitment:add']"
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
          v-hasPermi="['ysps:recruitment:edit']"
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
          v-hasPermi="['ysps:recruitment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:recruitment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recruitmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="职位名称" align="center" prop="jobTitle" />
      <el-table-column label="工作地点" align="center" prop="jobLocation" />
      <el-table-column label="职位类型" align="center" prop="jobType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.job_type" :value="scope.row.jobType"/>
        </template>
      </el-table-column>
      <el-table-column label="发布日期" align="center" prop="postingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.postingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截止日期" align="center" prop="closingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作职责" align="center" prop="jobResponsibilities" />
      <el-table-column label="任职要求" align="center" prop="jobRequirements" />
      <el-table-column label="薪资范围" align="center" prop="salaryRange" />
      <el-table-column label="福利待遇" align="center" prop="benefits" />
      <el-table-column label="联系人" align="center" prop="contactPerson" />
      <el-table-column label="电子邮箱" align="center" prop="contactEmail" />
      <el-table-column label="联系电话" align="center" prop="contactPhone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:recruitment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:recruitment:remove']"
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

    <!-- 添加或修改招聘信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="职位名称" prop="jobTitle">
          <el-input v-model="form.jobTitle" placeholder="请输入职位名称" />
        </el-form-item>
        <el-form-item label="工作地点" prop="jobLocation">
          <el-input v-model="form.jobLocation" placeholder="请输入工作地点" />
        </el-form-item>
        <el-form-item label="职位类型" prop="jobType">
          <el-select v-model="form.jobType" placeholder="请选择职位类型">
            <el-option
              v-for="dict in dict.type.job_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布日期" prop="postingDate">
          <el-date-picker clearable
            v-model="form.postingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截止日期" prop="closingDate">
          <el-date-picker clearable
            v-model="form.closingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作职责" prop="jobResponsibilities">
          <el-input v-model="form.jobResponsibilities" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="任职要求" prop="jobRequirements">
          <el-input v-model="form.jobRequirements" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="薪资范围" prop="salaryRange">
          <el-input v-model="form.salaryRange" placeholder="请输入薪资范围" />
        </el-form-item>
        <el-form-item label="福利待遇" prop="benefits">
          <el-input v-model="form.benefits" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="contactEmail">
          <el-input v-model="form.contactEmail" placeholder="请输入电子邮箱" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
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
import { listRecruitment, getRecruitment, delRecruitment, addRecruitment, updateRecruitment } from "@/api/ysps/recruitment";

export default {
  name: "Recruitment",
  dicts: ['job_type'],
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
      // 招聘信息表格数据
      recruitmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        jobLocation: null,
        jobType: null,
        jobRequirements: null,
        salaryRange: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        jobTitle: [
          { required: true, message: "职位名称不能为空", trigger: "blur" }
        ],
        jobLocation: [
          { required: true, message: "工作地点不能为空", trigger: "blur" }
        ],
        jobType: [
          { required: true, message: "职位类型不能为空", trigger: "change" }
        ],
        postingDate: [
          { required: true, message: "发布日期不能为空", trigger: "blur" }
        ],
        closingDate: [
          { required: true, message: "截止日期不能为空", trigger: "blur" }
        ],
        jobResponsibilities: [
          { required: true, message: "工作职责不能为空", trigger: "blur" }
        ],
        jobRequirements: [
          { required: true, message: "任职要求不能为空", trigger: "blur" }
        ],
        salaryRange: [
          { required: true, message: "薪资范围不能为空", trigger: "blur" }
        ],
        contactPerson: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        contactEmail: [
          { required: true, message: "电子邮箱不能为空", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询招聘信息列表 */
    getList() {
      this.loading = true;
      listRecruitment(this.queryParams).then(response => {
        this.recruitmentList = response.rows;
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
        jobTitle: null,
        jobLocation: null,
        jobType: null,
        postingDate: null,
        closingDate: null,
        jobResponsibilities: null,
        jobRequirements: null,
        salaryRange: null,
        benefits: null,
        contactPerson: null,
        contactEmail: null,
        contactPhone: null,
        applicationMaterials: null
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
      this.ids = selection.map(item => item.jobTitle)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加招聘信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const jobTitle = row.jobTitle || this.ids
      getRecruitment(jobTitle).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改招聘信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.jobTitle != null) {
            updateRecruitment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecruitment(this.form).then(response => {
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
      const jobTitles = row.jobTitle || this.ids;
      this.$modal.confirm('是否确认删除招聘信息编号为"' + jobTitles + '"的数据项？').then(function() {
        return delRecruitment(jobTitles);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/recruitment/export', {
        ...this.queryParams
      }, `recruitment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
