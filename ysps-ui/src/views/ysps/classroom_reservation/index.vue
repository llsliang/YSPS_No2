<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教室ID" prop="classroomId">
        <el-input
          v-model="queryParams.classroomId"
          placeholder="请输入教室ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入预约用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入预约用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约日期" prop="reservationDate">
        <el-date-picker clearable
          v-model="queryParams.reservationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预约日期">
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
          v-hasPermi="['ysps:classroom_reservation:add']"
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
          v-hasPermi="['ysps:classroom_reservation:edit']"
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
          v-hasPermi="['ysps:classroom_reservation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:classroom_reservation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classroom_reservationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="reservationId" />
      <el-table-column label="教室ID" align="center" prop="classroomId" />
      <el-table-column label="预约用户ID" align="center" prop="userId" />
      <el-table-column label="预约用户名" align="center" prop="username" />
      <el-table-column label="预约座位号" align="center" prop="seatNumber" />
      <el-table-column label="预约日期" align="center" prop="reservationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约状态" align="center" prop="status" />
      <el-table-column label="预约创建时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:classroom_reservation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:classroom_reservation:remove']"
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

    <!-- 添加或修改教室预约信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教室ID" prop="classroomId">
          <el-input v-model="form.classroomId" placeholder="请输入教室ID" />
        </el-form-item>
        <el-form-item label="预约用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入预约用户ID" />
        </el-form-item>
        <el-form-item label="预约用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入预约用户名" />
        </el-form-item>
        <el-form-item label="预约座位号" prop="seatNumber">
          <el-input v-model="form.seatNumber" placeholder="请输入预约座位号" />
        </el-form-item>
        <el-form-item label="预约日期" prop="reservationDate">
          <el-date-picker clearable
            v-model="form.reservationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约创建时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约创建时间">
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
import { listClassroom_reservation, getClassroom_reservation, delClassroom_reservation, addClassroom_reservation, updateClassroom_reservation } from "@/api/ysps/classroom_reservation";

export default {
  name: "Classroom_reservation",
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
      // 教室预约信息表格数据
      classroom_reservationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classroomId: null,
        userId: null,
        username: null,
        reservationDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classroomId: [
          { required: true, message: "教室ID不能为空", trigger: "blur" }
        ],
        reservationDate: [
          { required: true, message: "预约日期不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "预约开始时间不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "预约结束时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询教室预约信息列表 */
    getList() {
      this.loading = true;
      listClassroom_reservation(this.queryParams).then(response => {
        this.classroom_reservationList = response.rows;
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
        reservationId: null,
        classroomId: null,
        userId: null,
        username: null,
        seatNumber: null,
        reservationDate: null,
        startTime: null,
        endTime: null,
        status: null,
        createdAt: null
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
      this.ids = selection.map(item => item.reservationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教室预约信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reservationId = row.reservationId || this.ids
      getClassroom_reservation(reservationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改教室预约信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reservationId != null) {
            updateClassroom_reservation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClassroom_reservation(this.form).then(response => {
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
      const reservationIds = row.reservationId || this.ids;
      this.$modal.confirm('是否确认删除教室预约信息编号为"' + reservationIds + '"的数据项？').then(function() {
        return delClassroom_reservation(reservationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/classroom_reservation/export', {
        ...this.queryParams
      }, `classroom_reservation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
