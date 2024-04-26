<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参与的活动ID" prop="eventid">
        <el-input
          v-model="queryParams.eventid"
          placeholder="请输入参与的活动ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参与者ID" prop="userid">
        <el-input
          v-model="queryParams.userid"
          placeholder="请输入参与者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报名参加活动的时间" prop="signuptime">
        <el-date-picker clearable
          v-model="queryParams.signuptime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择报名参加活动的时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否签到" prop="checkin">
        <el-select v-model="queryParams.checkin" placeholder="请选择是否签到" clearable>
          <el-option
            v-for="dict in dict.type.checkin"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['ysps:participants:add']"
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
          v-hasPermi="['ysps:participants:edit']"
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
          v-hasPermi="['ysps:participants:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:participants:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="participantsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="参与者唯一标识符" align="center" prop="participantid" />
      <el-table-column label="参与的活动ID" align="center" prop="eventid" />
      <el-table-column label="参与者ID" align="center" prop="userid" />
      <el-table-column label="报名参加活动的时间" align="center" prop="signuptime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signuptime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否签到" align="center" prop="checkin">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.checkin" :value="scope.row.checkin"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:participants:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:participants:remove']"
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

    <!-- 添加或修改参与者信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="参与的活动ID" prop="eventid">
          <el-input v-model="form.eventid" placeholder="请输入参与的活动ID" />
        </el-form-item>
        <el-form-item label="参与者ID" prop="userid">
          <el-input v-model="form.userid" placeholder="请输入参与者ID" />
        </el-form-item>
        <el-form-item label="报名参加活动的时间" prop="signuptime">
          <el-date-picker clearable
            v-model="form.signuptime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名参加活动的时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否签到" prop="checkin">
          <el-select v-model="form.checkin" placeholder="请选择是否签到">
            <el-option
              v-for="dict in dict.type.checkin"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listParticipants, getParticipants, delParticipants, addParticipants, updateParticipants } from "@/api/ysps/participants";

export default {
  name: "Participants",
  dicts: ['checkin'],
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
      // 参与者信息表格数据
      participantsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventid: null,
        userid: null,
        signuptime: null,
        checkin: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        participantid: [
          { required: true, message: "参与者唯一标识符不能为空", trigger: "blur" }
        ],
        eventid: [
          { required: true, message: "参与的活动ID不能为空", trigger: "blur" }
        ],
        userid: [
          { required: true, message: "参与者ID不能为空", trigger: "blur" }
        ],
        signuptime: [
          { required: true, message: "报名参加活动的时间不能为空", trigger: "blur" }
        ],
        checkin: [
          { required: true, message: "是否签到不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询参与者信息列表 */
    getList() {
      this.loading = true;
      listParticipants(this.queryParams).then(response => {
        this.participantsList = response.rows;
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
        participantid: null,
        eventid: null,
        userid: null,
        signuptime: null,
        checkin: null
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
      this.ids = selection.map(item => item.participantid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加参与者信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const participantid = row.participantid || this.ids
      getParticipants(participantid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改参与者信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.participantid != null) {
            updateParticipants(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addParticipants(this.form).then(response => {
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
      const participantids = row.participantid || this.ids;
      this.$modal.confirm('是否确认删除参与者信息编号为"' + participantids + '"的数据项？').then(function() {
        return delParticipants(participantids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/participants/export', {
        ...this.queryParams
      }, `participants_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
