package com.ysps.web.controller.ysps;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ysps.common.annotation.Log;
import com.ysps.common.core.controller.BaseController;
import com.ysps.common.core.domain.AjaxResult;
import com.ysps.common.enums.BusinessType;
import com.ysps.ysps.domain.YspsClassrooms;
import com.ysps.ysps.service.IYspsClassroomsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 教室状态Controller
 * 
 * @author lins
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/ysps/classrooms")
public class YspsClassroomsController extends BaseController {
    @Autowired
    private IYspsClassroomsService yspsClassroomsService;

    /**
     * 查询教室状态列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsClassrooms yspsClassrooms) {
        startPage();
        List<YspsClassrooms> list = yspsClassroomsService.selectYspsClassroomsList(yspsClassrooms);
        return getDataTable(list);
    }

    /**
     * 导出教室状态列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:export')")
    @Log(title = "教室状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsClassrooms yspsClassrooms) {
        List<YspsClassrooms> list = yspsClassroomsService.selectYspsClassroomsList(yspsClassrooms);
        ExcelUtil<YspsClassrooms> util = new ExcelUtil<YspsClassrooms>(YspsClassrooms.class);
        util.exportExcel(response, list, "教室状态数据");
    }

    /**
     * 获取教室状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:query')")
    @GetMapping(value = "/{classroomid}")
    public AjaxResult getInfo(@PathVariable("classroomid") Long classroomid) {
        return success(yspsClassroomsService.selectYspsClassroomsByClassroomid(classroomid));
    }

    /**
     * 新增教室状态
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:add')")
    @Log(title = "教室状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsClassrooms yspsClassrooms) {
        return toAjax(yspsClassroomsService.insertYspsClassrooms(yspsClassrooms));
    }

    /**
     * 修改教室状态
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:edit')")
    @Log(title = "教室状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsClassrooms yspsClassrooms) {
        return toAjax(yspsClassroomsService.updateYspsClassrooms(yspsClassrooms));
    }

    /**
     * 删除教室状态
     */
    @PreAuthorize("@ss.hasPermi('ysps:classrooms:remove')")
    @Log(title = "教室状态", businessType = BusinessType.DELETE)
    @DeleteMapping("/{classroomids}")
    public AjaxResult remove(@PathVariable Long[] classroomids) {
        return toAjax(yspsClassroomsService.deleteYspsClassroomsByClassroomids(classroomids));
    }
}
//    /**
//     * 修改教室预约人数
//     */
//    @PreAuthorize("@ss.hasPermi('ysps:classrooms:edit')")
//    @Log(title = "教室状态", businessType = BusinessType.UPDATE)
//    @PutMapping("/updateReservation/{classroomid}")
//    public AjaxResult updateReservationCount(@PathVariable("classroomid") Long classroomid, @RequestBody Long reservedSeats)
//    {
//        YspsClassrooms classroom = new YspsClassrooms();
//        classroom.setClassroomid(classroomid);
//        classroom.setReservedSeats(reservedSeats);
//
//        return toAjax(yspsClassroomsService.updateReservedSeats(classroom));
//    }
//
//    /**
//     * 修改教室落座人数
//     */
//    @PreAuthorize("@ss.hasPermi('ysps:classrooms:edit')")
//    @Log(title = "教室状态", businessType = BusinessType.UPDATE)
//    @PutMapping("/updateSeating/{classroomid}")
//    public AjaxResult updateSeatingCount(@PathVariable("classroomid") Long classroomid, @RequestBody Long currentSeating)
//    {
//        YspsClassrooms classroom = new YspsClassrooms();
//        classroom.setClassroomid(classroomid);
//        classroom.setCurrentSeating(currentSeating);
//
//        return toAjax(yspsClassroomsService.updateCurrentSeating(classroom));
//    }
//}
