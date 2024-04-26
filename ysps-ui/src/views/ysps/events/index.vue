<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="eventname">
        <el-input
          v-model="queryParams.eventname"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动时间" prop="eventtime">
        <el-date-picker clearable
          v-model="queryParams.eventtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择活动时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动类型" prop="eventtype">
        <el-select v-model="queryParams.eventtype" placeholder="请选择活动类型" clearable>
          <el-option
            v-for="dict in dict.type.eventtype"
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
          v-hasPermi="['ysps:events:add']"
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
          v-hasPermi="['ysps:events:edit']"
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
          v-hasPermi="['ysps:events:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ysps:events:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="eventsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动id" align="center" prop="eventid" />
      <el-table-column label="发布者ID" align="center" prop="publisherid" />
      <el-table-column label="活动名称" align="center" prop="eventname" />
      <el-table-column label="活动时间" align="center" prop="eventtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.eventtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="eventlocation" />
      <el-table-column label="活动类型" align="center" prop="eventtype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.eventtype" :value="scope.row.eventtype"/>
        </template>
      </el-table-column>
      <el-table-column label="活动描述" align="center" prop="description" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ysps:events:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ysps:events:remove']"
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

    <!-- 添加或修改活动信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发布者ID" prop="publisherid">
          <el-input v-model="form.publisherid" placeholder="请输入发布者ID" />
        </el-form-item>
        <el-form-item label="活动名称" prop="eventname">
          <el-input v-model="form.eventname" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="活动时间" prop="eventtime">
          <el-date-picker clearable
            v-model="form.eventtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择活动时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="eventlocation">
          <el-input v-model="form.eventlocation" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="活动类型" prop="eventtype">
          <el-select v-model="form.eventtype" placeholder="请选择活动类型">
            <el-option
              v-for="dict in dict.type.eventtype"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="活动描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">记录活动参与者的信息，包括报名时间和签到状态信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddYspsParticipants">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteYspsParticipants">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="yspsParticipantsList" :row-class-name="rowYspsParticipantsIndex" @selection-change="handleYspsParticipantsSelectionChange" ref="yspsParticipants">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="参与者的用户ID" prop="userid" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userid" placeholder="请输入参与者的用户ID" />
            </template>
          </el-table-column>
          <el-table-column label="报名参加活动的时间" prop="signuptime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.signuptime" type="date" value-format="yyyy-MM-dd" placeholder="请选择报名参加活动的时间" />
            </template>
          </el-table-column>
          <el-table-column label="是否签到，是或否" prop="checkin" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.checkin" placeholder="请输入是否签到，是或否" />
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
import { listEvents, getEvents, delEvents, addEvents, updateEvents } from "@/api/ysps/events";

export default {
  name: "Events",
  dicts: ['eventtype'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedYspsParticipants: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动信息表格数据
      eventsList: [],
      // 记录活动参与者的信息，包括报名时间和签到状态表格数据
      yspsParticipantsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eventname: null,
        eventtime: null,
        eventtype: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        publisherid: [
          { required: true, message: "发布者ID不能为空", trigger: "blur" }
        ],
        eventname: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        eventtime: [
          { required: true, message: "活动时间不能为空", trigger: "blur" }
        ],
        eventlocation: [
          { required: true, message: "活动地点不能为空", trigger: "blur" }
        ],
        eventtype: [
          { required: true, message: "活动类型不能为空", trigger: "change" }
        ],
        description: [
          { required: true, message: "活动描述不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询活动信息列表 */
    getList() {
      this.loading = true;
      listEvents(this.queryParams).then(response => {
        this.eventsList = response.rows;
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
        eventid: null,
        publisherid: null,
        eventname: null,
        eventtime: null,
        eventlocation: null,
        eventtype: null,
        description: null
      };
      this.yspsParticipantsList = [];
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
      this.ids = selection.map(item => item.eventid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const eventid = row.eventid || this.ids
      getEvents(eventid).then(response => {
        this.form = response.data;
        this.yspsParticipantsList = response.data.yspsParticipantsList;
        this.open = true;
        this.title = "修改活动信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.yspsParticipantsList = this.yspsParticipantsList;
          if (this.form.eventid != null) {
            updateEvents(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEvents(this.form).then(response => {
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
      const eventids = row.eventid || this.ids;
      this.$modal.confirm('是否确认删除活动信息编号为"' + eventids + '"的数据项？').then(function() {
        return delEvents(eventids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 记录活动参与者的信息，包括报名时间和签到状态序号 */
    rowYspsParticipantsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 记录活动参与者的信息，包括报名时间和签到状态添加按钮操作 */
    handleAddYspsParticipants() {
      let obj = {};
      obj.userid = "";
      obj.signuptime = "";
      obj.checkin = "";
      this.yspsParticipantsList.push(obj);
    },
    /** 记录活动参与者的信息，包括报名时间和签到状态删除按钮操作 */
    handleDeleteYspsParticipants() {
      if (this.checkedYspsParticipants.length == 0) {
        this.$modal.msgError("请先选择要删除的记录活动参与者的信息，包括报名时间和签到状态数据");
      } else {
        const yspsParticipantsList = this.yspsParticipantsList;
        const checkedYspsParticipants = this.checkedYspsParticipants;
        this.yspsParticipantsList = yspsParticipantsList.filter(function(item) {
          return checkedYspsParticipants.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleYspsParticipantsSelectionChange(selection) {
      this.checkedYspsParticipants = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ysps/events/export', {
        ...this.queryParams
      }, `events_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
