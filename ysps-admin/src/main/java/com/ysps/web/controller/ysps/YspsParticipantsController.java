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
import com.ysps.ysps.domain.YspsParticipants;
import com.ysps.ysps.service.IYspsParticipantsService;
import com.ysps.common.utils.poi.ExcelUtil;
import com.ysps.common.core.page.TableDataInfo;

/**
 * 参与者信息Controller
 * 
 * @author lins
 * @date 2024-04-04
 */
@RestController
@RequestMapping("/ysps/participants")
public class YspsParticipantsController extends BaseController
{
    @Autowired
    private IYspsParticipantsService yspsParticipantsService;

    /**
     * 查询参与者信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:list')")
    @GetMapping("/list")
    public TableDataInfo list(YspsParticipants yspsParticipants)
    {
        startPage();
        List<YspsParticipants> list = yspsParticipantsService.selectYspsParticipantsList(yspsParticipants);
        return getDataTable(list);
    }

    /**
     * 导出参与者信息列表
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:export')")
    @Log(title = "参与者信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YspsParticipants yspsParticipants)
    {
        List<YspsParticipants> list = yspsParticipantsService.selectYspsParticipantsList(yspsParticipants);
        ExcelUtil<YspsParticipants> util = new ExcelUtil<YspsParticipants>(YspsParticipants.class);
        util.exportExcel(response, list, "参与者信息数据");
    }

    /**
     * 获取参与者信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:query')")
    @GetMapping(value = "/{participantid}")
    public AjaxResult getInfo(@PathVariable("participantid") Long participantid)
    {
        return success(yspsParticipantsService.selectYspsParticipantsByParticipantid(participantid));
    }

    /**
     * 新增参与者信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:add')")
    @Log(title = "参与者信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YspsParticipants yspsParticipants)
    {
        return toAjax(yspsParticipantsService.insertYspsParticipants(yspsParticipants));
    }

    /**
     * 修改参与者信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:edit')")
    @Log(title = "参与者信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YspsParticipants yspsParticipants)
    {
        return toAjax(yspsParticipantsService.updateYspsParticipants(yspsParticipants));
    }

    /**
     * 删除参与者信息
     */
    @PreAuthorize("@ss.hasPermi('ysps:participants:remove')")
    @Log(title = "参与者信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{participantids}")
    public AjaxResult remove(@PathVariable Long[] participantids)
    {
        return toAjax(yspsParticipantsService.deleteYspsParticipantsByParticipantids(participantids));
    }
}
