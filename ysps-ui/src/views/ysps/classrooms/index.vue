<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教室名称" prop="classname">
        <el-input
          v-model="queryParams.classname"
          placeholder="请输入教室名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教室容量" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入教室容量"
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
          v-hasPermi="['ysps:classrooms:add']"
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
          v-hasPermi="['ysps:classrooms:edit']"
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
          v-hasPermi="['ysps:classrooms:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:classrooms:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classroomsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教室唯一标识符" align="center" prop="classroomid" />
      <el-table-column label="教室名称" align="center" prop="classname" />
      <el-table-column label="教室容量" align="center" prop="capacity" />
      <el-table-column label="教室当前状态" align="center" prop="status" />
      <el-table-column label="当前落座人数" align="center" prop="currentSeating" />
      <el-table-column label="已预约座位数" align="center" prop="reservedSeats" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:classrooms:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:classrooms:remove']"
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

    <!-- 添加或修改教室状态对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教室名称" prop="classname">
          <el-input v-model="form.classname" placeholder="请输入教室名称" />
        </el-form-item>
        <el-form-item label="教室容量" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入教室容量" />
        </el-form-item>
        <el-form-item label="当前落座人数" prop="currentSeating">
          <el-input v-model="form.currentSeating" placeholder="请输入当前落座人数" />
        </el-form-item>
        <el-form-item label="已预约座位数" prop="reservedSeats">
          <el-input v-model="form.reservedSeats" placeholder="请输入已预约座位数" />
        </el-form-item>
        <el-divider content-position="center">教室座位预约信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddYspsClassroomReservation">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteYspsClassroomReservation">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="yspsClassroomReservationList" :row-class-name="rowYspsClassroomReservationIndex" @selection-change="handleYspsClassroomReservationSelectionChange" ref="yspsClassroomReservation">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="预约用户ID" prop="userId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userId" placeholder="请输入预约用户ID" />
            </template>
          </el-table-column>
          <el-table-column label="预约座位号" prop="seatNumber" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.seatNumber" placeholder="请输入预约座位号" />
            </template>
          </el-table-column>
          <el-table-column label="预约日期" prop="reservationDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.reservationDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择预约日期" />
            </template>
          </el-table-column>
          <el-table-column label="预约开始时间" prop="startTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.startTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择预约开始时间" />
            </template>
          </el-table-column>
          <el-table-column label="预约结束时间" prop="endTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.endTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择预约结束时间" />
            </template>
          </el-table-column>
          <el-table-column label="预约状态，0表示未到，1表示已到，2表示已取消" prop="status" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.status" placeholder="请选择预约状态，0表示未到，1表示已到，2表示已取消">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="预约创建时间" prop="createdAt" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.createdAt" type="date" value-format="yyyy-MM-dd" placeholder="请选择预约创建时间" />
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
import { listClassrooms, getClassrooms, delClassrooms, addClassrooms, updateClassrooms } from "@/api/ysps/classrooms";

export default {
  name: "Classrooms",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedYspsClassroomReservation: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 教室状态表格数据
      classroomsList: [],
      // 教室座位预约信息表格数据
      yspsClassroomReservationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classname: null,
        capacity: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classname: [
          { required: true, message: "教室名称不能为空", trigger: "blur" }
        ],
        capacity: [
          { required: true, message: "教室容量不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "教室当前状态不能为空", trigger: "change" }
        ],
        currentSeating: [
          { required: true, message: "当前落座人数不能为空", trigger: "blur" }
        ],
        reservedSeats: [
          { required: true, message: "已预约座位数不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询教室状态列表 */
    getList() {
      this.loading = true;
      listClassrooms(this.queryParams).then(response => {
        this.classroomsList = response.rows;
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
        classroomid: null,
        classname: null,
        capacity: null,
        status: null,
        currentSeating: null,
        reservedSeats: null
      };
      this.yspsClassroomReservationList = [];
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
      this.ids = selection.map(item => item.classroomid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加教室状态";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const classroomid = row.classroomid || this.ids
      getClassrooms(classroomid).then(response => {
        this.form = response.data;
        this.yspsClassroomReservationList = response.data.yspsClassroomReservationList;
        this.open = true;
        this.title = "修改教室状态";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.yspsClassroomReservationList = this.yspsClassroomReservationList;
          if (this.form.classroomid != null) {
            updateClassrooms(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClassrooms(this.form).then(response => {
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
      const classroomids = row.classroomid || this.ids;
      this.$modal.confirm('是否确认删除教室状态编号为"' + classroomids + '"的数据项？').then(function() {
        return delClassrooms(classroomids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 教室座位预约信息序号 */
    rowYspsClassroomReservationIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 教室座位预约信息添加按钮操作 */
    handleAddYspsClassroomReservation() {
      let obj = {};
      obj.userId = "";
      obj.seatNumber = "";
      obj.reservationDate = "";
      obj.startTime = "";
      obj.endTime = "";
      obj.status = "";
      obj.createdAt = "";
      this.yspsClassroomReservationList.push(obj);
    },
    /** 教室座位预约信息删除按钮操作 */
    handleDeleteYspsClassroomReservation() {
      if (this.checkedYspsClassroomReservation.length == 0) {
        this.$modal.msgError("请先选择要删除的教室座位预约信息数据");
      } else {
        const yspsClassroomReservationList = this.yspsClassroomReservationList;
        const checkedYspsClassroomReservation = this.checkedYspsClassroomReservation;
        this.yspsClassroomReservationList = yspsClassroomReservationList.filter(function(item) {
          return checkedYspsClassroomReservation.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleYspsClassroomReservationSelectionChange(selection) {
      this.checkedYspsClassroomReservation = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/classrooms/export', {
        ...this.queryParams
      }, `classrooms_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
