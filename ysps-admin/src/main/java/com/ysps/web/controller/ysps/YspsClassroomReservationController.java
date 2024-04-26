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
import com.ysps.ysps.domain.YspsClassroomReservation;
import com.ysps.ysps.service.IYspsClassroomReservationService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 教室预约信息Controller
 * 
 * @author lins
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/ysps/classroom_reservation")
public class YspsClassroomReservationController extends BaseController
{
    @Autowired
    private IYspsClassroomReservationService yspsClassroomReservationService;

    /**
     * 查询教室预约信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsClassroomReservation yspsClassroomReservation)
    {
        startPage();
        List<YspsClassroomReservation> list = yspsClassroomReservationService.selectYspsClassroomReservationList(yspsClassroomReservation);
        return getDataTable(list);
    }

    /**
     * 导出教室预约信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:export')")
    @Log(title = "教室预约信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsClassroomReservation yspsClassroomReservation)
    {
        List<YspsClassroomReservation> list = yspsClassroomReservationService.selectYspsClassroomReservationList(yspsClassroomReservation);
        ExcelUtil<YspsClassroomReservation> util = new ExcelUtil<YspsClassroomReservation>(YspsClassroomReservation.class);
        util.exportExcel(response, list, "教室预约信息数据");
    }

    /**
     * 获取教室预约信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:query')")
    @GetMapping(value = "/{reservationId}")
    public AjaxResult getInfo(@PathVariable("reservationId") Long reservationId)
    {
        return success(yspsClassroomReservationService.selectYspsClassroomReservationByReservationId(reservationId));
    }

    /**
     * 新增教室预约信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:add')")
    @Log(title = "教室预约信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsClassroomReservation yspsClassroomReservation)
    {
        return toAjax(yspsClassroomReservationService.insertYspsClassroomReservation(yspsClassroomReservation));
    }

    /**
     * 修改教室预约信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:edit')")
    @Log(title = "教室预约信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsClassroomReservation yspsClassroomReservation)
    {
        return toAjax(yspsClassroomReservationService.updateYspsClassroomReservation(yspsClassroomReservation));
    }

    /**
     * 删除教室预约信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:remove')")
    @Log(title = "教室预约信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reservationIds}")
    public AjaxResult remove(@PathVariable Long[] reservationIds)
    {
        return toAjax(yspsClassroomReservationService.deleteYspsClassroomReservationByReservationIds(reservationIds));
    }
    /**
     * 根据用户名获取教室预约信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:classroom_reservation:query')")
    @GetMapping(value = "/byUsername/{username}")
    public AjaxResult getInfoByUsername(@PathVariable("username") String username)
    {
        return success(yspsClassroomReservationService.selectYspsClassroomReservationByUsername(username));
    }
}
